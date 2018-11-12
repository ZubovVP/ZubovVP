package ru.job4j.parser;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertNotNull;

/**
 * Created by ZubovVP on 11.11.2018
 * zubovvp@yadndex.ru
 */
public class DownloaderTest {
    @Test
    public void download() {
        Downloader downloader = new Downloader();
        List<Offer> offers1;
        offers1 = downloader.download(true);
        assertNotNull(offers1);
        List<Offer> offers2;
        offers2 = downloader.download(false);
        assertNotNull(offers2);
    }
}