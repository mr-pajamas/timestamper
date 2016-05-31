package org.nelect.timestamper.internal.persistence;

/**
 * <p>TODO</p>
 *
 * @author <a href="mailto:lctang@foxmail.com">Michael Tang</a>
 * @version 1.0
 */
public interface EntityManager<K, E extends Entity<K>, U extends Updater<E>, Q extends Query<E>> {

    U newUpdater();

    U newUpdater(K id);

    U newUpdater(E entity);

    E get(K id);

    Q newQuery();
}
