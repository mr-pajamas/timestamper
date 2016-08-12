package org.nelect.timestamper.user;

import org.nelect.timestamper.*;

/**
 * Created by Michael on 2016/7/6.
 */
public interface IdentityService {

    void submitIndividualInfo(IndividualInput input);

    void submitOrganizationInfo(OrganizationInput input);

    String getVerificationFailReasons();

    Principal getIdentity();
}
