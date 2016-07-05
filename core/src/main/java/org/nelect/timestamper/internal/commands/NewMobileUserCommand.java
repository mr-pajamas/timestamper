package org.nelect.timestamper.internal.commands;

import java.util.Date;
import javax.validation.constraints.Pattern;

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
public class NewMobileUserCommand implements Command<Principal> {

    @NotBlank(message = "手机号码不能为空")
    @Pattern(regexp = "^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$", message = "非法的手机号")
    private String mobile;

    @NotBlank(message = "验证码不能为空")
    private String verificationCode;

    @NotBlank(message = "密码不能为空")
    private String password;

    public NewMobileUserCommand(String mobile, String verificationCode, String password) {
        this.mobile = trimToNull(mobile);
        this.verificationCode = trimToNull(verificationCode);
        this.password = trimToNull(password);
    }

    @Override
    public Principal doExecute(CommandContext context) throws TimestamperException {
        // 1. check duplicates
        UserManager userManager = context.getPersistenceContext().getUserManager();
        UserEntity duplicateUserEntity = userManager.getByMobile(mobile);
        if (duplicateUserEntity != null) throw new DuplicateUserException();

        // 2. verify mobile
        MobileVerificationManager mobileVerificationManager = context.getPersistenceContext().getMobileVerificationManager();
        MobileVerificationEntity mobileVerificationEntity = mobileVerificationManager.getByMobile(mobile);
        if (mobileVerificationEntity == null ||
            (mobileVerificationEntity.getUsed() != null && mobileVerificationEntity.getUsed()) ||
            isPast(mobileVerificationEntity.getExpiration())) {

            throw new VerificationException();
        } else {
            mobileVerificationEntity = mobileVerificationManager.newUpdater(mobileVerificationEntity)
                .setUsed(true)
                .save();
            if (!mobileVerificationEntity.getCode().equalsIgnoreCase(verificationCode))
                throw new VerificationException();
        }

        // 3. create account
        UserEntity userEntity = userManager.newUpdater()
            .setMobile(mobile)
            .setPassword(password)
            .setCreationTime(new Date())
            .save();

        return new PrincipalImpl(userEntity);
    }

    private boolean isPast(Date date) {
        return new DateTime(date).isBeforeNow();
    }
}
