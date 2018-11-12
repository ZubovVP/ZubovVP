package ru.job4j.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Download offers from website.
 * <p>
 * Created by ZubovVP on 29.10.2018
 * zubovvp@yadndex.ru
 */
public class Downloader {
    private static final Logger LOGGER = LoggerFactory.getLogger(Downloader.class);
    private final SimpleDateFormat format = new SimpleDateFormat("d MMM yy, HH:mm", new Locale("ru", "RU"));
    private Document doc = null;
    private int step = 2;
    private String urlEnd;
    private final String url = "http://www.sql.ru/forum/job";
    private String newURL = this.url;
    private Elements elements;

    /**
     * Connect to website.
     */
    private void connect() {
        try {
            this.doc = Jsoup.connect(this.newURL).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (this.urlEnd == null) {
            Elements lastElements = doc.getElementsByAttributeValue("class", "sort_options");
            this.urlEnd = lastElements.select("a").last().attr("href");
        }
        this.elements = this.doc.select("tr");
    }

    /**
     * Download offers depending on the date.
     *
     * @param newTable - was create table or not.
     * @return - List of offers.
     */
    public List<Offer> download(Boolean newTable) {
        LOGGER.info("--------START DOWNLOAD OFFERS FROM WEBSITE--------");
        List<Offer> result = new ArrayList<>();
        Calendar startDate = Calendar.getInstance();

        if (newTable) {
            startDate.set(Calendar.DAY_OF_MONTH, 1);
            startDate.set(Calendar.MONTH, Calendar.JANUARY);
        } else {
            startDate.add(Calendar.WEEK_OF_YEAR, -1);
        }
        Calendar finalDate = Calendar.getInstance();
        while (startDate.getTimeInMillis() <= finalDate.getTimeInMillis() || this.url.equals(this.newURL)) {
            connect();
            for (Element el : this.elements) {
                String element = el.select("td.postslisttopic").text().toLowerCase();
                if ((element.contains("java") && !element.contains("script") && (element.contains("вакансия") || element.contains("работа") || element.contains("нужен")))) {
                    String url = el.select("td.postslisttopic > a[href]").attr("href");
                    String data = el.select("td").get(5).text();
                    finalDate = correctDate(data);
                    result.add(new Offer(element, url, new Timestamp(finalDate.getTimeInMillis())));
                }
            }
            this.newURL = String.format("%s/%s", this.url, step++);
        }
        LOGGER.info("--------FINISH DOWNLOAD OFFERS FROM WEBSITE--------");

        return result;
    }

    /**
     * Convert date.
     *
     * @param date - old date.
     * @return - new date.
     */
    private Calendar correctDate(String date) {
        Calendar calendar = Calendar.getInstance();
        if (date.contains("сегодня")) {
            calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(date.substring(9, 11)));
            calendar.set(Calendar.MINUTE, Integer.parseInt(date.substring(12, 14)));
        } else if (date.contains("вчера")) {
            calendar.add(Calendar.WEEK_OF_MONTH, -1);
            calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(date.substring(9, 11)));
            calendar.set(Calendar.MINUTE, Integer.parseInt(date.substring(12, 14)));
        }
        try {
            calendar.setTime(this.format.parse(date));
        } catch (ParseException e) {
            e.getMessage();
        }
        return calendar;
    }
}
