package org.nelect.timestamper.partner;

/**
 * Created by Michael on 2016/5/30.
 */
public interface EContractService {

    EContract timestampEContract(EContractInput input);

    EContract getEContract(String digest);

    EContract findEContractByCheckId(String checkId);

    EContract findEContractByChecksum(String checksum);
}
