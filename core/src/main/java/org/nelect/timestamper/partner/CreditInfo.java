package org.nelect.timestamper.partner;

import org.nelect.timestamper.Registry;

/**
 * Created by Michael on 2016/5/30.
 */
public interface CreditInfo extends Registry {

    String getCheckId();

    String getName();

    String getDetails();
}
