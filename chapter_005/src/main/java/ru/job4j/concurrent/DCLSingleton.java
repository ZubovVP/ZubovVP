package ru.job4j.concurrent;

/**
 * При использовании блокировки с двойной проверкой необходимо полю присваивать volatile, т.к. может возникнуть такая ситуация:
 * 1) Поток А вызвал метод instOf, проверил что поле inst пустое и получил доступ в синхронизированнй блок;
 * 2) Поток А инициализирует объект, но информация сохраняется в кеше CPU, после чего
 * Поток Б вызывает метод instOf, и начинает читать информацию из регистра, соответсвенно получит искажённую информацию.
 * 3) Чтобы такого не происходило нужно использовать volatile.
 * <p>
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 08.02.2021.
 */
public class DCLSingleton {
    private volatile static DCLSingleton inst;

    public static DCLSingleton instOf() {
        if (inst == null) {
            synchronized (DCLSingleton.class) {
                if (inst == null) {
                    inst = new DCLSingleton();
                }
            }
        }
        return inst;
    }

    private DCLSingleton() {
    }
}
