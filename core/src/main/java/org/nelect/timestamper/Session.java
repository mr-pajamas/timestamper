package org.nelect.timestamper;

import org.nelect.timestamper.partner.*;

/**
 * Created by Michael on 2016/5/30.
 */
public interface Session {

    Principal getPrincipal();

    CreditworthinessService getCreditworthinessService();

    EContractQueryService getEContractQueryService();

    EInvoiceQueryService getEInvoiceQueryService();
}
