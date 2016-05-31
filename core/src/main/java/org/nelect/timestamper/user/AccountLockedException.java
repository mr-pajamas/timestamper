package org.nelect.timestamper.user;

import org.nelect.timestamper.TimestamperException;

/**
 * Created by Michael on 2016/5/30.
 */
public class AccountLockedException extends TimestamperException {

    public AccountLockedException(String message) {
        super("账户被冻结");
    }
}
