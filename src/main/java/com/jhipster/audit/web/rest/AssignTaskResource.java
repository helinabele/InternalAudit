package com.jhipster.audit.web.rest;

import com.jhipster.audit.domain.AssignTask;
import com.jhipster.audit.repository.AssignTaskRepository;
import com.jhipster.audit.service.AssignTaskService;
import com.jhipster.audit.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.StreamSupport;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.jhipster.audit.domain.AssignTask}.
 */
@RestController
@RequestMapping("/api")
public class AssignTaskResource {

    private final Logger log = LoggerFactory.getLogger(AssignTaskResource.class);

    private static final String ENTITY_NAME = "assignTask";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AssignTaskService assignTaskService;

    private final AssignTaskRepository assignTaskRepository;

    public AssignTaskResource(AssignTaskService assignTaskService, AssignTaskRepository assignTaskRepository) {
        this.assignTaskService = assignTaskService;
        this.assignTaskRepository = assignTaskRepository;
    }

    /**
     * {@code POST  /assign-tasks} : Create a new assignTask.
     *
     * @param assignTask the assignTask to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new assignTask, or with status {@code 400 (Bad Request)} if the assignTask has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/assign-tasks")
    public ResponseEntity<AssignTask> createAssignTask(@Valid @RequestBody AssignTask assignTask) throws URISyntaxException {
        log.debug("REST request to save AssignTask : {}", assignTask);
        if (assignTask.getId() != null) {
            throw new BadRequestAlertException("A new assignTask cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AssignTask result = assignTaskService.save(assignTask);
        return ResponseEntity
            .created(new URI("/api/assign-tasks/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /assign-tasks/:id} : Updates an existing assignTask.
     *
     * @param id the id of the assignTask to save.
     * @param assignTask the assignTask to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated assignTask,
     * or with status {@code 400 (Bad Request)} if the assignTask is not valid,
     * or with status {@code 500 (Internal Server Error)} if the assignTask couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/assign-tasks/{id}")
    public ResponseEntity<AssignTask> updateAssignTask(
        @PathVariable(value = "id", required = false) final String id,
        @Valid @RequestBody AssignTask assignTask
    ) throws URISyntaxException {
        log.debug("REST request to update AssignTask : {}, {}", id, assignTask);
        if (assignTask.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, assignTask.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!assignTaskRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        AssignTask result = assignTaskService.update(assignTask);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, assignTask.getId()))
            .body(result);
    }

    /**
     * {@code PATCH  /assign-tasks/:id} : Partial updates given fields of an existing assignTask, field will ignore if it is null
     *
     * @param id the id of the assignTask to save.
     * @param assignTask the assignTask to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated assignTask,
     * or with status {@code 400 (Bad Request)} if the assignTask is not valid,
     * or with status {@code 404 (Not Found)} if the assignTask is not found,
     * or with status {@code 500 (Internal Server Error)} if the assignTask couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/assign-tasks/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<AssignTask> partialUpdateAssignTask(
        @PathVariable(value = "id", required = false) final String id,
        @NotNull @RequestBody AssignTask assignTask
    ) throws URISyntaxException {
        log.debug("REST request to partial update AssignTask partially : {}, {}", id, assignTask);
        if (assignTask.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, assignTask.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!assignTaskRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<AssignTask> result = assignTaskService.partialUpdate(assignTask);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, assignTask.getId())
        );
    }

    /**
     * {@code GET  /assign-tasks} : get all the assignTasks.
     *
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of assignTasks in body.
     */
    @GetMapping("/assign-tasks")
    public List<AssignTask> getAllAssignTasks(@RequestParam(required = false) String filter) {
        if ("task-is-null".equals(filter)) {
            log.debug("REST request to get all AssignTasks where task is null");
            return assignTaskService.findAllWhereTaskIsNull();
        }
        log.debug("REST request to get all AssignTasks");
        return assignTaskService.findAll();
    }

    /**
     * {@code GET  /assign-tasks/:id} : get the "id" assignTask.
     *
     * @param id the id of the assignTask to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the assignTask, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/assign-tasks/{id}")
    public ResponseEntity<AssignTask> getAssignTask(@PathVariable String id) {
        log.debug("REST request to get AssignTask : {}", id);
        Optional<AssignTask> assignTask = assignTaskService.findOne(id);
        return ResponseUtil.wrapOrNotFound(assignTask);
    }

    /**
     * {@code DELETE  /assign-tasks/:id} : delete the "id" assignTask.
     *
     * @param id the id of the assignTask to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/assign-tasks/{id}")
    public ResponseEntity<Void> deleteAssignTask(@PathVariable String id) {
        log.debug("REST request to delete AssignTask : {}", id);
        assignTaskService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
