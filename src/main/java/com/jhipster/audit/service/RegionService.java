package com.jhipster.audit.service;

import com.jhipster.audit.domain.Region;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link Region}.
 */
public interface RegionService {
    /**
     * Save a region.
     *
     * @param region the entity to save.
     * @return the persisted entity.
     */
    Region save(Region region);

    /**
     * Updates a region.
     *
     * @param region the entity to update.
     * @return the persisted entity.
     */
    Region update(Region region);

    /**
     * Partially updates a region.
     *
     * @param region the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Region> partialUpdate(Region region);

    /**
     * Get all the regions.
     *
     * @return the list of entities.
     */
    List<Region> findAll();

    /**
     * Get all the regions with eager load of many-to-many relationships.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Region> findAllWithEagerRelationships(Pageable pageable);

    /**
     * Get the "id" region.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Region> findOne(String id);

    /**
     * Delete the "id" region.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
