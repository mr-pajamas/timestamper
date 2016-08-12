package org.nelect.timestamper.internal.commands;

import org.nelect.timestamper.Principal;
import org.nelect.timestamper.TimestamperException;
import org.nelect.timestamper.internal.*;
import org.nelect.timestamper.internal.persistence.UserEntity;

/**
 * Created by Michael on 2016/7/6.
 */
@Privileged
public class GetIdentityCommand implements Command<Principal> {

    @Override
    public Principal doExecute(CommandContext context) throws TimestamperException {

        UserEntity entity = context.getPersistenceContext().getUserManager().get(context.getPrincipal().getId());
        PrincipalBuilder principalBuilder = new PrincipalBuilder(entity);

        if (entity.getIdentityType() == UserEntity.IdentityType.INDIVIDUAL) {
            principalBuilder.individualInfo(context.getPersistenceContext().getIndividualInfoManager().getByUserId(entity.getId()));
        } else if (entity.getIdentityType() == UserEntity.IdentityType.ORGANIZATION){
            principalBuilder.organizationInfo(context.getPersistenceContext().getOrganizationInfoManager().getByUserId(entity.getId()));
        }

        return principalBuilder.build();
    }
}
