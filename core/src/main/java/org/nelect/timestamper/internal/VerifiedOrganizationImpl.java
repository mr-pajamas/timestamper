package org.nelect.timestamper.internal;

import org.nelect.timestamper.VerifiedOrganization;
import org.nelect.timestamper.internal.persistence.OrganizationInfoEntity;
import org.nelect.timestamper.internal.persistence.UserEntity;

/**
 * Created by Michael on 2016/7/7.
 */
public class VerifiedOrganizationImpl extends OrganizationImpl implements VerifiedOrganization {

    private Verified verified;

    public VerifiedOrganizationImpl(UserEntity userEntity, OrganizationInfoEntity organizationInfoEntity) {
        super(userEntity, organizationInfoEntity);
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
