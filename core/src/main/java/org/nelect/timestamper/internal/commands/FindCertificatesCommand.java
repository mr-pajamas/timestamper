package org.nelect.timestamper.internal.commands;

import java.util.LinkedList;
import java.util.List;

import org.nelect.timestamper.*;
import org.nelect.timestamper.internal.*;
import org.nelect.timestamper.internal.persistence.CertificateEntity;
import org.nelect.timestamper.internal.persistence.CertificateQuery;

import static org.apache.commons.lang3.StringUtils.trimToNull;

/**
 * Created by Michael on 2016/7/4.
 */
@Privileged(VerifiedPrincipal.class)
public class FindCertificatesCommand implements Command<List<Certificate>> {

    private String title;
    private int offset;
    private int limit;

    public FindCertificatesCommand(String title, int offset, int limit) {
        this.title = trimToNull(title);
        this.offset = (offset < 0 ? 0 : offset);
        this.limit = (limit < 1 ? 0 : limit);
    }

    @Override
    public List<Certificate> doExecute(CommandContext context) throws TimestamperException {

        VerifiedPrincipal verifiedPrincipal = (VerifiedPrincipal) context.getPrincipal();

        List<Certificate> certificates = new LinkedList<>();

        CertificateQuery certificateQuery = context.getPersistenceContext().getCertificateManager().newQuery()
            .principalIdIs(context.getPrincipal().getId())
            .registrationTimeExists()
            .orderByRegistrationTime(true);

        if (title != null) certificateQuery.titleContains(title);

        for (CertificateEntity certificateEntity : certificateQuery.list(offset, limit)) {
            certificates.add(new CertificateImpl(certificateEntity, verifiedPrincipal.getName()));
        }

        return certificates;
    }
}
