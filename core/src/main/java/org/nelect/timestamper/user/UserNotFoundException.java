package org.nelect.timestamper.user;

import org.nelect.timestamper.TimestamperException;

/**
 * Created by Michael on 2016/5/30.
 */
public class UserNotFoundException extends TimestamperException {

    public UserNotFoundException() {
        super("指定账号不存在");
    }
}
