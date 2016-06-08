package org.nelect.timestamper.internal.persistence;

import java.util.Date;

/**
 * Created by Michael on 2016/5/30.
 */
public interface EInvoiceEntity extends Entity<String> {

    String getCheckId();

    String getPrincipal();

    String getCertNumber();

    String getAttachmentName();

    String getAttachmentContentType();

    String getAttachmentPath();

    String getAttachmentChecksum();

    String getDigest();

    String getTransactionId();

    Date getRegistrationTime();

    Boolean getConfident();
}
