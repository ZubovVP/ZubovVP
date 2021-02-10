package ru.job4j.io;

import net.jcip.annotations.ThreadSafe;

import java.io.*;
import java.nio.charset.Charset;


/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 10.02.2021.
 */
@ThreadSafe
public class ParseFile {
    private File file;

    public synchronized void setFile(File f) {
        this.file = f;
    }

    public synchronized File getFile() {
        return this.file;
    }

    public String getContent() {
        StringBuilder output = new StringBuilder();
        try (Reader i = new FileReader(this.file, Charset.forName("UTF-8"));
             BufferedReader reader = new BufferedReader(i)) {
            String data;
            while ((data = reader.readLine()) != null) {
                output.append(data);
                output.append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output.toString();
    }

    public synchronized String getContentWithoutUnicode() {
        StringBuilder output = new StringBuilder();
        try (InputStream is = new FileInputStream(this.file);
             BufferedInputStream bis = new BufferedInputStream(is)) {
            int data;
            while ((data = bis.read()) != -1) {
                output.append((char) data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output.toString();
    }

    public synchronized void saveContent(String content) {
        try (OutputStream o = new FileOutputStream(this.file);
             BufferedOutputStream bos = new BufferedOutputStream(o)) {
            bos.write(content.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
