package org.nelect.timestamper;

import java.util.Date;

/**
 * Created by Michael on 2016/5/30.
 */
public interface Registry {

    String getPrincipalName();

    Date getRegistrationTime();

    Attachment getAttachment();

    String getDigest();

    String getTransactionId();
}
