package org.nelect.timestamper.internal;

import java.io.*;

import org.apache.commons.io.FileUtils;
import org.nelect.timestamper.Attachment;
import org.nelect.timestamper.internal.persistence.AttachmentEntity;

/**
 * Created by Michael on 2016/8/11.
 */
public class AttachmentImpl implements Attachment {

    private AttachmentEntity entity;

    public AttachmentImpl(AttachmentEntity entity) {
        this.entity = entity;
    }

    @Override
    public String getId() {
        return entity.getId();
    }

    @Override
    public String getName() {
        return entity.getName();
    }

    @Override
    public String getContentType() {
        return entity.getContentType();
    }

    @Override
    public InputStream openStream() throws IOException {
        return FileUtils.openInputStream(new File(entity.getPath()));
    }
}
