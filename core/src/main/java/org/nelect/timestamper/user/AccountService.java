package org.nelect.timestamper.user;

import org.nelect.timestamper.Principal;

/**
 * Created by Michael on 2016/5/30.
 */
public interface AccountService {

    Principal authenticateByEmail(String email, String password)
        throws UserNotFoundException, AuthenticationException, AccountLockedException;

    Principal authenticateByMobile(String mobile, String password)
        throws UserNotFoundException, AuthenticationException, AccountLockedException;

    boolean isEmailUsed(String email);

    boolean isMobileUsed(String mobile);

    VerificationCodeHash sendEmailVerificationMessage(String email);

    VerificationCodeHash sendMobileVerificationMessage(String mobile);

    Principal newEmailUser(String email, String verificationCode, String password) throws DuplicateUserException, VerificationException;

    Principal newMobileUser(String mobile, String verificationCode, String password) throws DuplicateUserException, VerificationException;
}
