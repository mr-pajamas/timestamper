package org.nelect.timestamper.internal;

import java.util.List;

import org.nelect.timestamper.Principal;
import org.nelect.timestamper.admin.UserFilter;
import org.nelect.timestamper.admin.UserManagementService;
import org.nelect.timestamper.internal.commands.FindUsersCommand;
import org.nelect.timestamper.internal.commands.VerifyUserCommand;

/**
 * Created by Michael on 2016/7/18.
 */
public class UserManagementServiceImpl implements UserManagementService {

    private CommandExecutor executor;
    private CommandContextFactory contextFactory;

    public UserManagementServiceImpl(CommandExecutor executor, CommandContextFactory contextFactory) {
        this.executor = executor;
        this.contextFactory = contextFactory;
    }

    @Override
    public List<Principal> findUsers(UserFilter filter, int offset, int limit) {
        return executor.execute(new FindUsersCommand(filter, offset, limit), contextFactory.newCommandContext());
    }

    @Override
    public void verify(String userId) {
        verify(userId, null);
    }

    @Override
    public void verify(String userId, String failReasons) {
        executor.execute(new VerifyUserCommand(userId, failReasons), contextFactory.newCommandContext());
    }
}
