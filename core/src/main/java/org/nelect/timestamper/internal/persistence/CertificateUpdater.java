package org.nelect.timestamper.internal.persistence;

import java.util.Date;

/**
 * Created by Michael on 2016/7/3.
 */
public interface CertificateUpdater extends Updater<CertificateEntity> {

    CertificateUpdater setTitle(String title);

    CertificateUpdater setPrincipalId(String principalId);

    CertificateUpdater setRegistrationTime(Date registrationTime);

    CertificateUpdater setAttachmentName(String attachmentName);

    CertificateUpdater setAttachmentContentType(String attachmentContentType);

    CertificateUpdater setAttachmentPath(String attachmentPath);

    CertificateUpdater setDigest(String digest);

    CertificateUpdater setTransactionId(String transactionId);

    CertificateUpdater setConfident(Boolean confident);
}
