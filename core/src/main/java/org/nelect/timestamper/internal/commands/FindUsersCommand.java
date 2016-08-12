package org.nelect.timestamper.internal.commands;

import java.util.LinkedList;
import java.util.List;

import org.nelect.timestamper.Principal;
import org.nelect.timestamper.TimestamperException;
import org.nelect.timestamper.admin.UserFilter;
import org.nelect.timestamper.internal.*;
import org.nelect.timestamper.internal.interceptors.TransactionInterceptor;
import org.nelect.timestamper.internal.persistence.UserEntity;
import org.nelect.timestamper.internal.persistence.UserQuery;

/**
 * Created by Michael on 2016/7/18.
 */
@TransactionInterceptor.Transactional
public class FindUsersCommand implements Command<List<Principal>> {

    private UserFilter filter;

    private int offset;
    private int limit;

    public FindUsersCommand(UserFilter filter, int offset, int limit) {
        this.filter = (filter == null ? new UserFilter() : filter);
        this.offset = (offset < 0 ? 0 : offset);
        this.limit = (limit < 1 ? 0 : limit);
    }

    @Override
    public List<Principal> doExecute(CommandContext context) throws TimestamperException {
        List<Principal> users = new LinkedList<>();

        UserQuery userQuery = context.getPersistenceContext().getUserManager().newQuery();

        if (filter.getIdentityType() != null) userQuery.identified(filter.getIdentityType() == UserFilter.IdentityType.INDIVIDUAL);
        if (filter.getUnverified() != null) userQuery.verificationTimeExists(!filter.getUnverified());

        for (UserEntity userEntity : userQuery.list(offset, limit)) {
            PrincipalBuilder principalBuilder = new PrincipalBuilder(userEntity);

            if (userEntity.getIdentityType() == UserEntity.IdentityType.INDIVIDUAL) {
                principalBuilder.individualInfo(context.getPersistenceContext().getIndividualInfoManager().getByUserId(userEntity.getId()));
            } else if (userEntity.getIdentityType() == UserEntity.IdentityType.ORGANIZATION) {
                principalBuilder.organizationInfo(context.getPersistenceContext().getOrganizationInfoManager().getByUserId(userEntity.getId()));
            }

            users.add(principalBuilder.build());
        }

        return users;
    }
}
