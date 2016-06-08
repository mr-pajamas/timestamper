package org.nelect.timestamper.internal.persistence;

/**
 * Created by Michael on 2016/5/30.
 */
public interface CreditInfoCriteria<C extends CreditInfoCriteria<C>> {

    C typeIs(CreditInfoEntityType type);

    C checkIdOrNameContains(String checkIdOrName);

    C registrationTimeExists();

    C confident();

    C orderByRegistrationTime(boolean desc);
}
