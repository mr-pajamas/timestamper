package org.nelect.timestamper;

import java.util.Date;

/**
 * Created by Michael on 2016/5/30.
 */
public interface Principal {

    String getId();

    String getEmail();

    //boolean isEmailVerified();

    String getMobile();

    Date getRegistrationTime();
}
