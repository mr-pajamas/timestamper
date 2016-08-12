package org.nelect.timestamper.internal.commands;

import java.io.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.nelect.timestamper.AttachmentInput;
import org.nelect.timestamper.TimestamperException;
import org.nelect.timestamper.internal.Command;
import org.nelect.timestamper.internal.CommandContext;

/**
 * Created by Michael on 2016/8/11.
 */
public class UploadAttachmentCommand implements Command<String> {

    @NotNull(message = "附件不可为空")
    @Valid
    private AttachmentInput input;

    public UploadAttachmentCommand(AttachmentInput input) {
        this.input = input;
    }

    @Override
    public String doExecute(CommandContext context) throws TimestamperException {

        String dir = context.getConfig().getProperty("attachment.directory");
        String id;

        try (InputStream in = input.openStream()) {
            id = DigestUtils.sha1Hex(in);
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }

        try {
            File transferred = new File(dir, id).getCanonicalFile();

            FileUtils.copyInputStreamToFile(input.openStream(), transferred);

            context.getPersistenceContext().getAttachmentManager().newUpdater()
                .setId(id)
                .setName(input.getName())
                .setContentType(input.getContentType())
                .setPath(transferred.getAbsolutePath())
                .save();

        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }

        return id;
    }
}
