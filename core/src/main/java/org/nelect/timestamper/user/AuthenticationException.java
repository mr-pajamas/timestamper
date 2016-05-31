package org.nelect.timestamper.user;

import org.nelect.timestamper.TimestamperException;

/**
 * Created by Michael on 2016/5/30.
 */
public class AuthenticationException extends TimestamperException {

    public AuthenticationException(String message) {
        super("密码错误");
    }
}
