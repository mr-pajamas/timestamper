package org.nelect.timestamper.internal.commands;

import java.util.Date;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.joda.time.DateTime;
import org.nelect.timestamper.Principal;
import org.nelect.timestamper.TimestamperException;
import org.nelect.timestamper.internal.*;
import org.nelect.timestamper.internal.interceptors.TransactionInterceptor;
import org.nelect.timestamper.internal.persistence.*;
import org.nelect.timestamper.user.DuplicateUserException;
import org.nelect.timestamper.user.VerificationException;

import static org.apache.commons.lang3.StringUtils.trimToNull;

/**
 * Created by Michael on 2016/7/5.
 */
@TransactionInterceptor.Transactional
public class NewEmailUserCommand implements Command<Principal> {

    @NotBlank(message = "Email不能为空")
    @Email(message = "非法的Email地址")
    private String email;

    @NotBlank(message = "验证码不能为空")
    private String verificationCode;

    @NotBlank(message = "密码不能为空")
    private String password;

    public NewEmailUserCommand(String email, String verificationCode, String password) {
        this.email = trimToNull(email);
        this.verificationCode = trimToNull(verificationCode);
        this.password = trimToNull(password);
    }

    @Override
    public Principal doExecute(CommandContext context) throws TimestamperException {

        UserManager userManager = context.getPersistenceContext().getUserManager();
        UserEntity duplicateUserEntity = userManager.getByEmail(email);
        if (duplicateUserEntity != null) throw new DuplicateUserException();

        EmailVerificationManager emailVerificationManager = context.getPersistenceContext().getEmailVerificationManager();
        EmailVerificationEntity emailVerificationEntity = emailVerificationManager.getByEmail(email);
        if (emailVerificationEntity == null ||
            (emailVerificationEntity.getUsed() != null && emailVerificationEntity.getUsed()) ||
            isPast(emailVerificationEntity.getExpiration())) {
            throw new VerificationException();
        } else {
            emailVerificationEntity = emailVerificationManager.newUpdater(emailVerificationEntity)
                .setUsed(true)
                .save();
            if (!emailVerificationEntity.getCode().equalsIgnoreCase(verificationCode))
                throw new VerificationException();
        }

        UserEntity userEntity = userManager.newUpdater()
            .setEmail(email)
            .setPassword(password)
            .setCreationTime(new Date())
            .setIdentityType(UserEntity.IdentityType.UNIDENTIFIED)
            .save();

        return new PrincipalImpl(userEntity);
    }

    private boolean isPast(Date date) {
        return new DateTime(date).isBeforeNow();
    }
}
