package org.nelect.timestamper.internal.interceptors;

import java.lang.annotation.*;

import org.nelect.timestamper.TimestamperException;
import org.nelect.timestamper.internal.*;
import org.nelect.timestamper.internal.persistence.Context;


/**
 * <p>This class implements command-level persistence transaction in order to unify
 * and centralize the process of transactional operations.</p>
 *
 * @author <a href="mailto:lctang@foxmail.com">Michael Tang</a>
 * @version 1.0
 */
public class TransactionInterceptor extends CommandInterceptor {

    @Override
    public <R> R execute(Command<R> command, CommandContext context) throws TimestamperException {
        if (!command.getClass().isAnnotationPresent(Transactional.class))
            return next.execute(command, context);

        Context persistenceContext = context.getPersistenceContext();

        boolean failed = false;
        persistenceContext.beginTransaction();
        try {
            return next.execute(command, context);
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
    public static @interface Transactional {}
}
