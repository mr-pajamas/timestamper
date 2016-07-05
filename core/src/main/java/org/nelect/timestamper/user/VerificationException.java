package org.nelect.timestamper.user;

import org.nelect.timestamper.TimestamperException;

/**
 * Created by Michael on 2016/7/1.
 */
public class VerificationException extends TimestamperException {

    public VerificationException() {
        super("邮箱/手机验证失败");
    }
}
