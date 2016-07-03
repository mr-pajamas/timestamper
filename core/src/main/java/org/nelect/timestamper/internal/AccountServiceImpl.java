package org.nelect.timestamper.internal;

import org.nelect.timestamper.Principal;
import org.nelect.timestamper.internal.commands.SendEmailVerificationMessageCommand;
import org.nelect.timestamper.internal.commands.SendMobileVerificationMessageCommand;
import org.nelect.timestamper.user.*;

/**
 * Created by Michael on 2016/7/2.
 */
public class AccountServiceImpl implements AccountService {

    private CommandExecutor executor;
    private CommandContextFactory contextFactory;

    public AccountServiceImpl(CommandExecutor executor, CommandContextFactory contextFactory) {
        this.executor = executor;
        this.contextFactory = contextFactory;
    }

    @Override
    public Principal authenticate(String emailOrMobile, String password) throws UserNotFoundException, AuthenticationException, AccountLockedException {
        return null;
    }

    @Override
    public boolean isEmailUsed(String email) {
        return false;
    }

    @Override
    public boolean isMobileUsed(String mobile) {
        return false;
    }

    @Override
    public VerificationCodeHash sendEmailVerificationMessage(String email) {
        return executor.execute(new SendEmailVerificationMessageCommand(email), contextFactory.newCommandContext());
    }

    @Override
    public VerificationCodeHash sendMobileVerificationMessage(String mobile) {
        return executor.execute(new SendMobileVerificationMessageCommand(mobile), contextFactory.newCommandContext());
    }

    @Override
    public Principal newEmailUser(String email, String verificationCode, String password) throws DuplicateUserException {
        return null;
    }

    @Override
    public Principal newMobileUser(String mobile, String verificationCode, String password) throws DuplicateUserException {
        return null;
    }
}
