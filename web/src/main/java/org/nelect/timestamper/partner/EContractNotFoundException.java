package org.nelect.timestamper.partner;

import org.nelect.timestamper.TimestamperException;

/**
 * Created by Michael on 2016/6/10.
 */
public class EContractNotFoundException extends TimestamperException {

    public EContractNotFoundException() {
        super("指定的电子合同信息不存在");
    }
}
