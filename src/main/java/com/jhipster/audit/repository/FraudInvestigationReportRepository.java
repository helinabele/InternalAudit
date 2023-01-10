package com.jhipster.audit.repository;

import com.jhipster.audit.domain.FraudInvestigationReport;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the FraudInvestigationReport entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FraudInvestigationReportRepository extends MongoRepository<FraudInvestigationReport, String> {}
