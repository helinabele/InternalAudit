package com.jhipster.audit.service;

import com.jhipster.audit.domain.FraudKnowledgeManagement;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link FraudKnowledgeManagement}.
 */
public interface FraudKnowledgeManagementService {
    /**
     * Save a fraudKnowledgeManagement.
     *
     * @param fraudKnowledgeManagement the entity to save.
     * @return the persisted entity.
     */
    FraudKnowledgeManagement save(FraudKnowledgeManagement fraudKnowledgeManagement);

    /**
     * Updates a fraudKnowledgeManagement.
     *
     * @param fraudKnowledgeManagement the entity to update.
     * @return the persisted entity.
     */
    FraudKnowledgeManagement update(FraudKnowledgeManagement fraudKnowledgeManagement);

    /**
     * Partially updates a fraudKnowledgeManagement.
     *
     * @param fraudKnowledgeManagement the entity to update partially.
     * @return the persisted entity.
     */
    Optional<FraudKnowledgeManagement> partialUpdate(FraudKnowledgeManagement fraudKnowledgeManagement);

    /**
     * Get all the fraudKnowledgeManagements.
     *
     * @return the list of entities.
     */
    List<FraudKnowledgeManagement> findAll();

    /**
     * Get the "id" fraudKnowledgeManagement.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<FraudKnowledgeManagement> findOne(String id);

    /**
     * Delete the "id" fraudKnowledgeManagement.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
