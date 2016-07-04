package org.nelect.timestamper.internal.mail;

import java.util.*;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.MimeMessageHelper;

import static org.apache.commons.lang3.StringUtils.*;

/**
 * Created by Michael on 2016/7/4.
 */
public class Mail {

    private String from;
    private String fromName;

    private String subject;
    private String text;
    private boolean html;

    private List<String> tos = new LinkedList<>();

    public String getFrom() {
        return from;
    }

    public String getFromName() {
        return fromName;
    }

    public String getSubject() {
        return subject;
    }

    public String getText() {
        return text;
    }

    public boolean isHtml() {
        return html;
    }

    public List<String> getTos() {
        return Collections.unmodifiableList(tos);
    }

    void setupMimeMessage(MimeMessage mimeMessage) throws Exception {
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
        for (String to : tos) messageHelper.addTo(to);

        if (fromName != null)
            messageHelper.setFrom(from, fromName);
        else
            messageHelper.setFrom(from);

        if (subject != null) messageHelper.setSubject(subject);
        if (isNoneEmpty(text)) messageHelper.setText(text, html);
    }

    public static class Builder {
        private Mail mail = new Mail();

        public Builder from(String from) {
            mail.from = trim(from);
            return this;
        }

        public Builder fromName(String fromName) {
            mail.fromName = trimToNull(fromName);
            return this;
        }

        public Builder subject(String subject) {
            mail.subject = trimToNull(subject);
            return this;
        }

        public Builder text(String text) {
            mail.text = text;
            return this;
        }

        public Builder html(boolean html) {
            mail.html = html;
            return this;
        }

        public Builder tos(String to, String... otherTos) {
            mail.tos.add(trim(to));
            for (String otherTo : otherTos) {
                mail.tos.add(trim(otherTo));
            }
            return this;
        }

        public Mail build() {
            return mail;
        }
    }
}
