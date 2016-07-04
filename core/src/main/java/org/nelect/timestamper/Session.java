package org.nelect.timestamper;

import org.nelect.timestamper.partner.*;
import org.nelect.timestamper.user.AccountService;

/**
 * Created by Michael on 2016/5/30.
 */
public interface Session {

    Principal getPrincipal();

    AccountService getAccountService();

    CertificateService getCertificateService();

    CreditworthinessService getCreditworthinessService();

    EContractService getEContractService();

    EInvoiceService getEInvoiceService();
}
