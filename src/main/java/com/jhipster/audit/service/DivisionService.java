package com.jhipster.audit.service;

import com.jhipster.audit.domain.Division;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link Division}.
 */
public interface DivisionService {
    /**
     * Save a division.
     *
     * @param division the entity to save.
     * @return the persisted entity.
     */
    Division save(Division division);

    /**
     * Updates a division.
     *
     * @param division the entity to update.
     * @return the persisted entity.
     */
    Division update(Division division);

    /**
     * Partially updates a division.
     *
     * @param division the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Division> partialUpdate(Division division);

    /**
     * Get all the divisions.
     *
     * @return the list of entities.
     */
    List<Division> findAll();

    /**
     * Get all the divisions with eager load of many-to-many relationships.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Division> findAllWithEagerRelationships(Pageable pageable);

    /**
     * Get the "id" division.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Division> findOne(String id);

    /**
     * Delete the "id" division.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
