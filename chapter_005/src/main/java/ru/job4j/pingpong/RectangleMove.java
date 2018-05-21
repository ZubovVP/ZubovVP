package ru.job4j.pingpong;

import javafx.scene.shape.Rectangle;

/**
 * @author Vitaly Zubov (zubovvp@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class RectangleMove implements Runnable {
    private final Rectangle rect;
    private volatile boolean running = true;

    /**
     * Constructor.
     *
     * @param rect - Rectangle.
     */
    public RectangleMove(Rectangle rect) {
        this.rect = rect;
    }

    /**
     * Shutdown from run.
     */
    public void shutdown() {
        running = false;
    }

    @Override
    public void run() {
        int x = 2;
        int y = -1;

        while (running) {
            if (this.rect.getY() >= 300) {
                y = -(int) (Math.random() * 10);
            } else if (this.rect.getY() <= 0) {
                y = (int) (Math.random() * 10);
            } else if (this.rect.getX() >= 300) {
                x = -2;
            } else if (this.rect.getX() <= 0) {
                x = 2;
            }

            this.rect.setX(this.rect.getX() + x);
            this.rect.setY(this.rect.getY() + y);
            try {
                Thread.sleep(35);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}