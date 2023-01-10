package com.jhipster.audit.repository;

import com.jhipster.audit.domain.Division;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the Division entity.
 */
@Repository
public interface DivisionRepository extends MongoRepository<Division, String> {
    @Query("{}")
    Page<Division> findAllWithEagerRelationships(Pageable pageable);

    @Query("{}")
    List<Division> findAllWithEagerRelationships();

    @Query("{'id': ?0}")
    Optional<Division> findOneWithEagerRelationships(String id);
}
