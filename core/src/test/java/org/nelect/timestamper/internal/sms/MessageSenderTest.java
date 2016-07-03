package org.nelect.timestamper.internal.sms;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.*;

/**
 * Created by Michael on 2016/6/29.
 */
public class MessageSenderTest extends AbstractSmsContextTest {

    @Autowired
    private MessageSender messageSender;

    @BeforeClass
    public void init() {

    }

    @BeforeMethod
    public void setUp() {

    }

    @Test
    public void sendMessage() {
        messageSender.batchSend("您的验证码为123456，15分钟内有效", "18601646631", "13795242060");
    }

    @AfterMethod
    public void tearDown() {

    }

    @AfterClass
    public void destroy() {

    }
}
