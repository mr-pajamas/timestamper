package org.nelect.timestamper.internal.persistence;

/**
 * Created by Michael on 2016/7/9.
 */
public interface OrganizationInfoCriteria<C extends OrganizationInfoCriteria<C>> {

    C nameContains(String name);
}
