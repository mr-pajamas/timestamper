package org.nelect.timestamper;

import java.util.List;

/**
 * Created by Michael on 2016/7/3.
 */
public interface CertificateService {

    Certificate timestampCertificate(CertificateInput input);

    List<Certificate> findCertificates(String title, int offset, int limit);
}
