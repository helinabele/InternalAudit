package com.jhipster.audit.repository;

import com.jhipster.audit.domain.Task;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the Task entity.
 */
@Repository
public interface TaskRepository extends MongoRepository<Task, String> {
    @Query("{}")
    Page<Task> findAllWithEagerRelationships(Pageable pageable);

    @Query("{}")
    List<Task> findAllWithEagerRelationships();

    @Query("{'id': ?0}")
    Optional<Task> findOneWithEagerRelationships(String id);
}
