package org.nelect.timestamper.internal;

import org.nelect.timestamper.internal.commands.*;
import org.nelect.timestamper.partner.*;

/**
 * Created by Michael on 2016/6/10.
 */
public class EContractServiceImpl implements EContractService {

    private CommandExecutor executor;
    private CommandContextFactory contextFactory;

    public EContractServiceImpl(CommandExecutor executor, CommandContextFactory contextFactory) {
        this.executor = executor;
        this.contextFactory = contextFactory;
    }

    @Override
    public EContract timestampEContract(EContractInput input) {
        return executor.execute(new TimestampEContractCommand(input), contextFactory.newCommandContext());
    }

    @Override
    public EContract getEContract(String digest) {
        return executor.execute(new GetEContractCommand(digest), contextFactory.newCommandContext());
    }

    @Override
    public EContract findEContractByCheckId(String checkId) {
        return executor.execute(new FindEContractByCheckIdCommand(checkId), contextFactory.newCommandContext());
    }

    @Override
    public EContract findEContractByChecksum(String checksum) {
        return executor.execute(new FindEContractByChecksumCommand(checksum), contextFactory.newCommandContext());
    }
}
