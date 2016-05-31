package org.nelect.timestamper.internal;

import java.util.List;

import org.nelect.timestamper.internal.commands.FindManufacturersCommand;
import org.nelect.timestamper.internal.commands.FindProductsCommand;
import org.nelect.timestamper.partner.CreditInfo;
import org.nelect.timestamper.partner.CreditworthinessQueryService;

/**
 * Created by Michael on 2016/5/31.
 */
public class CreditworthinessQueryServiceImpl implements CreditworthinessQueryService {

    private CommandExecutor executor;

    public CreditworthinessQueryServiceImpl(CommandExecutor executor) {
        this.executor = executor;
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
