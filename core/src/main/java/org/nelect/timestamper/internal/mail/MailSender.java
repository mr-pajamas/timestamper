package org.nelect.timestamper.internal.mail;

/**
 * Created by Michael on 2016/7/2.
 */
public interface MailSender {

    void batchSend(String message, boolean html, String email, String... otherEmails);

    void batchSendAsync(String message, boolean html, String email, String... otherEmails);
}
