package org.nelect.timestamper.internal.persistence;

/**
 * <p>TODO</p>
 *
 * @author <a href="mailto:lctang@foxmail.com">Michael Tang</a>
 * @version 1.0
 */
public interface Context extends Transactional {

    AttachmentManager getAttachmentManager();

    UserManager getUserManager();

    IndividualInfoManager getIndividualInfoManager();

    OrganizationInfoManager getOrganizationInfoManager();

    CertificateManager getCertificateManager();

    CreditInfoManager getCreditInfoManager();

    EContractManager getEContractManager();

    EInvoiceManager getEInvoiceManager();

    MobileVerificationManager getMobileVerificationManager();

    EmailVerificationManager getEmailVerificationManager();
}
