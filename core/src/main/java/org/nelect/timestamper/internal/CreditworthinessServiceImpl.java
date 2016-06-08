package org.nelect.timestamper.internal;

import java.util.List;

import org.nelect.timestamper.internal.commands.*;
import org.nelect.timestamper.partner.*;

/**
 * Created by Michael on 2016/5/31.
 */
public class CreditworthinessServiceImpl implements CreditworthinessService {

    private CommandExecutor executor;

    public CreditworthinessServiceImpl(CommandExecutor executor) {
        this.executor = executor;
    }

    @Override
    public CreditInfo timestampManufacturer(CreditInfoInput input) {
        return executor.execute(new TimestampManufacturerCommand(input));
    }

    @Override
    public CreditInfo timestampProduct(CreditInfoInput input) {
        return executor.execute(new TimestampProductCommand(input));
    }

    @Override
    public List<CreditInfo> findManufacturers(String checkIdOrName, int offset, int limit) {
        return executor.execute(new FindManufacturersCommand(checkIdOrName, offset, limit));
    }

    @Override
    public List<CreditInfo> findProducts(String checkIdOrName, int offset, int limit) {
        return executor.execute(new FindProductsCommand(checkIdOrName, offset, limit));
    }
}
