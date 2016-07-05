package org.nelect.timestamper.internal.commands;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.nelect.timestamper.Principal;
import org.nelect.timestamper.TimestamperException;
import org.nelect.timestamper.internal.*;
import org.nelect.timestamper.internal.persistence.UserEntity;
import org.nelect.timestamper.internal.persistence.UserManager;
import org.nelect.timestamper.user.AuthenticationException;
import org.nelect.timestamper.user.UserNotFoundException;

import static org.apache.commons.lang3.StringUtils.trimToNull;

/**
 * Created by Michael on 2016/7/5.
 */
public class AuthenticateByMobileCommand implements Command<Principal> {

    @NotBlank(message = "手机号码不能为空")
    @Pattern(regexp = "^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$", message = "非法的手机号")
    private String mobile;

    @NotBlank(message = "密码不能为空")
    private String password;

    public AuthenticateByMobileCommand(String mobile, String password) {
        this.mobile = trimToNull(mobile);
        this.password = trimToNull(password);
    }

    @Override
    public Principal doExecute(CommandContext context) throws TimestamperException {

        UserManager userManager = context.getPersistenceContext().getUserManager();
        UserEntity userEntity = userManager.getByMobile(mobile);

        if (userEntity == null) throw new UserNotFoundException();

        if (!userEntity.getPassword().equals(password)) throw new AuthenticationException();

        return new PrincipalImpl(userEntity);
    }
}
