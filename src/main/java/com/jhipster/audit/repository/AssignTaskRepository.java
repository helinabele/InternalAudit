package com.jhipster.audit.repository;

import com.jhipster.audit.domain.AssignTask;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the AssignTask entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AssignTaskRepository extends MongoRepository<AssignTask, String> {}
