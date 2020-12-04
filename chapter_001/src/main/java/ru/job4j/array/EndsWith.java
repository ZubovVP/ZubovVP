package ru.job4j.array;


/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 04.12.2020.
 */
public class EndsWith {
    public static boolean endsWith(char[] word, char[] post) {
        boolean result = true;
        int indexWord = word.length - 1;
        for (int x = post.length - 1; x > 0; x--) {
            if (post[x] != word[indexWord--]) {
                result = false;
                break;
            }
        }
        return result;
    }
}
