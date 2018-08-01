package ru.job4j.wait;

import net.jcip.annotations.ThreadSafe;

/**
 * Created by ZubovVP on 31.07.2018
 * zubovvp@yadndex.ru
 */
@ThreadSafe
public class EmailNotification {

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
     * send info on email.
     *
     * @param subject - subject.
     * @param body    - body.
     * @param email   - email.
     */
    private void send(String subject, String body, String email) {
        //Nothing
    }
}
