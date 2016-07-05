package org.nelect.timestamper.internal.persistence;

import java.util.Date;

/**
 * Created by Michael on 2016/7/5.
 */
public interface UserUpdater extends Updater<UserEntity> {

    UserUpdater setEmail(String email);

    UserUpdater setMobile(String mobile);

    UserUpdater setPassword(String password);

    UserUpdater setCreationTime(Date creationTime);

    UserUpdater setVerificationTime(Date verificationTime);
}
