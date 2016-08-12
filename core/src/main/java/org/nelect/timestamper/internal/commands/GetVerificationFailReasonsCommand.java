package org.nelect.timestamper.internal.commands;

import org.nelect.timestamper.*;
import org.nelect.timestamper.internal.*;
import org.nelect.timestamper.internal.persistence.UserEntity;

/**
 * Created by Michael on 2016/7/18.
 */
@Privileged({Individual.class, Organization.class})
public class GetVerificationFailReasonsCommand implements Command<String> {

    @Override
    public String doExecute(CommandContext context) throws TimestamperException {
        if (context.getPrincipal() instanceof VerifiedPrincipal) {
            return null;
        }

        UserEntity entity = context.getPersistenceContext().getUserManager().get(context.getPrincipal().getId());
        return entity.getVerificationFailReasons();
    }
}
