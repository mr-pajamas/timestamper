package org.nelect.timestamper.internal.commands;

import org.hibernate.validator.constraints.Email;
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
public class AuthenticateByEmailCommand implements Command<Principal> {

    @NotBlank(message = "Email不能为空")
    @Email(message = "非法的Email地址")
    private String email;

    @NotBlank(message = "密码不能为空")
    private String password;

    public AuthenticateByEmailCommand(String email, String password) {
        this.email = trimToNull(email);
        this.password = trimToNull(password);
    }

    @Override
    public Principal doExecute(CommandContext context) throws TimestamperException {

        UserManager userManager = context.getPersistenceContext().getUserManager();
        UserEntity userEntity = userManager.getByEmail(email);

        if (userEntity == null) throw new UserNotFoundException();

        if (!userEntity.getPassword().equals(password)) throw new AuthenticationException();

        return new PrincipalImpl(userEntity);
    }
}
