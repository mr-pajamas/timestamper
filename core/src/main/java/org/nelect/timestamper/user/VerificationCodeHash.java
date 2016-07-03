package org.nelect.timestamper.user;

import java.util.Date;

/**
 * Created by Michael on 2016/7/1.
 */
public interface VerificationCodeHash {

    //String getMobile();

    String getCodeHash();

    String getCodeHashSalt();

    Date getExpiration();
}
