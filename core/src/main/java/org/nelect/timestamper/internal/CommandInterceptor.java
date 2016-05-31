package org.nelect.timestamper.internal;

/**
 * <p>Command execution interceptor to impose some AOP characteristics</p>
 *
 * @author <a href="mailto:lctang@foxmail.com">Michael Tang</a>
 * @version 1.0
 */
public abstract class CommandInterceptor implements CommandExecutor {

    /**
     * The next executor in the command execution chain
     */
    protected CommandExecutor next;

    /**
     * Set the next executor
     */
    public void setNext(CommandExecutor next) {
        this.next = next;
    }
}
