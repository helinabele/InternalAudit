package com.jhipster.audit.web.rest;

import com.jhipster.audit.domain.FraudInvestigationReport;
import com.jhipster.audit.repository.FraudInvestigationReportRepository;
import com.jhipster.audit.security.AuthoritiesConstants;
import com.jhipster.audit.service.FraudInvestigationReportService;
import com.jhipster.audit.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.jhipster.audit.domain.FraudInvestigationReport}.
 */
@RestController
@RequestMapping("/api")
public class FraudInvestigationReportResource {

    private final Logger log = LoggerFactory.getLogger(FraudInvestigationReportResource.class);

    private static final String ENTITY_NAME = "fraudInvestigationReport";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FraudInvestigationReportService fraudInvestigationReportService;

    private final FraudInvestigationReportRepository fraudInvestigationReportRepository;

    public FraudInvestigationReportResource(
        FraudInvestigationReportService fraudInvestigationReportService,
        FraudInvestigationReportRepository fraudInvestigationReportRepository
    ) {
        this.fraudInvestigationReportService = fraudInvestigationReportService;
        this.fraudInvestigationReportRepository = fraudInvestigationReportRepository;
    }

    /**
     * {@code POST  /fraud-investigation-reports} : Create a new fraudInvestigationReport.
     *
     * @param fraudInvestigationReport the fraudInvestigationReport to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new fraudInvestigationReport, or with status {@code 400 (Bad Request)} if the fraudInvestigationReport has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/fraud-investigation-reports")
    //@PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.AUDITOR + "\")")
    //    @PreAuthorize("hasAuthority('ROLE_AUDITOR')")
    public ResponseEntity<FraudInvestigationReport> createFraudInvestigationReport(
        @RequestBody FraudInvestigationReport fraudInvestigationReport
    ) throws URISyntaxException {
        log.debug("REST request to save FraudInvestigationReport : {}", fraudInvestigationReport);
        if (fraudInvestigationReport.getId() != null) {
            throw new BadRequestAlertException("A new fraudInvestigationReport cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FraudInvestigationReport result = fraudInvestigationReportService.save(fraudInvestigationReport);
        return ResponseEntity
            .created(new URI("/api/fraud-investigation-reports/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /fraud-investigation-reports/:id} : Updates an existing fraudInvestigationReport.
     *
     * @param id the id of the fraudInvestigationReport to save.
     * @param fraudInvestigationReport the fraudInvestigationReport to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated fraudInvestigationReport,
     * or with status {@code 400 (Bad Request)} if the fraudInvestigationReport is not valid,
     * or with status {@code 500 (Internal Server Error)} if the fraudInvestigationReport couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/fraud-investigation-reports/{id}")
    public ResponseEntity<FraudInvestigationReport> updateFraudInvestigationReport(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody FraudInvestigationReport fraudInvestigationReport
    ) throws URISyntaxException {
        log.debug("REST request to update FraudInvestigationReport : {}, {}", id, fraudInvestigationReport);
        if (fraudInvestigationReport.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, fraudInvestigationReport.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!fraudInvestigationReportRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        FraudInvestigationReport result = fraudInvestigationReportService.update(fraudInvestigationReport);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, fraudInvestigationReport.getId()))
            .body(result);
    }

    /**
     * {@code PATCH  /fraud-investigation-reports/:id} : Partial updates given fields of an existing fraudInvestigationReport, field will ignore if it is null
     *
     * @param id the id of the fraudInvestigationReport to save.
     * @param fraudInvestigationReport the fraudInvestigationReport to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated fraudInvestigationReport,
     * or with status {@code 400 (Bad Request)} if the fraudInvestigationReport is not valid,
     * or with status {@code 404 (Not Found)} if the fraudInvestigationReport is not found,
     * or with status {@code 500 (Internal Server Error)} if the fraudInvestigationReport couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/fraud-investigation-reports/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<FraudInvestigationReport> partialUpdateFraudInvestigationReport(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody FraudInvestigationReport fraudInvestigationReport
    ) throws URISyntaxException {
        log.debug("REST request to partial update FraudInvestigationReport partially : {}, {}", id, fraudInvestigationReport);
        if (fraudInvestigationReport.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, fraudInvestigationReport.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!fraudInvestigationReportRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<FraudInvestigationReport> result = fraudInvestigationReportService.partialUpdate(fraudInvestigationReport);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, fraudInvestigationReport.getId())
        );
    }

    /**
     * {@code GET  /fraud-investigation-reports} : get all the fraudInvestigationReports.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of fraudInvestigationReports in body.
     */
    @GetMapping("/fraud-investigation-reports")
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.AUDITOR + "\")")
    public ResponseEntity<List<FraudInvestigationReport>> getAllFraudInvestigationReports(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of FraudInvestigationReports");
        Page<FraudInvestigationReport> page = fraudInvestigationReportService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /fraud-investigation-reports/:id} : get the "id" fraudInvestigationReport.
     *
     * @param id the id of the fraudInvestigationReport to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the fraudInvestigationReport, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/fraud-investigation-reports/{id}")
    public ResponseEntity<FraudInvestigationReport> getFraudInvestigationReport(@PathVariable String id) {
        log.debug("REST request to get FraudInvestigationReport : {}", id);
        Optional<FraudInvestigationReport> fraudInvestigationReport = fraudInvestigationReportService.findOne(id);
        return ResponseUtil.wrapOrNotFound(fraudInvestigationReport);
    }

    /**
     * {@code DELETE  /fraud-investigation-reports/:id} : delete the "id" fraudInvestigationReport.
     *
     * @param id the id of the fraudInvestigationReport to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/fraud-investigation-reports/{id}")
    public ResponseEntity<Void> deleteFraudInvestigationReport(@PathVariable String id) {
        log.debug("REST request to delete FraudInvestigationReport : {}", id);
        fraudInvestigationReportService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
