package org.nelect.timestamper.internal.persistence;

import java.util.Date;

/**
 * Created by Michael on 2016/7/1.
 */
public interface EmailVerificationEntity extends Entity<String> {

    String getEmail();

    String getCode();

    Date getExpiration();

    Boolean getUsed();
}
