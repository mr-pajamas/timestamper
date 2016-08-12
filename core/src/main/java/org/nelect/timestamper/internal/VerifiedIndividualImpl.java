package org.nelect.timestamper.internal;

import org.nelect.timestamper.VerifiedIndividual;
import org.nelect.timestamper.internal.persistence.IndividualInfoEntity;
import org.nelect.timestamper.internal.persistence.UserEntity;

/**
 * Created by Michael on 2016/7/7.
 */
public class VerifiedIndividualImpl extends IndividualImpl implements VerifiedIndividual {

    private Verified verified;

    public VerifiedIndividualImpl(UserEntity userEntity, IndividualInfoEntity individualInfoEntity) {
        super(userEntity, individualInfoEntity);
        verified = new Verified(userEntity);
    }

    @Override
    public int getCertificateCount() {
        return verified.getCertificateCount();
    }

    @Override
    public int getBalance() {
        return verified.getBalance();
    }
}
