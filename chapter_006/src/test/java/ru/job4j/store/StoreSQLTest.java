package ru.job4j.store;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by ZubovVP on 21.10.2018
 * zubovvp@yadndex.ru
 */
public class StoreSQLTest {
    private Config config = new Config();

    @Test
    public void testGenerate() {
        try {
            StoreSQL storeSQL = new StoreSQL(this.config);
            storeSQL.generate(1);
            List<StoreXML.Field> result = storeSQL.getAllEntries();
            assertThat(result.size(), is(1));
            storeSQL.deleteAllEntryes();

            storeSQL.generate(3);
            result = storeSQL.getAllEntries();
            assertThat(result.size(), is(3));
            storeSQL.deleteAllEntryes();


            storeSQL.generate(5);
            result = storeSQL.getAllEntries();
            assertThat(result.size(), is(5));
            storeSQL.deleteAllEntryes();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}