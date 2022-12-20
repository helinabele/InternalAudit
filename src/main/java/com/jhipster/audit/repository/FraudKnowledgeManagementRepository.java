package com.jhipster.audit.repository;

import com.jhipster.audit.domain.FraudKnowledgeManagement;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the FraudKnowledgeManagement entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FraudKnowledgeManagementRepository extends MongoRepository<FraudKnowledgeManagement, String> {}
