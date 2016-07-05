package org.nelect.timestamper.internal.persistence;

/**
 * Created by Michael on 2016/7/5.
 */
public interface UserManager extends EntityManager<String, UserEntity, UserUpdater, UserQuery> {

    UserEntity getByEmail(String email);

    UserEntity getByMobile(String mobile);
}
