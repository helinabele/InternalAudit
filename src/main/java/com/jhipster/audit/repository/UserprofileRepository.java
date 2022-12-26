package com.jhipster.audit.repository;

import com.jhipster.audit.domain.Userprofile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the Userprofile entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UserprofileRepository extends MongoRepository<Userprofile, String> {}
