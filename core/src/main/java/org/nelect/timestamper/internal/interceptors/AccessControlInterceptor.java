package org.nelect.timestamper.internal.interceptors;

import org.nelect.timestamper.TimestamperException;
import org.nelect.timestamper.UnauthorizedActionException;
import org.nelect.timestamper.internal.*;

/**
 * Created by Michael on 2016/7/4.
 */
public class AccessControlInterceptor extends CommandInterceptor {

    @Override
    public <R> R execute(Command<R> command, CommandContext context) throws TimestamperException {

        if (!command.getClass().isAnnotationPresent(Privileged.class))
            return next.execute(command, context);

        Privileged privileged = command.getClass().getAnnotation(Privileged.class);

        if (privileged.value().isAssignableFrom(context.getPrincipal().getClass())) {
            return next.execute(command, context);
        } else {
            throw new UnauthorizedActionException(privileged.value());
        }
    }
}
