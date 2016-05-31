package org.nelect.timestamper.user;

import org.nelect.timestamper.Principal;

/**
 * Created by Michael on 2016/5/30.
 */
public interface AccountService {

    Principal authenticate(String emailOrMobile, String password)
        throws UserNotFoundException, AuthenticationException, AccountLockedException;

    boolean isEmailUsed(String email);

    boolean isMobileUsed(String mobile);

    Principal newEmailUser(String email, String password) throws DuplicateUserException;

    Principal newMobileUser(String mobile, String password) throws DuplicateUserException;
}
