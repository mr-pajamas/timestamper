package org.nelect.timestamper;

import java.util.Date;

/**
 * Created by Michael on 2016/5/30.
 */
public interface Registry {

    String getPrincipalName();

    //Attachment getAttachment();

    String getAttachmentId();

    String getDigest();

    String getTransactionId();

    Date getRegistrationTime();  // confirmed time

    boolean isConfident();
}
