package org.nelect.timestamper.internal.persistence;

/**
 * <p>TODO</p>
 *
 * @author <a href="mailto:lctang@foxmail.com">Michael Tang</a>
 * @version 1.0
 */
public interface Transactional {

    /**
     * Begin a new transaction
     *
     * @throws IllegalStateException when begin an embedded transaction in a
     * transactional context while implementation doesn't support embedded transaction
     */
    void beginTransaction();

    /**
     * Commit and terminate current transaction
     *
     * @throws IllegalStateException if there is no transaction in the context to close
     */
    void endTransaction();

    /**
     * Rollback and terminate current transaction
     *
     * @throws IllegalStateException if there is no transaction in the context to close
     */
    void abortTransaction();
}
