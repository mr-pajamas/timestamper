package org.nelect.timestamper;

import org.nelect.timestamper.partner.*;
import org.nelect.timestamper.user.AccountService;
import org.nelect.timestamper.user.IdentityService;

/**
 * Created by Michael on 2016/5/30.
 */
public interface Session {

    Principal getPrincipal();

    AccountService getAccountService();

    AttachmentService getAttachmentService();

    IdentityService getIdentityService();

    CertificateService getCertificateService();

    CreditworthinessService getCreditworthinessService();

    EContractService getEContractService();

    EInvoiceService getEInvoiceService();
}
