package org.nelect.timestamper.partner;

import org.nelect.timestamper.TimestamperException;

/**
 * Created by Michael on 2016/6/10.
 */
public class EContractNotConfidentException extends TimestamperException {

    public EContractNotConfidentException() {
        super("指定的电子合同尚未完成存证");
    }
}
