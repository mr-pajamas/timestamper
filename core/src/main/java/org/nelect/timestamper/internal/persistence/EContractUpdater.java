package org.nelect.timestamper.internal.persistence;

import java.util.Date;

/**
 * Created by Michael on 2016/6/10.
 */
public interface EContractUpdater extends Updater<EContractEntity> {

    EContractUpdater setCheckId(String checkId);

    EContractUpdater setPrincipal(String principal);

    EContractUpdater setCertNumber(String certNumber);

    EContractUpdater setChecksum(String checksum);

    EContractUpdater setDigest(String digest);

    EContractUpdater setTransactionId(String transactionId);

    EContractUpdater setRegistrationTime(Date registrationTime);

    EContractUpdater setConfident(Boolean confident);
}
