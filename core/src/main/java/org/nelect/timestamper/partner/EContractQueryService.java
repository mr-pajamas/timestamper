package org.nelect.timestamper.partner;

/**
 * Created by Michael on 2016/5/30.
 */
public interface EContractQueryService {

    EContract getContractByCheckId(String checkId);

    EContract getContractBySizeAndChecksum(long size, String checksum);
}
