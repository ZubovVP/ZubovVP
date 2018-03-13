package ru.job4j.templates;

/**
 * @author Vitaly Zubov (zubovvp@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public abstract class TemplateAction {

    public TemplateAction(String name) {
    }
    abstract void start();

    abstract void finish();

    public void work() {
        this.start();
        this.finish();
    }

    public static void main(String[] args) {
        new TemplateAction("String") {
            public void start() {
            }
            public void finish() {
            }
        };
    }

}
