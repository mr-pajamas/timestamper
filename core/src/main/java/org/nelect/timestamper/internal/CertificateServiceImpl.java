package org.nelect.timestamper.internal;

import java.util.List;

import org.nelect.timestamper.*;
import org.nelect.timestamper.internal.commands.FindCertificatesCommand;
import org.nelect.timestamper.internal.commands.TimestampCertificateCommand;

/**
 * Created by Michael on 2016/7/4.
 */
public class CertificateServiceImpl implements CertificateService {

    private CommandExecutor executor;
    private CommandContextFactory contextFactory;

    public CertificateServiceImpl(CommandExecutor executor, CommandContextFactory contextFactory) {
        this.executor = executor;
        this.contextFactory = contextFactory;
    }

    @Override
    public Certificate timestampCertificate(CertificateInput input) {
        return executor.execute(new TimestampCertificateCommand(input), contextFactory.newCommandContext());
    }

    @Override
    public List<Certificate> findCertificates(String title, int offset, int limit) {
        return executor.execute(new FindCertificatesCommand(title, offset, limit), contextFactory.newCommandContext());
    }
}
