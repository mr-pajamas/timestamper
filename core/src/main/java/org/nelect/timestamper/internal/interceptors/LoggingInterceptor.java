package org.nelect.timestamper.internal.interceptors;

import org.nelect.timestamper.TimestamperException;
import org.nelect.timestamper.internal.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>{@link Command} execution logging interceptor</p>
 *
 * @author <a href="mailto:lctang@foxmail.com">Michael Tang</a>
 * @version 1.0
 */
public class LoggingInterceptor extends CommandInterceptor {

    private final Logger logger = LoggerFactory.getLogger(LoggingInterceptor.class);

    @Override
    public <R> R execute(Command<R> command, CommandContext context) throws TimestamperException {

        String commandName = command.getClass().getName();
        logger.debug("Start executing command '{}'", commandName);

        try {
            R returnValue = next.execute(command, context);
            logger.debug("Command '{}' is executed successfully and returns: {}",
                commandName, returnValue);
            return returnValue;
        } catch (TimestamperException te) {
            logger.debug("Command '{}' execution failed, cause: {}",
                commandName, te.getMessage());
            throw te;
        } catch (Exception e) {
            logger.error("Command '" + commandName + "' execution unexpectedly failed", e);
            throw e;
        }
    }
}
