package org.nelect.timestamper.internal.commands;

import org.hibernate.validator.constraints.NotBlank;
import org.nelect.timestamper.TimestamperException;
import org.nelect.timestamper.internal.*;
import org.nelect.timestamper.internal.persistence.EInvoiceEntity;
import org.nelect.timestamper.partner.EInvoice;

import static org.apache.commons.lang3.StringUtils.trim;

/**
 * Created by Michael on 2016/6/10.
 */
public class GetEInvoiceCommand implements Command<EInvoice> {

    @NotBlank(message = "电子合同文件指纹不可为空")
    private String digest;

    public GetEInvoiceCommand(String digest) {
        this.digest = trim(digest);
    }

    @Override
    public EInvoice doExecute(CommandContext context) throws TimestamperException {
        EInvoiceEntity entity = context.getPersistenceContext().getEInvoiceManager().getByDigest(digest);
        if (entity == null)
            return null;
        else
            return new EInvoiceImpl(entity);
    }
}
