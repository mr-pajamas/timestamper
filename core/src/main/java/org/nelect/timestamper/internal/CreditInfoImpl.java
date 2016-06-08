package org.nelect.timestamper.internal;

import java.io.File;
import java.util.Date;

import org.nelect.timestamper.Attachment;
import org.nelect.timestamper.internal.persistence.CreditInfoEntity;
import org.nelect.timestamper.partner.CreditInfo;

/**
 * Created by Michael on 2016/5/31.
 */
public class CreditInfoImpl implements CreditInfo {

    private CreditInfoEntity entity;

    public CreditInfoImpl(CreditInfoEntity entity) {
        this.entity = entity;
    }

    @Override
    public String getCheckId() {
        return entity.getCheckId();
    }

    @Override
    public String getName() {
        return entity.getName();
    }

    @Override
    public String getDetails() {
        return entity.getDetails();
    }

    @Override
    public String getPrincipalName() {
        return entity.getPrincipal();
    }

    @Override
    public Date getRegistrationTime() {
        return entity.getRegistrationTime();
    }

    @Override
    public Attachment getAttachment() {
        return new Attachment() {

            @Override
            public String getName() {
                return entity.getAttachmentName();
            }

            @Override
            public String getContentType() {
                return entity.getAttachmentContentType();
            }

            @Override
            public File getFile() {
                return new File(entity.getAttachmentPath());
            }
        };
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
    public boolean isConfident() {
        return entity.getConfident() != null && entity.getConfident();
    }
}
