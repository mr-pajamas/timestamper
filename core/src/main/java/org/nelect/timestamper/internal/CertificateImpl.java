package org.nelect.timestamper.internal;

import java.util.Date;

import org.nelect.timestamper.Certificate;
import org.nelect.timestamper.internal.persistence.CertificateEntity;

/**
 * Created by Michael on 2016/7/4.
 */
public class CertificateImpl implements Certificate {

    private CertificateEntity entity;
    private String principalName;

    public CertificateImpl(CertificateEntity entity, String principalName) {
        this.entity = entity;
        this.principalName = principalName;
    }

    @Override
    public String getTitle() {
        return entity.getTitle();
    }

    @Override
    public String getPrincipalName() {
        return principalName;
    }

/*
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
*/

    @Override
    public String getAttachmentId() {
        return entity.getAttachmentId();
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
