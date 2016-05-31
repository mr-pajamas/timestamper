package org.nelect.timestamper.internal.interceptors;

import org.nelect.timestamper.TimestamperException;
import org.nelect.timestamper.internal.Command;
import org.nelect.timestamper.internal.CommandInterceptor;
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

    public <R> R execute(Command<R> command) throws TimestamperException {

        String commandName = command.getClass().getName();
        logger.debug("Start executing command '{}'", commandName);

        try {
            R returnValue = next.execute(command);
            logger.debug("Command '{}' is executed successfully and returns: {}",
                commandName, returnValue);
            return returnValue;
        } catch (TimestamperException ce) {
            logger.debug("Command '{}' execution failed, cause: {}",
                commandName, ce.getMessage());
            throw ce;
        } catch (Exception e) {
            logger.error("Command '" + commandName + "' execution unexpectedly failed", e);
            throw e;
        }
    }
}
