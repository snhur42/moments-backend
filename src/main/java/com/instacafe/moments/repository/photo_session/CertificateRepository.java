package com.instacafe.moments.repository.photo_session;

import com.instacafe.moments.model.photo_session.certificate.Certificate;
import com.instacafe.moments.repository.EntityRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CertificateRepository extends EntityRepository<Certificate> {
}
