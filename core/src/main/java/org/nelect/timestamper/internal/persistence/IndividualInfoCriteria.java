package org.nelect.timestamper.internal.persistence;

/**
 * Created by Michael on 2016/7/9.
 */
public interface IndividualInfoCriteria<C extends IndividualInfoCriteria<C>> {

    C nameContains(String name);
}
