package org.nelect.timestamper.internal;

import java.util.List;

import org.nelect.timestamper.internal.commands.*;
import org.nelect.timestamper.partner.*;

/**
 * Created by Michael on 2016/5/31.
 */
public class CreditworthinessServiceImpl implements CreditworthinessService {

    private CommandExecutor executor;
    private CommandContextFactory contextFactory;

    public CreditworthinessServiceImpl(CommandExecutor executor, CommandContextFactory contextFactory) {
        this.executor = executor;
        this.contextFactory = contextFactory;
    }

    @Override
    public CreditInfo timestampManufacturer(CreditInfoInput input) {
        return executor.execute(new TimestampManufacturerCommand(input), contextFactory.newCommandContext());
    }

    @Override
    public CreditInfo timestampProduct(CreditInfoInput input) {
        return executor.execute(new TimestampProductCommand(input), contextFactory.newCommandContext());
    }

    @Override
    public List<CreditInfo> findManufacturers(String checkIdOrName, int offset, int limit) {
        return executor.execute(new FindManufacturersCommand(checkIdOrName, offset, limit), contextFactory.newCommandContext());
    }

    @Override
    public List<CreditInfo> findProducts(String checkIdOrName, int offset, int limit) {
        return executor.execute(new FindProductsCommand(checkIdOrName, offset, limit), contextFactory.newCommandContext());
    }
}
