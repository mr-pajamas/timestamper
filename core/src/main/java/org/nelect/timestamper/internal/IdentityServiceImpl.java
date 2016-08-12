package org.nelect.timestamper.internal;

import org.nelect.timestamper.*;
import org.nelect.timestamper.internal.commands.*;
import org.nelect.timestamper.user.IdentityService;

/**
 * Created by Michael on 2016/7/6.
 */
public class IdentityServiceImpl implements IdentityService {

    private CommandExecutor executor;
    private CommandContextFactory contextFactory;

    public IdentityServiceImpl(CommandExecutor executor, CommandContextFactory contextFactory) {
        this.executor = executor;
        this.contextFactory = contextFactory;
    }

    @Override
    public void submitIndividualInfo(IndividualInput input) {
        executor.execute(new SubmitIndividualInfoCommand(input), contextFactory.newCommandContext());
    }

    @Override
    public void submitOrganizationInfo(OrganizationInput input) {
        executor.execute(new SubmitOrganizationInfoCommand(input), contextFactory.newCommandContext());
    }

    @Override
    public String getVerificationFailReasons() {
        return executor.execute(new GetVerificationFailReasonsCommand(), contextFactory.newCommandContext());
    }

    @Override
    public Principal getIdentity() {
        return executor.execute(new GetIdentityCommand(), contextFactory.newCommandContext());
    }
}
