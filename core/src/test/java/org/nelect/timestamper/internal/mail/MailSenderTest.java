package org.nelect.timestamper.internal.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.*;

/**
 * <p>TODO</p>
 *
 * @author <a href="mailto:lctang@foxmail.com">Michael Tang</a>
 * @version 1.0
 */
public class MailSenderTest extends AbstractMailContextTest {

    @Autowired
    private MailSender mailSender;

    @BeforeClass
    public void init() {

    }

    @BeforeMethod
    public void setUp() {

    }

    @Test
    public void sendMail() {
        Mail mail = new Mail.Builder()
            .from("lctang@foxmail.com")
            .fromName("汤力丞")
            .subject("hahahah")
            .text("shit")
            .tos("lctang@foxmail.com")
            .build();
        mailSender.send(mail);
    }

    @AfterMethod
    public void tearDown() {

    }

    @AfterClass
    public void destroy() {

    }
}
