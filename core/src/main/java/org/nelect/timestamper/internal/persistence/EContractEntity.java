package org.nelect.timestamper.internal.persistence;

import java.util.Date;

/**
 * Created by Michael on 2016/5/30.
 */
public interface EContractEntity extends Entity<String> {

    String getCheckId();

    String getPrincipal();

    String getCertNumber();

    Date getRegistrationTime();

    String getAttachmentName();

    String getAttachmentPath();

    Long getAttachmentSize();

    String getAttachmentChecksum();

    String getDigest();

    String getTransactionId();
}
