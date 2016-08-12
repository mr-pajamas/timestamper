package org.nelect.timestamper.internal;

import org.nelect.timestamper.*;
import org.nelect.timestamper.internal.commands.GetAttachmentCommand;
import org.nelect.timestamper.internal.commands.UploadAttachmentCommand;

/**
 * Created by Michael on 2016/8/11.
 */
public class AttachmentServiceImpl implements AttachmentService {

    private CommandExecutor executor;
    private CommandContextFactory contextFactory;

    public AttachmentServiceImpl(CommandExecutor executor, CommandContextFactory contextFactory) {
        this.executor = executor;
        this.contextFactory = contextFactory;
    }

    @Override
    public String upload(AttachmentInput input) {
        return executor.execute(new UploadAttachmentCommand(input), contextFactory.newCommandContext());
    }

    @Override
    public Attachment get(String id) {
        return executor.execute(new GetAttachmentCommand(id), contextFactory.newCommandContext());
    }
}
