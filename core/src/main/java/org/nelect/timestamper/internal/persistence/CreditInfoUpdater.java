package org.nelect.timestamper.internal.persistence;

import java.io.File;
import java.util.Date;

/**
 * Created by Michael on 2016/5/30.
 */
public interface CreditInfoUpdater extends Updater<CreditInfoEntity> {

    CreditInfoUpdater setType(CreditInfoEntityType type);

    CreditInfoUpdater setCheckId(String checkId);

    CreditInfoUpdater setName(String name);

    CreditInfoUpdater setPrincipal(String principal);

    CreditInfoUpdater setRegistrationTime(Date registrationTime);

    CreditInfoUpdater setAttachmentName(String attachmentName);

    CreditInfoUpdater setAttachmentFile(File file);

    CreditInfoUpdater setAttachmentPath(String attachmentPath);

    CreditInfoUpdater setAttachmentSize(Long size);

    CreditInfoUpdater setDigest(String digest);

    CreditInfoUpdater setDetails(String details);

    CreditInfoUpdater setTransactionId(String transactionId);
}
