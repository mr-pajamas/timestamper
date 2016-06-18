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
public class GetEContractCommand implements Command<EContract> {

    @NotBlank(message = "电子合同文件指纹不可为空")
    private String digest;

    public GetEContractCommand(String digest) {
        this.digest = trim(digest);
    }

    @Override
    public EContract doExecute(CommandContext context) throws TimestamperException {
        EContractEntity entity = context.getPersistenceContext().getEContractManager().getByDigest(digest);
        if (entity == null)
            return null;
        else
            return new EContractImpl(entity);
    }
}
