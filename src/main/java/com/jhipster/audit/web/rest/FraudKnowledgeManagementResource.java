package com.jhipster.audit.web.rest;

import com.jhipster.audit.domain.FraudKnowledgeManagement;
import com.jhipster.audit.repository.FraudKnowledgeManagementRepository;
import com.jhipster.audit.service.FraudKnowledgeManagementService;
import com.jhipster.audit.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.jhipster.audit.domain.FraudKnowledgeManagement}.
 */
@RestController
@RequestMapping("/api")
public class FraudKnowledgeManagementResource {

    private final Logger log = LoggerFactory.getLogger(FraudKnowledgeManagementResource.class);

    private static final String ENTITY_NAME = "fraudKnowledgeManagement";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FraudKnowledgeManagementService fraudKnowledgeManagementService;

    private final FraudKnowledgeManagementRepository fraudKnowledgeManagementRepository;

    public FraudKnowledgeManagementResource(
        FraudKnowledgeManagementService fraudKnowledgeManagementService,
        FraudKnowledgeManagementRepository fraudKnowledgeManagementRepository
    ) {
        this.fraudKnowledgeManagementService = fraudKnowledgeManagementService;
        this.fraudKnowledgeManagementRepository = fraudKnowledgeManagementRepository;
    }

    /**
     * {@code POST  /fraud-knowledge-managements} : Create a new fraudKnowledgeManagement.
     *
     * @param fraudKnowledgeManagement the fraudKnowledgeManagement to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new fraudKnowledgeManagement, or with status {@code 400 (Bad Request)} if the fraudKnowledgeManagement has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/fraud-knowledge-managements")
    public ResponseEntity<FraudKnowledgeManagement> createFraudKnowledgeManagement(
        @RequestBody FraudKnowledgeManagement fraudKnowledgeManagement
    ) throws URISyntaxException {
        log.debug("REST request to save FraudKnowledgeManagement : {}", fraudKnowledgeManagement);
        if (fraudKnowledgeManagement.getId() != null) {
            throw new BadRequestAlertException("A new fraudKnowledgeManagement cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FraudKnowledgeManagement result = fraudKnowledgeManagementService.save(fraudKnowledgeManagement);
        return ResponseEntity
            .created(new URI("/api/fraud-knowledge-managements/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /fraud-knowledge-managements/:id} : Updates an existing fraudKnowledgeManagement.
     *
     * @param id the id of the fraudKnowledgeManagement to save.
     * @param fraudKnowledgeManagement the fraudKnowledgeManagement to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated fraudKnowledgeManagement,
     * or with status {@code 400 (Bad Request)} if the fraudKnowledgeManagement is not valid,
     * or with status {@code 500 (Internal Server Error)} if the fraudKnowledgeManagement couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/fraud-knowledge-managements/{id}")
    public ResponseEntity<FraudKnowledgeManagement> updateFraudKnowledgeManagement(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody FraudKnowledgeManagement fraudKnowledgeManagement
    ) throws URISyntaxException {
        log.debug("REST request to update FraudKnowledgeManagement : {}, {}", id, fraudKnowledgeManagement);
        if (fraudKnowledgeManagement.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, fraudKnowledgeManagement.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!fraudKnowledgeManagementRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        FraudKnowledgeManagement result = fraudKnowledgeManagementService.update(fraudKnowledgeManagement);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, fraudKnowledgeManagement.getId()))
            .body(result);
    }

    /**
     * {@code PATCH  /fraud-knowledge-managements/:id} : Partial updates given fields of an existing fraudKnowledgeManagement, field will ignore if it is null
     *
     * @param id the id of the fraudKnowledgeManagement to save.
     * @param fraudKnowledgeManagement the fraudKnowledgeManagement to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated fraudKnowledgeManagement,
     * or with status {@code 400 (Bad Request)} if the fraudKnowledgeManagement is not valid,
     * or with status {@code 404 (Not Found)} if the fraudKnowledgeManagement is not found,
     * or with status {@code 500 (Internal Server Error)} if the fraudKnowledgeManagement couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/fraud-knowledge-managements/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<FraudKnowledgeManagement> partialUpdateFraudKnowledgeManagement(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody FraudKnowledgeManagement fraudKnowledgeManagement
    ) throws URISyntaxException {
        log.debug("REST request to partial update FraudKnowledgeManagement partially : {}, {}", id, fraudKnowledgeManagement);
        if (fraudKnowledgeManagement.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, fraudKnowledgeManagement.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!fraudKnowledgeManagementRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<FraudKnowledgeManagement> result = fraudKnowledgeManagementService.partialUpdate(fraudKnowledgeManagement);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, fraudKnowledgeManagement.getId())
        );
    }

    /**
     * {@code GET  /fraud-knowledge-managements} : get all the fraudKnowledgeManagements.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of fraudKnowledgeManagements in body.
     */
    @GetMapping("/fraud-knowledge-managements")
    public List<FraudKnowledgeManagement> getAllFraudKnowledgeManagements() {
        log.debug("REST request to get all FraudKnowledgeManagements");
        return fraudKnowledgeManagementService.findAll();
    }

    /**
     * {@code GET  /fraud-knowledge-managements/:id} : get the "id" fraudKnowledgeManagement.
     *
     * @param id the id of the fraudKnowledgeManagement to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the fraudKnowledgeManagement, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/fraud-knowledge-managements/{id}")
    public ResponseEntity<FraudKnowledgeManagement> getFraudKnowledgeManagement(@PathVariable String id) {
        log.debug("REST request to get FraudKnowledgeManagement : {}", id);
        Optional<FraudKnowledgeManagement> fraudKnowledgeManagement = fraudKnowledgeManagementService.findOne(id);
        return ResponseUtil.wrapOrNotFound(fraudKnowledgeManagement);
    }

    /**
     * {@code DELETE  /fraud-knowledge-managements/:id} : delete the "id" fraudKnowledgeManagement.
     *
     * @param id the id of the fraudKnowledgeManagement to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/fraud-knowledge-managements/{id}")
    public ResponseEntity<Void> deleteFraudKnowledgeManagement(@PathVariable String id) {
        log.debug("REST request to delete FraudKnowledgeManagement : {}", id);
        fraudKnowledgeManagementService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
