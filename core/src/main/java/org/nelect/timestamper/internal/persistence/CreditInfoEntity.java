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

    Date getRegistrationTime();

    String getAttachmentName();

    String getAttachmentPath();

    Long getAttachmentSize();

    String getDigest();

    String getDetails();

    String getTransactionId();
}
