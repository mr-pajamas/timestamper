package org.nelect.timestamper.internal;

import java.util.Date;

import org.nelect.timestamper.Principal;
import org.nelect.timestamper.internal.persistence.UserEntity;

/**
 * Created by Michael on 2016/7/5.
 */
public class PrincipalImpl implements Principal {

    private UserEntity entity;

    public PrincipalImpl(UserEntity entity) {
        this.entity = entity;
    }

    @Override
    public String getId() {
        return entity.getId();
    }

    @Override
    public String getEmail() {
        return entity.getEmail();
    }

    @Override
    public String getMobile() {
        return entity.getMobile();
    }

    @Override
    public Date getRegistrationTime() {
        return entity.getCreationTime();
    }
}
