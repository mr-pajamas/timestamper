package org.nelect.timestamper.internal.persistence;

/**
 * Created by Michael on 2016/8/12.
 */
public interface EInvoiceCriteria<C extends EInvoiceCriteria<C>> {

    C registrationTimeExists();

    C confident();

    C orderByRegistrationTime(boolean desc);
}
