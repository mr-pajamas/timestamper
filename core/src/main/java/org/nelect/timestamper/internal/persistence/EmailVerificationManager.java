package org.nelect.timestamper.internal.persistence;

/**
 * Created by Michael on 2016/7/3.
 */
public interface EmailVerificationManager extends EntityManager<String, EmailVerificationEntity, EmailVerificationUpdater, Query<EmailVerificationEntity>> {

    EmailVerificationEntity getByEmail(String email);
}
