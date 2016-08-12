package org.nelect.timestamper.internal.persistence;

import java.util.Date;

/**
 * Created by Michael on 2016/5/30.
 */
public interface CreditInfoEntity extends Entity<String> {

    CreditInfoEntityType getType();

    String getCheckId();

    String getName();

    String getPrincipal();

    String getDetails();

/*
    String getAttachmentName();

    String getAttachmentContentType();

    String getAttachmentPath();
*/
    String getAttachmentId();

    String getDigest();

    String getTransactionId();

    Date getRegistrationTime();

    Boolean getConfident();
}
