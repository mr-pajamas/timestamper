package org.nelect.timestamper.internal;

import java.util.Date;

import org.nelect.timestamper.internal.persistence.EInvoiceEntity;
import org.nelect.timestamper.partner.EInvoice;

/**
 * Created by Michael on 2016/6/10.
 */
public class EInvoiceImpl implements EInvoice {

    private EInvoiceEntity entity;

    public EInvoiceImpl(EInvoiceEntity entity) {
        this.entity = entity;
    }

    @Override
    public String getCheckId() {
        return entity.getCheckId();
    }

    @Override
    public String getCertNumber() {
        return entity.getCertNumber();
    }

    @Override
    public String getChecksum() {
        return entity.getChecksum();
    }

    @Override
    public String getPrincipalName() {
        return entity.getPrincipal();
    }

/*
    @Override
    public Attachment getAttachment() {
        return null;
    }
*/

    @Override
    public String getAttachmentId() {
        return null;
    }

    @Override
    public String getDigest() {
        return entity.getDigest();
    }

    @Override
    public String getTransactionId() {
        return entity.getTransactionId();
    }

    @Override
    public Date getRegistrationTime() {
        return entity.getRegistrationTime();
    }

    @Override
    public boolean isConfident() {
        return entity.getConfident() != null && entity.getConfident();
    }
}
