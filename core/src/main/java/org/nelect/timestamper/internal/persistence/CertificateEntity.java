package org.nelect.timestamper.internal.persistence;

import java.util.Date;

/**
 * Created by Michael on 2016/7/3.
 */
public interface CertificateEntity extends Entity<String> {

    String getTitle();

    String getPrincipalId();

    String getAttachmentName();

    String getAttachmentContentType();

    String getAttachmentPath();

    String getDigest();

    String getTransactionId();

    Date getRegistrationTime();

    Boolean getConfident();
}
