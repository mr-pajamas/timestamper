package org.nelect.timestamper.internal.persistence;

/**
 * Created by Michael on 2016/7/5.
 */
public interface UserCriteria<C extends UserCriteria<C>> {

    C identified(boolean individualOrOrganization);

    C verificationTimeExists(boolean exists);

    C orderByCreationTime(boolean desc);
}
