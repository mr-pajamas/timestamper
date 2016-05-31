package org.nelect.timestamper.internal.persistence;

import java.util.List;

/**
 * <p>TODO</p>
 *
 * @author <a href="mailto:lctang@foxmail.com">Michael Tang</a>
 * @version 1.0
 */
public interface Query<E extends Entity<?>> {

    List<E> list();

    List<E> list(int offset, int limit);

    int count();
}
