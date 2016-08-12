package org.nelect.timestamper.internal;

import org.nelect.timestamper.Principal;
import org.nelect.timestamper.internal.persistence.*;

/**
 * Created by Michael on 2016/7/5.
 */
public class PrincipalBuilder {

    private UserEntity userEntity;
    private IndividualInfoEntity individualInfoEntity;
    private OrganizationInfoEntity organizationInfoEntity;

    public PrincipalBuilder(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public PrincipalBuilder individualInfo(IndividualInfoEntity individualInfoEntity) {
        this.individualInfoEntity = individualInfoEntity;
        return this;
    }

    public PrincipalBuilder organizationInfo(OrganizationInfoEntity organizationInfoEntity) {
        this.organizationInfoEntity = organizationInfoEntity;
        return this;
    }

    public Principal build() {
        if (userEntity.getVerificationTime() != null && userEntity.getVerificationFailReasons() == null) {
            if (individualInfoEntity != null) {
                return new VerifiedIndividualImpl(userEntity, individualInfoEntity);
            } else {
                return new VerifiedOrganizationImpl(userEntity, organizationInfoEntity);
            }
        } else {
            if (individualInfoEntity != null) {
                return new IndividualImpl(userEntity, individualInfoEntity);
            } else if (organizationInfoEntity != null) {
                return new OrganizationImpl(userEntity, organizationInfoEntity);
            } else {
                return new PrincipalImpl(userEntity);
            }
        }
    }
}
