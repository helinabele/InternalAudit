package com.jhipster.audit.service;

import com.jhipster.audit.domain.AssignTask;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link AssignTask}.
 */
public interface AssignTaskService {
    /**
     * Save a assignTask.
     *
     * @param assignTask the entity to save.
     * @return the persisted entity.
     */
    AssignTask save(AssignTask assignTask);

    /**
     * Updates a assignTask.
     *
     * @param assignTask the entity to update.
     * @return the persisted entity.
     */
    AssignTask update(AssignTask assignTask);

    /**
     * Partially updates a assignTask.
     *
     * @param assignTask the entity to update partially.
     * @return the persisted entity.
     */
    Optional<AssignTask> partialUpdate(AssignTask assignTask);

    /**
     * Get all the assignTasks.
     *
     * @return the list of entities.
     */
    List<AssignTask> findAll();
    /**
     * Get all the AssignTask where Task is {@code null}.
     *
     * @return the {@link List} of entities.
     */
    List<AssignTask> findAllWhereTaskIsNull();

    /**
     * Get the "id" assignTask.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<AssignTask> findOne(String id);

    /**
     * Delete the "id" assignTask.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
