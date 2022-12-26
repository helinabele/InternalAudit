package com.jhipster.audit.repository;

import com.jhipster.audit.domain.Region;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the Region entity.
 */
@Repository
public interface RegionRepository extends MongoRepository<Region, String> {
    @Query("{}")
    Page<Region> findAllWithEagerRelationships(Pageable pageable);

    @Query("{}")
    List<Region> findAllWithEagerRelationships();

    @Query("{'id': ?0}")
    Optional<Region> findOneWithEagerRelationships(String id);
}
