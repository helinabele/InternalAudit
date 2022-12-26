package com.jhipster.audit.web.rest;

import com.jhipster.audit.domain.Userprofile;
import com.jhipster.audit.repository.UserprofileRepository;
import com.jhipster.audit.service.UserprofileService;
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
 * REST controller for managing {@link com.jhipster.audit.domain.Userprofile}.
 */
@RestController
@RequestMapping("/api")
public class UserprofileResource {

    private final Logger log = LoggerFactory.getLogger(UserprofileResource.class);

    private static final String ENTITY_NAME = "userprofile";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final UserprofileService userprofileService;

    private final UserprofileRepository userprofileRepository;

    public UserprofileResource(UserprofileService userprofileService, UserprofileRepository userprofileRepository) {
        this.userprofileService = userprofileService;
        this.userprofileRepository = userprofileRepository;
    }

    /**
     * {@code POST  /userprofiles} : Create a new userprofile.
     *
     * @param userprofile the userprofile to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new userprofile, or with status {@code 400 (Bad Request)} if the userprofile has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/userprofiles")
    public ResponseEntity<Userprofile> createUserprofile(@RequestBody Userprofile userprofile) throws URISyntaxException {
        log.debug("REST request to save Userprofile : {}", userprofile);
        if (userprofile.getId() != null) {
            throw new BadRequestAlertException("A new userprofile cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Userprofile result = userprofileService.save(userprofile);
        return ResponseEntity
            .created(new URI("/api/userprofiles/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /userprofiles/:id} : Updates an existing userprofile.
     *
     * @param id the id of the userprofile to save.
     * @param userprofile the userprofile to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated userprofile,
     * or with status {@code 400 (Bad Request)} if the userprofile is not valid,
     * or with status {@code 500 (Internal Server Error)} if the userprofile couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/userprofiles/{id}")
    public ResponseEntity<Userprofile> updateUserprofile(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody Userprofile userprofile
    ) throws URISyntaxException {
        log.debug("REST request to update Userprofile : {}, {}", id, userprofile);
        if (userprofile.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, userprofile.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!userprofileRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Userprofile result = userprofileService.update(userprofile);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, userprofile.getId()))
            .body(result);
    }

    /**
     * {@code PATCH  /userprofiles/:id} : Partial updates given fields of an existing userprofile, field will ignore if it is null
     *
     * @param id the id of the userprofile to save.
     * @param userprofile the userprofile to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated userprofile,
     * or with status {@code 400 (Bad Request)} if the userprofile is not valid,
     * or with status {@code 404 (Not Found)} if the userprofile is not found,
     * or with status {@code 500 (Internal Server Error)} if the userprofile couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/userprofiles/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Userprofile> partialUpdateUserprofile(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody Userprofile userprofile
    ) throws URISyntaxException {
        log.debug("REST request to partial update Userprofile partially : {}, {}", id, userprofile);
        if (userprofile.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, userprofile.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!userprofileRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Userprofile> result = userprofileService.partialUpdate(userprofile);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, userprofile.getId())
        );
    }

    /**
     * {@code GET  /userprofiles} : get all the userprofiles.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of userprofiles in body.
     */
    @GetMapping("/userprofiles")
    public List<Userprofile> getAllUserprofiles() {
        log.debug("REST request to get all Userprofiles");
        return userprofileService.findAll();
    }

    /**
     * {@code GET  /userprofiles/:id} : get the "id" userprofile.
     *
     * @param id the id of the userprofile to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the userprofile, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/userprofiles/{id}")
    public ResponseEntity<Userprofile> getUserprofile(@PathVariable String id) {
        log.debug("REST request to get Userprofile : {}", id);
        Optional<Userprofile> userprofile = userprofileService.findOne(id);
        return ResponseUtil.wrapOrNotFound(userprofile);
    }

    /**
     * {@code DELETE  /userprofiles/:id} : delete the "id" userprofile.
     *
     * @param id the id of the userprofile to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/userprofiles/{id}")
    public ResponseEntity<Void> deleteUserprofile(@PathVariable String id) {
        log.debug("REST request to delete Userprofile : {}", id);
        userprofileService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
