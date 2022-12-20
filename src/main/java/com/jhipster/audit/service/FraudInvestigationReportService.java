package com.jhipster.audit.service;

import com.jhipster.audit.domain.FraudInvestigationReport;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link FraudInvestigationReport}.
 */
public interface FraudInvestigationReportService {
    /**
     * Save a fraudInvestigationReport.
     *
     * @param fraudInvestigationReport the entity to save.
     * @return the persisted entity.
     */
    FraudInvestigationReport save(FraudInvestigationReport fraudInvestigationReport);

    /**
     * Updates a fraudInvestigationReport.
     *
     * @param fraudInvestigationReport the entity to update.
     * @return the persisted entity.
     */
    FraudInvestigationReport update(FraudInvestigationReport fraudInvestigationReport);

    /**
     * Partially updates a fraudInvestigationReport.
     *
     * @param fraudInvestigationReport the entity to update partially.
     * @return the persisted entity.
     */
    Optional<FraudInvestigationReport> partialUpdate(FraudInvestigationReport fraudInvestigationReport);

    /**
     * Get all the fraudInvestigationReports.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<FraudInvestigationReport> findAll(Pageable pageable);

    /**
     * Get the "id" fraudInvestigationReport.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<FraudInvestigationReport> findOne(String id);

    /**
     * Delete the "id" fraudInvestigationReport.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
