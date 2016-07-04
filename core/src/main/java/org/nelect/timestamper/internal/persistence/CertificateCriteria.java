package org.nelect.timestamper.internal.persistence;

/**
 * Created by Michael on 2016/7/3.
 */
public interface CertificateCriteria<C extends CertificateCriteria<C>> {

    C titleContains(String title);

    C principalIdIs(String principalId);

    C registrationTimeExists();

    C confident();

    C orderByRegistrationTime(boolean desc);
}
