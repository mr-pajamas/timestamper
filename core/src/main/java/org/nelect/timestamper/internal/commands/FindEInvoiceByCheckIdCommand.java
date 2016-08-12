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
public class FindEInvoiceByCheckIdCommand implements Command<EInvoice> {

    @NotBlank(message = "查验ID不可为空")
    private String checkId;

    public FindEInvoiceByCheckIdCommand(String checkId) {
        this.checkId = trim(checkId);
    }

    @Override
    public EInvoice doExecute(CommandContext context) throws TimestamperException {
        EInvoiceEntity entity = context.getPersistenceContext().getEInvoiceManager().getByCheckId(checkId);
        if (entity == null)
            return null;
        else
            return new EInvoiceImpl(entity);
    }
}
