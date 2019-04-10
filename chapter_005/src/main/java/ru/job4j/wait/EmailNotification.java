package ru.job4j.wait;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by ZubovVP on 31.07.2018
 * zubovvp@yadndex.ru
 */
@ThreadSafe
public class EmailNotification {
    private ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    @GuardedBy("this")
    private BlockingQueue<User> users;

    /**
     * Constructor.
     *
     * @param users - users of list.
     */
    public EmailNotification(BlockingQueue<User> users) {
        this.users = users;
    }

    /**
     * Create threads and start them.
     *
     * @throws InterruptedException
     */
    public void start() throws InterruptedException {
        for (int x = 0; x < this.users.size(); x++) {
            this.pool.submit(new Runnable() {
                @Override
                public void run() {
                    User us = null;
                    try {
                        us = users.poll(1000, TimeUnit.MILLISECONDS);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (us != null) {
                        emailTo(us);
                    }
                }
            });
        }
        this.pool.shutdown();
        this.pool.awaitTermination(1, TimeUnit.DAYS);
    }

    /**
     * Send pattern on email.
     *
     * @param user - user.
     */
    public synchronized void emailTo(User user) {
        String subject = "Notification {" + user.getUserName() + "} to email {" + user.getEmail() + "}.";
        String body = "Add a new event to {" + user.getUserName() + "}.";
        send(subject, body, user.getEmail());
    }

    /**
     * Send info on email.
     *
     * @param subject - subject.
     * @param body    - body.
     * @param email   - email.
     */
    private void send(String subject, String body, String email) {
        //TODO
    }
}
