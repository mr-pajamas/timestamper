package org.nelect.timestamper.internal;

import org.nelect.timestamper.internal.commands.*;
import org.nelect.timestamper.partner.*;

/**
 * Created by Michael on 2016/6/10.
 */
public class EInvoiceServiceImpl implements EInvoiceService {

    private CommandExecutor executor;
    private CommandContextFactory contextFactory;

    public EInvoiceServiceImpl(CommandExecutor executor, CommandContextFactory contextFactory) {
        this.executor = executor;
        this.contextFactory = contextFactory;
    }

    @Override
    public EInvoice timestampEInvoice(EInvoiceInput input) {
        return executor.execute(new TimestampEInvoiceCommand(input), contextFactory.newCommandContext());
    }

    @Override
    public EInvoice getEInvoice(String digest) {
        return executor.execute(new GetEInvoiceCommand(digest), contextFactory.newCommandContext());
    }

    @Override
    public EInvoice findEInvoiceByCheckId(String checkId) {
        return executor.execute(new FindEInvoiceByCheckIdCommand(checkId), contextFactory.newCommandContext());
    }

    @Override
    public EInvoice findEInvoiceByCertNumber(String certNumber) {
        return executor.execute(new FindEInvoiceByCertNumberCommand(certNumber), contextFactory.newCommandContext());
    }

    @Override
    public EInvoice findEInvoiceByChecksum(String checksum) {
        return executor.execute(new FindEInvoiceByChecksumCommand(checksum), contextFactory.newCommandContext());
    }
}
