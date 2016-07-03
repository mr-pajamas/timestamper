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
        mailSender.batchSend("Hello dude!", false, "lctang@foxmail.com");
    }

    @AfterMethod
    public void tearDown() {

    }

    @AfterClass
    public void destroy() {

    }
}
