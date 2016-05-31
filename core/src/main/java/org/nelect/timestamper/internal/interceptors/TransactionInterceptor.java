package org.nelect.timestamper.internal.interceptors;

import java.lang.annotation.*;

import org.nelect.timestamper.TimestamperException;
import org.nelect.timestamper.internal.Command;
import org.nelect.timestamper.internal.CommandInterceptor;
import org.nelect.timestamper.internal.persistence.Context;


/**
 * <p>This class implements command-level persistence transaction in order to unify
 * and centralize the process of transactional operations.</p>
 *
 * @author <a href="mailto:lctang@foxmail.com">Michael Tang</a>
 * @version 1.0
 */
public class TransactionInterceptor extends CommandInterceptor {

    private Context persistenceContext;

    public TransactionInterceptor(Context persistenceContext) {
        this.persistenceContext = persistenceContext;
    }

    @Override
    public <R> R execute(Command<R> command) throws TimestamperException {

        if (command.getClass().isAnnotationPresent(NonTransactional.class))
            return next.execute(command);

        boolean failed = false;
        persistenceContext.beginTransaction();
        try {
            return next.execute(command);
        } catch (Exception e) {
            failed = true;
            try {
                persistenceContext.abortTransaction();
            } finally {
                throw e;
            }
        } finally {
            if (!failed) persistenceContext.endTransaction();
        }
    }

    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    public static @interface NonTransactional {}
}
