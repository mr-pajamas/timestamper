package org.nelect.timestamper.internal.persistence;

import java.util.Date;

/**
 * Created by Michael on 2016/7/2.
 */
public interface EmailVerificationUpdater extends Updater<EmailVerificationEntity> {

    EmailVerificationUpdater setEmail(String email);

    EmailVerificationUpdater setCode(String code);

    EmailVerificationUpdater setExpiration(Date expiration);

    EmailVerificationUpdater setUsed(Boolean used);
}
