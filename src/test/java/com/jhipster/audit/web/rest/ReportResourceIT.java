package com.jhipster.audit.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.jhipster.audit.IntegrationTest;
import com.jhipster.audit.domain.Report;
import com.jhipster.audit.repository.ReportRepository;
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
 * Integration tests for the {@link ReportResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ReportResourceIT {

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

    private static final String ENTITY_API_URL = "/api/reports";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private MockMvc restReportMockMvc;

    private Report report;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Report createEntity() {
        Report report = new Report()
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
        return report;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Report createUpdatedEntity() {
        Report report = new Report()
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
        return report;
    }

    @BeforeEach
    public void initTest() {
        reportRepository.deleteAll();
        report = createEntity();
    }

    @Test
    void createReport() throws Exception {
        int databaseSizeBeforeCreate = reportRepository.findAll().size();
        // Create the Report
        restReportMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(report)))
            .andExpect(status().isCreated());

        // Validate the Report in the database
        List<Report> reportList = reportRepository.findAll();
        assertThat(reportList).hasSize(databaseSizeBeforeCreate + 1);
        Report testReport = reportList.get(reportList.size() - 1);
        assertThat(testReport.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testReport.getExecutiveSummary()).isEqualTo(DEFAULT_EXECUTIVE_SUMMARY);
        assertThat(testReport.getIntroduction()).isEqualTo(DEFAULT_INTRODUCTION);
        assertThat(testReport.getObjective()).isEqualTo(DEFAULT_OBJECTIVE);
        assertThat(testReport.getScope()).isEqualTo(DEFAULT_SCOPE);
        assertThat(testReport.getLimitation()).isEqualTo(DEFAULT_LIMITATION);
        assertThat(testReport.getMethodology()).isEqualTo(DEFAULT_METHODOLOGY);
        assertThat(testReport.getFindingAndAnalysis()).isEqualTo(DEFAULT_FINDING_AND_ANALYSIS);
        assertThat(testReport.getConclusion()).isEqualTo(DEFAULT_CONCLUSION);
        assertThat(testReport.getRecommendation()).isEqualTo(DEFAULT_RECOMMENDATION);
        assertThat(testReport.getNameOfMembers()).isEqualTo(DEFAULT_NAME_OF_MEMBERS);
        assertThat(testReport.getSignature()).isEqualTo(DEFAULT_SIGNATURE);
        assertThat(testReport.getAnnexes()).isEqualTo(DEFAULT_ANNEXES);
        assertThat(testReport.getAuthor()).isEqualTo(DEFAULT_AUTHOR);
    }

    @Test
    void createReportWithExistingId() throws Exception {
        // Create the Report with an existing ID
        report.setId("existing_id");

        int databaseSizeBeforeCreate = reportRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restReportMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(report)))
            .andExpect(status().isBadRequest());

        // Validate the Report in the database
        List<Report> reportList = reportRepository.findAll();
        assertThat(reportList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllReports() throws Exception {
        // Initialize the database
        reportRepository.save(report);

        // Get all the reportList
        restReportMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(report.getId())))
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
    void getReport() throws Exception {
        // Initialize the database
        reportRepository.save(report);

        // Get the report
        restReportMockMvc
            .perform(get(ENTITY_API_URL_ID, report.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(report.getId()))
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
    void getNonExistingReport() throws Exception {
        // Get the report
        restReportMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    void putExistingReport() throws Exception {
        // Initialize the database
        reportRepository.save(report);

        int databaseSizeBeforeUpdate = reportRepository.findAll().size();

        // Update the report
        Report updatedReport = reportRepository.findById(report.getId()).get();
        updatedReport
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

        restReportMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedReport.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedReport))
            )
            .andExpect(status().isOk());

        // Validate the Report in the database
        List<Report> reportList = reportRepository.findAll();
        assertThat(reportList).hasSize(databaseSizeBeforeUpdate);
        Report testReport = reportList.get(reportList.size() - 1);
        assertThat(testReport.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testReport.getExecutiveSummary()).isEqualTo(UPDATED_EXECUTIVE_SUMMARY);
        assertThat(testReport.getIntroduction()).isEqualTo(UPDATED_INTRODUCTION);
        assertThat(testReport.getObjective()).isEqualTo(UPDATED_OBJECTIVE);
        assertThat(testReport.getScope()).isEqualTo(UPDATED_SCOPE);
        assertThat(testReport.getLimitation()).isEqualTo(UPDATED_LIMITATION);
        assertThat(testReport.getMethodology()).isEqualTo(UPDATED_METHODOLOGY);
        assertThat(testReport.getFindingAndAnalysis()).isEqualTo(UPDATED_FINDING_AND_ANALYSIS);
        assertThat(testReport.getConclusion()).isEqualTo(UPDATED_CONCLUSION);
        assertThat(testReport.getRecommendation()).isEqualTo(UPDATED_RECOMMENDATION);
        assertThat(testReport.getNameOfMembers()).isEqualTo(UPDATED_NAME_OF_MEMBERS);
        assertThat(testReport.getSignature()).isEqualTo(UPDATED_SIGNATURE);
        assertThat(testReport.getAnnexes()).isEqualTo(UPDATED_ANNEXES);
        assertThat(testReport.getAuthor()).isEqualTo(UPDATED_AUTHOR);
    }

    @Test
    void putNonExistingReport() throws Exception {
        int databaseSizeBeforeUpdate = reportRepository.findAll().size();
        report.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restReportMockMvc
            .perform(
                put(ENTITY_API_URL_ID, report.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(report))
            )
            .andExpect(status().isBadRequest());

        // Validate the Report in the database
        List<Report> reportList = reportRepository.findAll();
        assertThat(reportList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchReport() throws Exception {
        int databaseSizeBeforeUpdate = reportRepository.findAll().size();
        report.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restReportMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(report))
            )
            .andExpect(status().isBadRequest());

        // Validate the Report in the database
        List<Report> reportList = reportRepository.findAll();
        assertThat(reportList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamReport() throws Exception {
        int databaseSizeBeforeUpdate = reportRepository.findAll().size();
        report.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restReportMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(report)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Report in the database
        List<Report> reportList = reportRepository.findAll();
        assertThat(reportList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateReportWithPatch() throws Exception {
        // Initialize the database
        reportRepository.save(report);

        int databaseSizeBeforeUpdate = reportRepository.findAll().size();

        // Update the report using partial update
        Report partialUpdatedReport = new Report();
        partialUpdatedReport.setId(report.getId());

        partialUpdatedReport
            .executiveSummary(UPDATED_EXECUTIVE_SUMMARY)
            .introduction(UPDATED_INTRODUCTION)
            .objective(UPDATED_OBJECTIVE)
            .methodology(UPDATED_METHODOLOGY)
            .findingAndAnalysis(UPDATED_FINDING_AND_ANALYSIS)
            .recommendation(UPDATED_RECOMMENDATION)
            .signature(UPDATED_SIGNATURE);

        restReportMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedReport.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedReport))
            )
            .andExpect(status().isOk());

        // Validate the Report in the database
        List<Report> reportList = reportRepository.findAll();
        assertThat(reportList).hasSize(databaseSizeBeforeUpdate);
        Report testReport = reportList.get(reportList.size() - 1);
        assertThat(testReport.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testReport.getExecutiveSummary()).isEqualTo(UPDATED_EXECUTIVE_SUMMARY);
        assertThat(testReport.getIntroduction()).isEqualTo(UPDATED_INTRODUCTION);
        assertThat(testReport.getObjective()).isEqualTo(UPDATED_OBJECTIVE);
        assertThat(testReport.getScope()).isEqualTo(DEFAULT_SCOPE);
        assertThat(testReport.getLimitation()).isEqualTo(DEFAULT_LIMITATION);
        assertThat(testReport.getMethodology()).isEqualTo(UPDATED_METHODOLOGY);
        assertThat(testReport.getFindingAndAnalysis()).isEqualTo(UPDATED_FINDING_AND_ANALYSIS);
        assertThat(testReport.getConclusion()).isEqualTo(DEFAULT_CONCLUSION);
        assertThat(testReport.getRecommendation()).isEqualTo(UPDATED_RECOMMENDATION);
        assertThat(testReport.getNameOfMembers()).isEqualTo(DEFAULT_NAME_OF_MEMBERS);
        assertThat(testReport.getSignature()).isEqualTo(UPDATED_SIGNATURE);
        assertThat(testReport.getAnnexes()).isEqualTo(DEFAULT_ANNEXES);
        assertThat(testReport.getAuthor()).isEqualTo(DEFAULT_AUTHOR);
    }

    @Test
    void fullUpdateReportWithPatch() throws Exception {
        // Initialize the database
        reportRepository.save(report);

        int databaseSizeBeforeUpdate = reportRepository.findAll().size();

        // Update the report using partial update
        Report partialUpdatedReport = new Report();
        partialUpdatedReport.setId(report.getId());

        partialUpdatedReport
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

        restReportMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedReport.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedReport))
            )
            .andExpect(status().isOk());

        // Validate the Report in the database
        List<Report> reportList = reportRepository.findAll();
        assertThat(reportList).hasSize(databaseSizeBeforeUpdate);
        Report testReport = reportList.get(reportList.size() - 1);
        assertThat(testReport.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testReport.getExecutiveSummary()).isEqualTo(UPDATED_EXECUTIVE_SUMMARY);
        assertThat(testReport.getIntroduction()).isEqualTo(UPDATED_INTRODUCTION);
        assertThat(testReport.getObjective()).isEqualTo(UPDATED_OBJECTIVE);
        assertThat(testReport.getScope()).isEqualTo(UPDATED_SCOPE);
        assertThat(testReport.getLimitation()).isEqualTo(UPDATED_LIMITATION);
        assertThat(testReport.getMethodology()).isEqualTo(UPDATED_METHODOLOGY);
        assertThat(testReport.getFindingAndAnalysis()).isEqualTo(UPDATED_FINDING_AND_ANALYSIS);
        assertThat(testReport.getConclusion()).isEqualTo(UPDATED_CONCLUSION);
        assertThat(testReport.getRecommendation()).isEqualTo(UPDATED_RECOMMENDATION);
        assertThat(testReport.getNameOfMembers()).isEqualTo(UPDATED_NAME_OF_MEMBERS);
        assertThat(testReport.getSignature()).isEqualTo(UPDATED_SIGNATURE);
        assertThat(testReport.getAnnexes()).isEqualTo(UPDATED_ANNEXES);
        assertThat(testReport.getAuthor()).isEqualTo(UPDATED_AUTHOR);
    }

    @Test
    void patchNonExistingReport() throws Exception {
        int databaseSizeBeforeUpdate = reportRepository.findAll().size();
        report.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restReportMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, report.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(report))
            )
            .andExpect(status().isBadRequest());

        // Validate the Report in the database
        List<Report> reportList = reportRepository.findAll();
        assertThat(reportList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchReport() throws Exception {
        int databaseSizeBeforeUpdate = reportRepository.findAll().size();
        report.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restReportMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(report))
            )
            .andExpect(status().isBadRequest());

        // Validate the Report in the database
        List<Report> reportList = reportRepository.findAll();
        assertThat(reportList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamReport() throws Exception {
        int databaseSizeBeforeUpdate = reportRepository.findAll().size();
        report.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restReportMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(report)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Report in the database
        List<Report> reportList = reportRepository.findAll();
        assertThat(reportList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteReport() throws Exception {
        // Initialize the database
        reportRepository.save(report);

        int databaseSizeBeforeDelete = reportRepository.findAll().size();

        // Delete the report
        restReportMockMvc
            .perform(delete(ENTITY_API_URL_ID, report.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Report> reportList = reportRepository.findAll();
        assertThat(reportList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
