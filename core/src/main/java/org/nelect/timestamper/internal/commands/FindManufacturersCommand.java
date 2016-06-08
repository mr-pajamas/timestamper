package org.nelect.timestamper.internal.commands;

import java.util.LinkedList;
import java.util.List;

import org.hibernate.validator.constraints.NotBlank;
import org.nelect.timestamper.TimestamperException;
import org.nelect.timestamper.internal.*;
import org.nelect.timestamper.internal.persistence.*;
import org.nelect.timestamper.partner.CreditInfo;

import static org.apache.commons.lang3.StringUtils.trim;

/**
 * Created by Michael on 2016/5/31.
 */
public class FindManufacturersCommand implements Command<List<CreditInfo>> {

    @NotBlank(message = "企业查询关键字不可为空")
    private String checkIdOrName;
    private int offset;
    private int limit;

    public FindManufacturersCommand(String checkIdOrName, int offset, int limit) {
        this.checkIdOrName = trim(checkIdOrName);
        this.offset = (offset < 0 ? 0 : offset);
        this.limit = (limit < 1 ? 0 : limit);
    }

    @Override
    public List<CreditInfo> doExecute(CommandContext context) throws TimestamperException {
        List<CreditInfo> creditInfos = new LinkedList<>();

        CreditInfoQuery creditInfoQuery = context.getPersistenceContext().getCreditInfoManager().newQuery()
            .typeIs(CreditInfoEntityType.MANUFACTURER)
            .checkIdOrNameContains(checkIdOrName)
            .registrationTimeExists()
            .orderByRegistrationTime(true);

        for (CreditInfoEntity creditInfoEntity : creditInfoQuery.list(offset, limit)) {
            creditInfos.add(new CreditInfoImpl(creditInfoEntity));
        }

        return creditInfos;
    }
}
