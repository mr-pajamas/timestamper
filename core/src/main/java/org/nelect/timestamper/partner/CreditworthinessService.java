package org.nelect.timestamper.partner;

import java.util.List;

/**
 * Created by Michael on 2016/5/30.
 */
public interface CreditworthinessService {

    CreditInfo timestampManufacturer(CreditInfoInput input);

    CreditInfo timestampProduct(CreditInfoInput input);

    List<CreditInfo> findManufacturers(String checkIdOrName, int offset, int limit);

    List<CreditInfo> findProducts(String checkIdOrName, int offset, int limit);
}
