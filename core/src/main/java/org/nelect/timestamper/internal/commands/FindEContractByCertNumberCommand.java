package org.nelect.timestamper.internal.commands;

import org.hibernate.validator.constraints.NotBlank;
import org.nelect.timestamper.TimestamperException;
import org.nelect.timestamper.internal.*;
import org.nelect.timestamper.internal.persistence.EContractEntity;
import org.nelect.timestamper.partner.EContract;

import static org.apache.commons.lang3.StringUtils.trim;

/**
 * Created by Michael on 2016/6/10.
 */
public class FindEContractByCertNumberCommand implements Command<EContract> {

    @NotBlank(message = "电子合同编号不可为空")
    private String certNumber;

    public FindEContractByCertNumberCommand(String certNumber) {
        this.certNumber = trim(certNumber);
    }

    @Override
    public EContract doExecute(CommandContext context) throws TimestamperException {
        EContractEntity entity = context.getPersistenceContext().getEContractManager().getByCertNumber(certNumber);
        if (entity == null)
            return null;
        else
            return new EContractImpl(entity);
    }
}
