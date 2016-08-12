package org.nelect.timestamper.internal.persistence;

import java.util.Date;

/**
 * Created by Michael on 2016/7/6.
 */
public interface UserEntry<E extends UserEntry<E>> {

    E setEmail(String email);

    E setMobile(String mobile);

    E setPassword(String password);

    E setCreationTime(Date creationTime);

    E setIdentityType(UserEntity.IdentityType type);

    E setVerificationTime(Date verificationTime);

    E setVerificationFailReasons(String verificationFailReasons);

    E setCertificateCount(Integer certificateCount);

    E setBalance(Integer balance);
}
