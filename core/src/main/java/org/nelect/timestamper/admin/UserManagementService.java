package org.nelect.timestamper.admin;

import java.util.List;

import org.nelect.timestamper.Principal;

/**
 * Created by Michael on 2016/7/18.
 */
public interface UserManagementService {

    List<Principal> findUsers(UserFilter filter, int offset, int limit);

    void verify(String userId);

    void verify(String userId, String failReasons);
}
