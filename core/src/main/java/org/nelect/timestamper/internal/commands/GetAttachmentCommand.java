package org.nelect.timestamper.internal.commands;

import org.hibernate.validator.constraints.NotBlank;
import org.nelect.timestamper.Attachment;
import org.nelect.timestamper.TimestamperException;
import org.nelect.timestamper.internal.*;
import org.nelect.timestamper.internal.persistence.AttachmentEntity;

import static org.apache.commons.lang3.StringUtils.trim;

/**
 * Created by Michael on 2016/8/11.
 */
public class GetAttachmentCommand implements Command<Attachment> {

    @NotBlank(message = "附件ID不可为空")
    private String id;

    public GetAttachmentCommand(String id) {
        this.id = trim(id);
    }

    @Override
    public Attachment doExecute(CommandContext context) throws TimestamperException {
        AttachmentEntity entity = context.getPersistenceContext().getAttachmentManager().get(id);
        return entity == null ? null : new AttachmentImpl(entity);
    }
}
