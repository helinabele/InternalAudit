package com.jhipster.audit.service;

import com.jhipster.audit.domain.Branch;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link Branch}.
 */
public interface BranchService {
    /**
     * Save a branch.
     *
     * @param branch the entity to save.
     * @return the persisted entity.
     */
    Branch save(Branch branch);

    /**
     * Updates a branch.
     *
     * @param branch the entity to update.
     * @return the persisted entity.
     */
    Branch update(Branch branch);

    /**
     * Partially updates a branch.
     *
     * @param branch the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Branch> partialUpdate(Branch branch);

    /**
     * Get all the branches.
     *
     * @return the list of entities.
     */
    List<Branch> findAll();

    /**
     * Get all the branches with eager load of many-to-many relationships.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Branch> findAllWithEagerRelationships(Pageable pageable);

    /**
     * Get the "id" branch.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Branch> findOne(String id);

    /**
     * Delete the "id" branch.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
