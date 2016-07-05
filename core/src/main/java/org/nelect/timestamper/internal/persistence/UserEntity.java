package org.nelect.timestamper.internal.persistence;

import java.util.Date;

/**
 * Created by Michael on 2016/7/5.
 */
public interface UserEntity extends Entity<String> {

    String getId();

    String getEmail();

    String getMobile();

    String getPassword();

    Date getCreationTime();

    Date getVerificationTime();
}
