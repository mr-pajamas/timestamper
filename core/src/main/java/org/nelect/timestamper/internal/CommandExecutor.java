package org.nelect.timestamper.internal;

import org.nelect.timestamper.TimestamperException;

/**
 * <p>As its name implies, {@code CommandExecutor} is used to execute {@link Command}s</p>
 *
 * @author <a href="mailto:lctang@foxmail.com">Michael Tang</a>
 * @version 1.0
 */
public interface CommandExecutor {

    /**
     * Execute specific command
     *
     * @param command the command to execute
     * @param <R> return type of the command
     * @return return value of the command
     * @throws TimestamperException when the command execution throws an
     * domain-specific exception and it's re-thrown by this executor
     */
    <R> R execute(Command<R> command) throws TimestamperException;
}
