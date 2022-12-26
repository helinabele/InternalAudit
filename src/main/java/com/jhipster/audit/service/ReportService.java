package com.jhipster.audit.service;

import com.jhipster.audit.domain.Report;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link Report}.
 */
public interface ReportService {
    /**
     * Save a report.
     *
     * @param report the entity to save.
     * @return the persisted entity.
     */
    Report save(Report report);

    /**
     * Updates a report.
     *
     * @param report the entity to update.
     * @return the persisted entity.
     */
    Report update(Report report);

    /**
     * Partially updates a report.
     *
     * @param report the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Report> partialUpdate(Report report);

    /**
     * Get all the reports.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Report> findAll(Pageable pageable);

    /**
     * Get the "id" report.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Report> findOne(String id);

    /**
     * Delete the "id" report.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
