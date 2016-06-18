package org.nelect.timestamper.internal.persistence;

/**
 * Created by Michael on 2016/6/10.
 */
public interface EContractCriteria<C extends EContractCriteria<C>> {

    C registrationTimeExists();

    C confident();

    C orderByRegistrationTime(boolean desc);
}
