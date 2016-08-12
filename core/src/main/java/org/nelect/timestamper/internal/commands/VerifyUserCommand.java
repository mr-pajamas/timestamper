package org.nelect.timestamper.internal.commands;

import java.util.Date;

import org.hibernate.validator.constraints.NotBlank;
import org.nelect.timestamper.TimestamperException;
import org.nelect.timestamper.internal.Command;
import org.nelect.timestamper.internal.CommandContext;
import org.nelect.timestamper.internal.interceptors.TransactionInterceptor;
import org.nelect.timestamper.internal.persistence.*;
import org.nelect.timestamper.user.UserNotFoundException;

import static org.apache.commons.lang3.StringUtils.trimToNull;

/**
 * Created by Michael on 2016/7/18.
 */
@TransactionInterceptor.Transactional
public class VerifyUserCommand implements Command<Void> {

    @NotBlank(message = "用户ID不可为空")
    private String userId;
    private String failReasons;

    public VerifyUserCommand(String userId, String failReasons) {
        this.userId = trimToNull(userId);
        this.failReasons = trimToNull(failReasons);
    }

    @Override
    public Void doExecute(CommandContext context) throws TimestamperException {

        UserManager userManager = context.getPersistenceContext().getUserManager();
        UserEntity userEntity = userManager.get(userId);
        if (userEntity == null) throw new UserNotFoundException();

        UserUpdater userUpdater = userManager.newUpdater(userEntity).setVerificationTime(new Date());

        if (failReasons == null) {
            userUpdater.setCertificateCount(0).setBalance(3);
            // TODO: transaction list
        } else {
            userUpdater.setVerificationFailReasons(failReasons);
        }

        userUpdater.save();

        return null;
    }
}
