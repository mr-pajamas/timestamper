package org.nelect.timestamper.internal.interceptors;

import java.util.Set;
import javax.validation.*;

import org.hibernate.validator.HibernateValidator;
import org.nelect.timestamper.TimestamperException;
import org.nelect.timestamper.internal.*;

/**
 * <p>{@link Command} parameters validation interceptor. Note that this validation
 * interceptor should be used to check only technically-illegal parameters,
 * but not </p>
 *
 * @author <a href="mailto:lctang@foxmail.com">Michael Tang</a>
 * @version 1.0
 */
public class ValidationInterceptor extends CommandInterceptor {

    private static final ValidatorFactory factory = Validation
        .byProvider(HibernateValidator.class)
        .configure()
        .failFast(true)
        .buildValidatorFactory();

    private Validator validator = factory.getValidator();

    @Override
    public <R> R execute(Command<R> command, CommandContext context) throws TimestamperException {

        Set<ConstraintViolation<Command<R>>> violations = validator.validate(command);

        if (violations.isEmpty()) {
            return next.execute(command, context);
        } else {
            ConstraintViolation<?> violation = violations.iterator().next();
            throw new IllegalArgumentException(violation.getMessage());
        }
    }
}
