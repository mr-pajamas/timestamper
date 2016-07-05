package org.nelect.timestamper.user;

import org.nelect.timestamper.TimestamperException;

/**
 * Created by Michael on 2016/5/30.
 */
public class DuplicateUserException extends TimestamperException {

    public DuplicateUserException() {
        super("用户已经存在");
    }
}
