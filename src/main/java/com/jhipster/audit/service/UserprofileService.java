package com.jhipster.audit.service;

import com.jhipster.audit.domain.Userprofile;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Userprofile}.
 */
public interface UserprofileService {
    /**
     * Save a userprofile.
     *
     * @param userprofile the entity to save.
     * @return the persisted entity.
     */
    Userprofile save(Userprofile userprofile);

    /**
     * Updates a userprofile.
     *
     * @param userprofile the entity to update.
     * @return the persisted entity.
     */
    Userprofile update(Userprofile userprofile);

    /**
     * Partially updates a userprofile.
     *
     * @param userprofile the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Userprofile> partialUpdate(Userprofile userprofile);

    /**
     * Get all the userprofiles.
     *
     * @return the list of entities.
     */
    List<Userprofile> findAll();

    /**
     * Get the "id" userprofile.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Userprofile> findOne(String id);

    /**
     * Delete the "id" userprofile.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
