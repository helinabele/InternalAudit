package com.jhipster.audit.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.jhipster.audit.IntegrationTest;
import com.jhipster.audit.domain.FraudInvestigationReport;
import com.jhipster.audit.repository.FraudInvestigationReportRepository;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Integration tests for the {@link FraudInvestigationReportResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class FraudInvestigationReportResourceIT {

    private static final String DEFAULT_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_TITLE = "BBBBBBBBBB";

    private static final String DEFAULT_EXECUTIVE_SUMMARY = "AAAAAAAAAA";
    private static final String UPDATED_EXECUTIVE_SUMMARY = "BBBBBBBBBB";

    private static final String DEFAULT_INTRODUCTION = "AAAAAAAAAA";
    private static final String UPDATED_INTRODUCTION = "BBBBBBBBBB";

    private static final String DEFAULT_OBJECTIVE = "AAAAAAAAAA";
    private static final String UPDATED_OBJECTIVE = "BBBBBBBBBB";

    private static final String DEFAULT_SCOPE = "AAAAAAAAAA";
    private static final String UPDATED_SCOPE = "BBBBBBBBBB";

    private static final String DEFAULT_LIMITATION = "AAAAAAAAAA";
    private static final String UPDATED_LIMITATION = "BBBBBBBBBB";

    private static final String DEFAULT_METHODOLOGY = "AAAAAAAAAA";
    private static final String UPDATED_METHODOLOGY = "BBBBBBBBBB";

    private static final String DEFAULT_FINDING_AND_ANALYSIS = "AAAAAAAAAA";
    private static final String UPDATED_FINDING_AND_ANALYSIS = "BBBBBBBBBB";

    private static final String DEFAULT_CONCLUSION = "AAAAAAAAAA";
    private static final String UPDATED_CONCLUSION = "BBBBBBBBBB";

    private static final String DEFAULT_RECOMMENDATION = "AAAAAAAAAA";
    private static final String UPDATED_RECOMMENDATION = "BBBBBBBBBB";

    private static final String DEFAULT_NAME_OF_MEMBERS = "AAAAAAAAAA";
    private static final String UPDATED_NAME_OF_MEMBERS = "BBBBBBBBBB";

    private static final String DEFAULT_SIGNATURE = "AAAAAAAAAA";
    private static final String UPDATED_SIGNATURE = "BBBBBBBBBB";

    private static final String DEFAULT_ANNEXES = "AAAAAAAAAA";
    private static final String UPDATED_ANNEXES = "BBBBBBBBBB";

    private static final String DEFAULT_AUTHOR = "AAAAAAAAAA";
    private static final String UPDATED_AUTHOR = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/fraud-investigation-reports";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private FraudInvestigationReportRepository fraudInvestigationReportRepository;

    @Autowired
    private MockMvc restFraudInvestigationReportMockMvc;

    private FraudInvestigationReport fraudInvestigationReport;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FraudInvestigationReport createEntity() {
        FraudInvestigationReport fraudInvestigationReport = new FraudInvestigationReport()
            .title(DEFAULT_TITLE)
            .executiveSummary(DEFAULT_EXECUTIVE_SUMMARY)
            .introduction(DEFAULT_INTRODUCTION)
            .objective(DEFAULT_OBJECTIVE)
            .scope(DEFAULT_SCOPE)
            .limitation(DEFAULT_LIMITATION)
            .methodology(DEFAULT_METHODOLOGY)
            .findingAndAnalysis(DEFAULT_FINDING_AND_ANALYSIS)
            .conclusion(DEFAULT_CONCLUSION)
            .recommendation(DEFAULT_RECOMMENDATION)
            .nameOfMembers(DEFAULT_NAME_OF_MEMBERS)
            .signature(DEFAULT_SIGNATURE)
            .annexes(DEFAULT_ANNEXES)
            .author(DEFAULT_AUTHOR);
        return fraudInvestigationReport;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FraudInvestigationReport createUpdatedEntity() {
        FraudInvestigationReport fraudInvestigationReport = new FraudInvestigationReport()
            .title(UPDATED_TITLE)
            .executiveSummary(UPDATED_EXECUTIVE_SUMMARY)
            .introduction(UPDATED_INTRODUCTION)
            .objective(UPDATED_OBJECTIVE)
            .scope(UPDATED_SCOPE)
            .limitation(UPDATED_LIMITATION)
            .methodology(UPDATED_METHODOLOGY)
            .findingAndAnalysis(UPDATED_FINDING_AND_ANALYSIS)
            .conclusion(UPDATED_CONCLUSION)
            .recommendation(UPDATED_RECOMMENDATION)
            .nameOfMembers(UPDATED_NAME_OF_MEMBERS)
            .signature(UPDATED_SIGNATURE)
            .annexes(UPDATED_ANNEXES)
            .author(UPDATED_AUTHOR);
        return fraudInvestigationReport;
    }

    @BeforeEach
    public void initTest() {
        fraudInvestigationReportRepository.deleteAll();
        fraudInvestigationReport = createEntity();
    }

    @Test
    void createFraudInvestigationReport() throws Exception {
        int databaseSizeBeforeCreate = fraudInvestigationReportRepository.findAll().size();
        // Create the FraudInvestigationReport
        restFraudInvestigationReportMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(fraudInvestigationReport))
            )
            .andExpect(status().isCreated());

        // Validate the FraudInvestigationReport in the database
        List<FraudInvestigationReport> fraudInvestigationReportList = fraudInvestigationReportRepository.findAll();
        assertThat(fraudInvestigationReportList).hasSize(databaseSizeBeforeCreate + 1);
        FraudInvestigationReport testFraudInvestigationReport = fraudInvestigationReportList.get(fraudInvestigationReportList.size() - 1);
        assertThat(testFraudInvestigationReport.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testFraudInvestigationReport.getExecutiveSummary()).isEqualTo(DEFAULT_EXECUTIVE_SUMMARY);
        assertThat(testFraudInvestigationReport.getIntroduction()).isEqualTo(DEFAULT_INTRODUCTION);
        assertThat(testFraudInvestigationReport.getObjective()).isEqualTo(DEFAULT_OBJECTIVE);
        assertThat(testFraudInvestigationReport.getScope()).isEqualTo(DEFAULT_SCOPE);
        assertThat(testFraudInvestigationReport.getLimitation()).isEqualTo(DEFAULT_LIMITATION);
        assertThat(testFraudInvestigationReport.getMethodology()).isEqualTo(DEFAULT_METHODOLOGY);
        assertThat(testFraudInvestigationReport.getFindingAndAnalysis()).isEqualTo(DEFAULT_FINDING_AND_ANALYSIS);
        assertThat(testFraudInvestigationReport.getConclusion()).isEqualTo(DEFAULT_CONCLUSION);
        assertThat(testFraudInvestigationReport.getRecommendation()).isEqualTo(DEFAULT_RECOMMENDATION);
        assertThat(testFraudInvestigationReport.getNameOfMembers()).isEqualTo(DEFAULT_NAME_OF_MEMBERS);
        assertThat(testFraudInvestigationReport.getSignature()).isEqualTo(DEFAULT_SIGNATURE);
        assertThat(testFraudInvestigationReport.getAnnexes()).isEqualTo(DEFAULT_ANNEXES);
        assertThat(testFraudInvestigationReport.getAuthor()).isEqualTo(DEFAULT_AUTHOR);
    }

    @Test
    void createFraudInvestigationReportWithExistingId() throws Exception {
        // Create the FraudInvestigationReport with an existing ID
        fraudInvestigationReport.setId("existing_id");

        int databaseSizeBeforeCreate = fraudInvestigationReportRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restFraudInvestigationReportMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(fraudInvestigationReport))
            )
            .andExpect(status().isBadRequest());

        // Validate the FraudInvestigationReport in the database
        List<FraudInvestigationReport> fraudInvestigationReportList = fraudInvestigationReportRepository.findAll();
        assertThat(fraudInvestigationReportList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllFraudInvestigationReports() throws Exception {
        // Initialize the database
        fraudInvestigationReportRepository.save(fraudInvestigationReport);

        // Get all the fraudInvestigationReportList
        restFraudInvestigationReportMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(fraudInvestigationReport.getId())))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE)))
            .andExpect(jsonPath("$.[*].executiveSummary").value(hasItem(DEFAULT_EXECUTIVE_SUMMARY)))
            .andExpect(jsonPath("$.[*].introduction").value(hasItem(DEFAULT_INTRODUCTION)))
            .andExpect(jsonPath("$.[*].objective").value(hasItem(DEFAULT_OBJECTIVE)))
            .andExpect(jsonPath("$.[*].scope").value(hasItem(DEFAULT_SCOPE)))
            .andExpect(jsonPath("$.[*].limitation").value(hasItem(DEFAULT_LIMITATION)))
            .andExpect(jsonPath("$.[*].methodology").value(hasItem(DEFAULT_METHODOLOGY)))
            .andExpect(jsonPath("$.[*].findingAndAnalysis").value(hasItem(DEFAULT_FINDING_AND_ANALYSIS)))
            .andExpect(jsonPath("$.[*].conclusion").value(hasItem(DEFAULT_CONCLUSION)))
            .andExpect(jsonPath("$.[*].recommendation").value(hasItem(DEFAULT_RECOMMENDATION)))
            .andExpect(jsonPath("$.[*].nameOfMembers").value(hasItem(DEFAULT_NAME_OF_MEMBERS)))
            .andExpect(jsonPath("$.[*].signature").value(hasItem(DEFAULT_SIGNATURE)))
            .andExpect(jsonPath("$.[*].annexes").value(hasItem(DEFAULT_ANNEXES)))
            .andExpect(jsonPath("$.[*].author").value(hasItem(DEFAULT_AUTHOR)));
    }

    @Test
    void getFraudInvestigationReport() throws Exception {
        // Initialize the database
        fraudInvestigationReportRepository.save(fraudInvestigationReport);

        // Get the fraudInvestigationReport
        restFraudInvestigationReportMockMvc
            .perform(get(ENTITY_API_URL_ID, fraudInvestigationReport.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(fraudInvestigationReport.getId()))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE))
            .andExpect(jsonPath("$.executiveSummary").value(DEFAULT_EXECUTIVE_SUMMARY))
            .andExpect(jsonPath("$.introduction").value(DEFAULT_INTRODUCTION))
            .andExpect(jsonPath("$.objective").value(DEFAULT_OBJECTIVE))
            .andExpect(jsonPath("$.scope").value(DEFAULT_SCOPE))
            .andExpect(jsonPath("$.limitation").value(DEFAULT_LIMITATION))
            .andExpect(jsonPath("$.methodology").value(DEFAULT_METHODOLOGY))
            .andExpect(jsonPath("$.findingAndAnalysis").value(DEFAULT_FINDING_AND_ANALYSIS))
            .andExpect(jsonPath("$.conclusion").value(DEFAULT_CONCLUSION))
            .andExpect(jsonPath("$.recommendation").value(DEFAULT_RECOMMENDATION))
            .andExpect(jsonPath("$.nameOfMembers").value(DEFAULT_NAME_OF_MEMBERS))
            .andExpect(jsonPath("$.signature").value(DEFAULT_SIGNATURE))
            .andExpect(jsonPath("$.annexes").value(DEFAULT_ANNEXES))
            .andExpect(jsonPath("$.author").value(DEFAULT_AUTHOR));
    }

    @Test
    void getNonExistingFraudInvestigationReport() throws Exception {
        // Get the fraudInvestigationReport
        restFraudInvestigationReportMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    void putExistingFraudInvestigationReport() throws Exception {
        // Initialize the database
        fraudInvestigationReportRepository.save(fraudInvestigationReport);

        int databaseSizeBeforeUpdate = fraudInvestigationReportRepository.findAll().size();

        // Update the fraudInvestigationReport
        FraudInvestigationReport updatedFraudInvestigationReport = fraudInvestigationReportRepository
            .findById(fraudInvestigationReport.getId())
            .get();
        updatedFraudInvestigationReport
            .title(UPDATED_TITLE)
            .executiveSummary(UPDATED_EXECUTIVE_SUMMARY)
            .introduction(UPDATED_INTRODUCTION)
            .objective(UPDATED_OBJECTIVE)
            .scope(UPDATED_SCOPE)
            .limitation(UPDATED_LIMITATION)
            .methodology(UPDATED_METHODOLOGY)
            .findingAndAnalysis(UPDATED_FINDING_AND_ANALYSIS)
            .conclusion(UPDATED_CONCLUSION)
            .recommendation(UPDATED_RECOMMENDATION)
            .nameOfMembers(UPDATED_NAME_OF_MEMBERS)
            .signature(UPDATED_SIGNATURE)
            .annexes(UPDATED_ANNEXES)
            .author(UPDATED_AUTHOR);

        restFraudInvestigationReportMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedFraudInvestigationReport.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedFraudInvestigationReport))
            )
            .andExpect(status().isOk());

        // Validate the FraudInvestigationReport in the database
        List<FraudInvestigationReport> fraudInvestigationReportList = fraudInvestigationReportRepository.findAll();
        assertThat(fraudInvestigationReportList).hasSize(databaseSizeBeforeUpdate);
        FraudInvestigationReport testFraudInvestigationReport = fraudInvestigationReportList.get(fraudInvestigationReportList.size() - 1);
        assertThat(testFraudInvestigationReport.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testFraudInvestigationReport.getExecutiveSummary()).isEqualTo(UPDATED_EXECUTIVE_SUMMARY);
        assertThat(testFraudInvestigationReport.getIntroduction()).isEqualTo(UPDATED_INTRODUCTION);
        assertThat(testFraudInvestigationReport.getObjective()).isEqualTo(UPDATED_OBJECTIVE);
        assertThat(testFraudInvestigationReport.getScope()).isEqualTo(UPDATED_SCOPE);
        assertThat(testFraudInvestigationReport.getLimitation()).isEqualTo(UPDATED_LIMITATION);
        assertThat(testFraudInvestigationReport.getMethodology()).isEqualTo(UPDATED_METHODOLOGY);
        assertThat(testFraudInvestigationReport.getFindingAndAnalysis()).isEqualTo(UPDATED_FINDING_AND_ANALYSIS);
        assertThat(testFraudInvestigationReport.getConclusion()).isEqualTo(UPDATED_CONCLUSION);
        assertThat(testFraudInvestigationReport.getRecommendation()).isEqualTo(UPDATED_RECOMMENDATION);
        assertThat(testFraudInvestigationReport.getNameOfMembers()).isEqualTo(UPDATED_NAME_OF_MEMBERS);
        assertThat(testFraudInvestigationReport.getSignature()).isEqualTo(UPDATED_SIGNATURE);
        assertThat(testFraudInvestigationReport.getAnnexes()).isEqualTo(UPDATED_ANNEXES);
        assertThat(testFraudInvestigationReport.getAuthor()).isEqualTo(UPDATED_AUTHOR);
    }

    @Test
    void putNonExistingFraudInvestigationReport() throws Exception {
        int databaseSizeBeforeUpdate = fraudInvestigationReportRepository.findAll().size();
        fraudInvestigationReport.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFraudInvestigationReportMockMvc
            .perform(
                put(ENTITY_API_URL_ID, fraudInvestigationReport.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(fraudInvestigationReport))
            )
            .andExpect(status().isBadRequest());

        // Validate the FraudInvestigationReport in the database
        List<FraudInvestigationReport> fraudInvestigationReportList = fraudInvestigationReportRepository.findAll();
        assertThat(fraudInvestigationReportList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchFraudInvestigationReport() throws Exception {
        int databaseSizeBeforeUpdate = fraudInvestigationReportRepository.findAll().size();
        fraudInvestigationReport.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFraudInvestigationReportMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(fraudInvestigationReport))
            )
            .andExpect(status().isBadRequest());

        // Validate the FraudInvestigationReport in the database
        List<FraudInvestigationReport> fraudInvestigationReportList = fraudInvestigationReportRepository.findAll();
        assertThat(fraudInvestigationReportList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamFraudInvestigationReport() throws Exception {
        int databaseSizeBeforeUpdate = fraudInvestigationReportRepository.findAll().size();
        fraudInvestigationReport.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFraudInvestigationReportMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(fraudInvestigationReport))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the FraudInvestigationReport in the database
        List<FraudInvestigationReport> fraudInvestigationReportList = fraudInvestigationReportRepository.findAll();
        assertThat(fraudInvestigationReportList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateFraudInvestigationReportWithPatch() throws Exception {
        // Initialize the database
        fraudInvestigationReportRepository.save(fraudInvestigationReport);

        int databaseSizeBeforeUpdate = fraudInvestigationReportRepository.findAll().size();

        // Update the fraudInvestigationReport using partial update
        FraudInvestigationReport partialUpdatedFraudInvestigationReport = new FraudInvestigationReport();
        partialUpdatedFraudInvestigationReport.setId(fraudInvestigationReport.getId());

        partialUpdatedFraudInvestigationReport
            .introduction(UPDATED_INTRODUCTION)
            .findingAndAnalysis(UPDATED_FINDING_AND_ANALYSIS)
            .conclusion(UPDATED_CONCLUSION)
            .nameOfMembers(UPDATED_NAME_OF_MEMBERS)
            .signature(UPDATED_SIGNATURE)
            .annexes(UPDATED_ANNEXES);

        restFraudInvestigationReportMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedFraudInvestigationReport.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedFraudInvestigationReport))
            )
            .andExpect(status().isOk());

        // Validate the FraudInvestigationReport in the database
        List<FraudInvestigationReport> fraudInvestigationReportList = fraudInvestigationReportRepository.findAll();
        assertThat(fraudInvestigationReportList).hasSize(databaseSizeBeforeUpdate);
        FraudInvestigationReport testFraudInvestigationReport = fraudInvestigationReportList.get(fraudInvestigationReportList.size() - 1);
        assertThat(testFraudInvestigationReport.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testFraudInvestigationReport.getExecutiveSummary()).isEqualTo(DEFAULT_EXECUTIVE_SUMMARY);
        assertThat(testFraudInvestigationReport.getIntroduction()).isEqualTo(UPDATED_INTRODUCTION);
        assertThat(testFraudInvestigationReport.getObjective()).isEqualTo(DEFAULT_OBJECTIVE);
        assertThat(testFraudInvestigationReport.getScope()).isEqualTo(DEFAULT_SCOPE);
        assertThat(testFraudInvestigationReport.getLimitation()).isEqualTo(DEFAULT_LIMITATION);
        assertThat(testFraudInvestigationReport.getMethodology()).isEqualTo(DEFAULT_METHODOLOGY);
        assertThat(testFraudInvestigationReport.getFindingAndAnalysis()).isEqualTo(UPDATED_FINDING_AND_ANALYSIS);
        assertThat(testFraudInvestigationReport.getConclusion()).isEqualTo(UPDATED_CONCLUSION);
        assertThat(testFraudInvestigationReport.getRecommendation()).isEqualTo(DEFAULT_RECOMMENDATION);
        assertThat(testFraudInvestigationReport.getNameOfMembers()).isEqualTo(UPDATED_NAME_OF_MEMBERS);
        assertThat(testFraudInvestigationReport.getSignature()).isEqualTo(UPDATED_SIGNATURE);
        assertThat(testFraudInvestigationReport.getAnnexes()).isEqualTo(UPDATED_ANNEXES);
        assertThat(testFraudInvestigationReport.getAuthor()).isEqualTo(DEFAULT_AUTHOR);
    }

    @Test
    void fullUpdateFraudInvestigationReportWithPatch() throws Exception {
        // Initialize the database
        fraudInvestigationReportRepository.save(fraudInvestigationReport);

        int databaseSizeBeforeUpdate = fraudInvestigationReportRepository.findAll().size();

        // Update the fraudInvestigationReport using partial update
        FraudInvestigationReport partialUpdatedFraudInvestigationReport = new FraudInvestigationReport();
        partialUpdatedFraudInvestigationReport.setId(fraudInvestigationReport.getId());

        partialUpdatedFraudInvestigationReport
            .title(UPDATED_TITLE)
            .executiveSummary(UPDATED_EXECUTIVE_SUMMARY)
            .introduction(UPDATED_INTRODUCTION)
            .objective(UPDATED_OBJECTIVE)
            .scope(UPDATED_SCOPE)
            .limitation(UPDATED_LIMITATION)
            .methodology(UPDATED_METHODOLOGY)
            .findingAndAnalysis(UPDATED_FINDING_AND_ANALYSIS)
            .conclusion(UPDATED_CONCLUSION)
            .recommendation(UPDATED_RECOMMENDATION)
            .nameOfMembers(UPDATED_NAME_OF_MEMBERS)
            .signature(UPDATED_SIGNATURE)
            .annexes(UPDATED_ANNEXES)
            .author(UPDATED_AUTHOR);

        restFraudInvestigationReportMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedFraudInvestigationReport.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedFraudInvestigationReport))
            )
            .andExpect(status().isOk());

        // Validate the FraudInvestigationReport in the database
        List<FraudInvestigationReport> fraudInvestigationReportList = fraudInvestigationReportRepository.findAll();
        assertThat(fraudInvestigationReportList).hasSize(databaseSizeBeforeUpdate);
        FraudInvestigationReport testFraudInvestigationReport = fraudInvestigationReportList.get(fraudInvestigationReportList.size() - 1);
        assertThat(testFraudInvestigationReport.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testFraudInvestigationReport.getExecutiveSummary()).isEqualTo(UPDATED_EXECUTIVE_SUMMARY);
        assertThat(testFraudInvestigationReport.getIntroduction()).isEqualTo(UPDATED_INTRODUCTION);
        assertThat(testFraudInvestigationReport.getObjective()).isEqualTo(UPDATED_OBJECTIVE);
        assertThat(testFraudInvestigationReport.getScope()).isEqualTo(UPDATED_SCOPE);
        assertThat(testFraudInvestigationReport.getLimitation()).isEqualTo(UPDATED_LIMITATION);
        assertThat(testFraudInvestigationReport.getMethodology()).isEqualTo(UPDATED_METHODOLOGY);
        assertThat(testFraudInvestigationReport.getFindingAndAnalysis()).isEqualTo(UPDATED_FINDING_AND_ANALYSIS);
        assertThat(testFraudInvestigationReport.getConclusion()).isEqualTo(UPDATED_CONCLUSION);
        assertThat(testFraudInvestigationReport.getRecommendation()).isEqualTo(UPDATED_RECOMMENDATION);
        assertThat(testFraudInvestigationReport.getNameOfMembers()).isEqualTo(UPDATED_NAME_OF_MEMBERS);
        assertThat(testFraudInvestigationReport.getSignature()).isEqualTo(UPDATED_SIGNATURE);
        assertThat(testFraudInvestigationReport.getAnnexes()).isEqualTo(UPDATED_ANNEXES);
        assertThat(testFraudInvestigationReport.getAuthor()).isEqualTo(UPDATED_AUTHOR);
    }

    @Test
    void patchNonExistingFraudInvestigationReport() throws Exception {
        int databaseSizeBeforeUpdate = fraudInvestigationReportRepository.findAll().size();
        fraudInvestigationReport.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFraudInvestigationReportMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, fraudInvestigationReport.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(fraudInvestigationReport))
            )
            .andExpect(status().isBadRequest());

        // Validate the FraudInvestigationReport in the database
        List<FraudInvestigationReport> fraudInvestigationReportList = fraudInvestigationReportRepository.findAll();
        assertThat(fraudInvestigationReportList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchFraudInvestigationReport() throws Exception {
        int databaseSizeBeforeUpdate = fraudInvestigationReportRepository.findAll().size();
        fraudInvestigationReport.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFraudInvestigationReportMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(fraudInvestigationReport))
            )
            .andExpect(status().isBadRequest());

        // Validate the FraudInvestigationReport in the database
        List<FraudInvestigationReport> fraudInvestigationReportList = fraudInvestigationReportRepository.findAll();
        assertThat(fraudInvestigationReportList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamFraudInvestigationReport() throws Exception {
        int databaseSizeBeforeUpdate = fraudInvestigationReportRepository.findAll().size();
        fraudInvestigationReport.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFraudInvestigationReportMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(fraudInvestigationReport))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the FraudInvestigationReport in the database
        List<FraudInvestigationReport> fraudInvestigationReportList = fraudInvestigationReportRepository.findAll();
        assertThat(fraudInvestigationReportList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteFraudInvestigationReport() throws Exception {
        // Initialize the database
        fraudInvestigationReportRepository.save(fraudInvestigationReport);

        int databaseSizeBeforeDelete = fraudInvestigationReportRepository.findAll().size();

        // Delete the fraudInvestigationReport
        restFraudInvestigationReportMockMvc
            .perform(delete(ENTITY_API_URL_ID, fraudInvestigationReport.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<FraudInvestigationReport> fraudInvestigationReportList = fraudInvestigationReportRepository.findAll();
        assertThat(fraudInvestigationReportList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
