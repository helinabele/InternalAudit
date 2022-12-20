package com.jhipster.audit.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.jhipster.audit.IntegrationTest;
import com.jhipster.audit.domain.FraudKnowledgeManagement;
import com.jhipster.audit.repository.FraudKnowledgeManagementRepository;
import java.time.LocalDate;
import java.time.ZoneId;
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
 * Integration tests for the {@link FraudKnowledgeManagementResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class FraudKnowledgeManagementResourceIT {

    private static final Integer DEFAULT_REPORT_NUMBER = 1;
    private static final Integer UPDATED_REPORT_NUMBER = 2;

    private static final String DEFAULT_CASE_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_CASE_TITLE = "BBBBBBBBBB";

    private static final String DEFAULT_FRAUD_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_FRAUD_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_UNIT = "AAAAAAAAAA";
    private static final String UPDATED_UNIT = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_INCIDENT_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_INCIDENT_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_REPORT_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_REPORT_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_INTERNAL_EMPLOYEE = "AAAAAAAAAA";
    private static final String UPDATED_INTERNAL_EMPLOYEE = "BBBBBBBBBB";

    private static final String DEFAULT_EXTERNAL_CUSTOMER = "AAAAAAAAAA";
    private static final String UPDATED_EXTERNAL_CUSTOMER = "BBBBBBBBBB";

    private static final Float DEFAULT_FINANCIAL_LOSS_AMOUNT = 1F;
    private static final Float UPDATED_FINANCIAL_LOSS_AMOUNT = 2F;

    private static final String DEFAULT_CAUSE_FOR_AN_INCIDENT = "AAAAAAAAAA";
    private static final String UPDATED_CAUSE_FOR_AN_INCIDENT = "BBBBBBBBBB";

    private static final String DEFAULT_EFFECT = "AAAAAAAAAA";
    private static final String UPDATED_EFFECT = "BBBBBBBBBB";

    private static final String DEFAULT_RECOMMENDATIONS_DRAWN = "AAAAAAAAAA";
    private static final String UPDATED_RECOMMENDATIONS_DRAWN = "BBBBBBBBBB";

    private static final String DEFAULT_POSITION_JG = "AAAAAAAAAA";
    private static final String UPDATED_POSITION_JG = "BBBBBBBBBB";

    private static final String DEFAULT_NAME_ID_NO = "AAAAAAAAAA";
    private static final String UPDATED_NAME_ID_NO = "BBBBBBBBBB";

    private static final String DEFAULT_ACTION_INVOLVED = "AAAAAAAAAA";
    private static final String UPDATED_ACTION_INVOLVED = "BBBBBBBBBB";

    private static final String DEFAULT_NG_SCREENER_REPORT = "AAAAAAAAAA";
    private static final String UPDATED_NG_SCREENER_REPORT = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS_AFTER_REPORTING = "AAAAAAAAAA";
    private static final String UPDATED_STATUS_AFTER_REPORTING = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/fraud-knowledge-managements";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private FraudKnowledgeManagementRepository fraudKnowledgeManagementRepository;

    @Autowired
    private MockMvc restFraudKnowledgeManagementMockMvc;

    private FraudKnowledgeManagement fraudKnowledgeManagement;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FraudKnowledgeManagement createEntity() {
        FraudKnowledgeManagement fraudKnowledgeManagement = new FraudKnowledgeManagement()
            .reportNumber(DEFAULT_REPORT_NUMBER)
            .caseTitle(DEFAULT_CASE_TITLE)
            .fraudType(DEFAULT_FRAUD_TYPE)
            .unit(DEFAULT_UNIT)
            .incidentDate(DEFAULT_INCIDENT_DATE)
            .reportDate(DEFAULT_REPORT_DATE)
            .internalEmployee(DEFAULT_INTERNAL_EMPLOYEE)
            .externalCustomer(DEFAULT_EXTERNAL_CUSTOMER)
            .financialLossAmount(DEFAULT_FINANCIAL_LOSS_AMOUNT)
            .causeForAnIncident(DEFAULT_CAUSE_FOR_AN_INCIDENT)
            .effect(DEFAULT_EFFECT)
            .recommendationsDrawn(DEFAULT_RECOMMENDATIONS_DRAWN)
            .positionJG(DEFAULT_POSITION_JG)
            .nameIdNo(DEFAULT_NAME_ID_NO)
            .actionInvolved(DEFAULT_ACTION_INVOLVED)
            .ngScreenerReport(DEFAULT_NG_SCREENER_REPORT)
            .statusAfterReporting(DEFAULT_STATUS_AFTER_REPORTING);
        return fraudKnowledgeManagement;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FraudKnowledgeManagement createUpdatedEntity() {
        FraudKnowledgeManagement fraudKnowledgeManagement = new FraudKnowledgeManagement()
            .reportNumber(UPDATED_REPORT_NUMBER)
            .caseTitle(UPDATED_CASE_TITLE)
            .fraudType(UPDATED_FRAUD_TYPE)
            .unit(UPDATED_UNIT)
            .incidentDate(UPDATED_INCIDENT_DATE)
            .reportDate(UPDATED_REPORT_DATE)
            .internalEmployee(UPDATED_INTERNAL_EMPLOYEE)
            .externalCustomer(UPDATED_EXTERNAL_CUSTOMER)
            .financialLossAmount(UPDATED_FINANCIAL_LOSS_AMOUNT)
            .causeForAnIncident(UPDATED_CAUSE_FOR_AN_INCIDENT)
            .effect(UPDATED_EFFECT)
            .recommendationsDrawn(UPDATED_RECOMMENDATIONS_DRAWN)
            .positionJG(UPDATED_POSITION_JG)
            .nameIdNo(UPDATED_NAME_ID_NO)
            .actionInvolved(UPDATED_ACTION_INVOLVED)
            .ngScreenerReport(UPDATED_NG_SCREENER_REPORT)
            .statusAfterReporting(UPDATED_STATUS_AFTER_REPORTING);
        return fraudKnowledgeManagement;
    }

    @BeforeEach
    public void initTest() {
        fraudKnowledgeManagementRepository.deleteAll();
        fraudKnowledgeManagement = createEntity();
    }

    @Test
    void createFraudKnowledgeManagement() throws Exception {
        int databaseSizeBeforeCreate = fraudKnowledgeManagementRepository.findAll().size();
        // Create the FraudKnowledgeManagement
        restFraudKnowledgeManagementMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(fraudKnowledgeManagement))
            )
            .andExpect(status().isCreated());

        // Validate the FraudKnowledgeManagement in the database
        List<FraudKnowledgeManagement> fraudKnowledgeManagementList = fraudKnowledgeManagementRepository.findAll();
        assertThat(fraudKnowledgeManagementList).hasSize(databaseSizeBeforeCreate + 1);
        FraudKnowledgeManagement testFraudKnowledgeManagement = fraudKnowledgeManagementList.get(fraudKnowledgeManagementList.size() - 1);
        assertThat(testFraudKnowledgeManagement.getReportNumber()).isEqualTo(DEFAULT_REPORT_NUMBER);
        assertThat(testFraudKnowledgeManagement.getCaseTitle()).isEqualTo(DEFAULT_CASE_TITLE);
        assertThat(testFraudKnowledgeManagement.getFraudType()).isEqualTo(DEFAULT_FRAUD_TYPE);
        assertThat(testFraudKnowledgeManagement.getUnit()).isEqualTo(DEFAULT_UNIT);
        assertThat(testFraudKnowledgeManagement.getIncidentDate()).isEqualTo(DEFAULT_INCIDENT_DATE);
        assertThat(testFraudKnowledgeManagement.getReportDate()).isEqualTo(DEFAULT_REPORT_DATE);
        assertThat(testFraudKnowledgeManagement.getInternalEmployee()).isEqualTo(DEFAULT_INTERNAL_EMPLOYEE);
        assertThat(testFraudKnowledgeManagement.getExternalCustomer()).isEqualTo(DEFAULT_EXTERNAL_CUSTOMER);
        assertThat(testFraudKnowledgeManagement.getFinancialLossAmount()).isEqualTo(DEFAULT_FINANCIAL_LOSS_AMOUNT);
        assertThat(testFraudKnowledgeManagement.getCauseForAnIncident()).isEqualTo(DEFAULT_CAUSE_FOR_AN_INCIDENT);
        assertThat(testFraudKnowledgeManagement.getEffect()).isEqualTo(DEFAULT_EFFECT);
        assertThat(testFraudKnowledgeManagement.getRecommendationsDrawn()).isEqualTo(DEFAULT_RECOMMENDATIONS_DRAWN);
        assertThat(testFraudKnowledgeManagement.getPositionJG()).isEqualTo(DEFAULT_POSITION_JG);
        assertThat(testFraudKnowledgeManagement.getNameIdNo()).isEqualTo(DEFAULT_NAME_ID_NO);
        assertThat(testFraudKnowledgeManagement.getActionInvolved()).isEqualTo(DEFAULT_ACTION_INVOLVED);
        assertThat(testFraudKnowledgeManagement.getNgScreenerReport()).isEqualTo(DEFAULT_NG_SCREENER_REPORT);
        assertThat(testFraudKnowledgeManagement.getStatusAfterReporting()).isEqualTo(DEFAULT_STATUS_AFTER_REPORTING);
    }

    @Test
    void createFraudKnowledgeManagementWithExistingId() throws Exception {
        // Create the FraudKnowledgeManagement with an existing ID
        fraudKnowledgeManagement.setId("existing_id");

        int databaseSizeBeforeCreate = fraudKnowledgeManagementRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restFraudKnowledgeManagementMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(fraudKnowledgeManagement))
            )
            .andExpect(status().isBadRequest());

        // Validate the FraudKnowledgeManagement in the database
        List<FraudKnowledgeManagement> fraudKnowledgeManagementList = fraudKnowledgeManagementRepository.findAll();
        assertThat(fraudKnowledgeManagementList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllFraudKnowledgeManagements() throws Exception {
        // Initialize the database
        fraudKnowledgeManagementRepository.save(fraudKnowledgeManagement);

        // Get all the fraudKnowledgeManagementList
        restFraudKnowledgeManagementMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(fraudKnowledgeManagement.getId())))
            .andExpect(jsonPath("$.[*].reportNumber").value(hasItem(DEFAULT_REPORT_NUMBER)))
            .andExpect(jsonPath("$.[*].caseTitle").value(hasItem(DEFAULT_CASE_TITLE)))
            .andExpect(jsonPath("$.[*].fraudType").value(hasItem(DEFAULT_FRAUD_TYPE)))
            .andExpect(jsonPath("$.[*].unit").value(hasItem(DEFAULT_UNIT)))
            .andExpect(jsonPath("$.[*].incidentDate").value(hasItem(DEFAULT_INCIDENT_DATE.toString())))
            .andExpect(jsonPath("$.[*].reportDate").value(hasItem(DEFAULT_REPORT_DATE.toString())))
            .andExpect(jsonPath("$.[*].internalEmployee").value(hasItem(DEFAULT_INTERNAL_EMPLOYEE)))
            .andExpect(jsonPath("$.[*].externalCustomer").value(hasItem(DEFAULT_EXTERNAL_CUSTOMER)))
            .andExpect(jsonPath("$.[*].financialLossAmount").value(hasItem(DEFAULT_FINANCIAL_LOSS_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].causeForAnIncident").value(hasItem(DEFAULT_CAUSE_FOR_AN_INCIDENT)))
            .andExpect(jsonPath("$.[*].effect").value(hasItem(DEFAULT_EFFECT)))
            .andExpect(jsonPath("$.[*].recommendationsDrawn").value(hasItem(DEFAULT_RECOMMENDATIONS_DRAWN)))
            .andExpect(jsonPath("$.[*].positionJG").value(hasItem(DEFAULT_POSITION_JG)))
            .andExpect(jsonPath("$.[*].nameIdNo").value(hasItem(DEFAULT_NAME_ID_NO)))
            .andExpect(jsonPath("$.[*].actionInvolved").value(hasItem(DEFAULT_ACTION_INVOLVED)))
            .andExpect(jsonPath("$.[*].ngScreenerReport").value(hasItem(DEFAULT_NG_SCREENER_REPORT)))
            .andExpect(jsonPath("$.[*].statusAfterReporting").value(hasItem(DEFAULT_STATUS_AFTER_REPORTING)));
    }

    @Test
    void getFraudKnowledgeManagement() throws Exception {
        // Initialize the database
        fraudKnowledgeManagementRepository.save(fraudKnowledgeManagement);

        // Get the fraudKnowledgeManagement
        restFraudKnowledgeManagementMockMvc
            .perform(get(ENTITY_API_URL_ID, fraudKnowledgeManagement.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(fraudKnowledgeManagement.getId()))
            .andExpect(jsonPath("$.reportNumber").value(DEFAULT_REPORT_NUMBER))
            .andExpect(jsonPath("$.caseTitle").value(DEFAULT_CASE_TITLE))
            .andExpect(jsonPath("$.fraudType").value(DEFAULT_FRAUD_TYPE))
            .andExpect(jsonPath("$.unit").value(DEFAULT_UNIT))
            .andExpect(jsonPath("$.incidentDate").value(DEFAULT_INCIDENT_DATE.toString()))
            .andExpect(jsonPath("$.reportDate").value(DEFAULT_REPORT_DATE.toString()))
            .andExpect(jsonPath("$.internalEmployee").value(DEFAULT_INTERNAL_EMPLOYEE))
            .andExpect(jsonPath("$.externalCustomer").value(DEFAULT_EXTERNAL_CUSTOMER))
            .andExpect(jsonPath("$.financialLossAmount").value(DEFAULT_FINANCIAL_LOSS_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.causeForAnIncident").value(DEFAULT_CAUSE_FOR_AN_INCIDENT))
            .andExpect(jsonPath("$.effect").value(DEFAULT_EFFECT))
            .andExpect(jsonPath("$.recommendationsDrawn").value(DEFAULT_RECOMMENDATIONS_DRAWN))
            .andExpect(jsonPath("$.positionJG").value(DEFAULT_POSITION_JG))
            .andExpect(jsonPath("$.nameIdNo").value(DEFAULT_NAME_ID_NO))
            .andExpect(jsonPath("$.actionInvolved").value(DEFAULT_ACTION_INVOLVED))
            .andExpect(jsonPath("$.ngScreenerReport").value(DEFAULT_NG_SCREENER_REPORT))
            .andExpect(jsonPath("$.statusAfterReporting").value(DEFAULT_STATUS_AFTER_REPORTING));
    }

    @Test
    void getNonExistingFraudKnowledgeManagement() throws Exception {
        // Get the fraudKnowledgeManagement
        restFraudKnowledgeManagementMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    void putExistingFraudKnowledgeManagement() throws Exception {
        // Initialize the database
        fraudKnowledgeManagementRepository.save(fraudKnowledgeManagement);

        int databaseSizeBeforeUpdate = fraudKnowledgeManagementRepository.findAll().size();

        // Update the fraudKnowledgeManagement
        FraudKnowledgeManagement updatedFraudKnowledgeManagement = fraudKnowledgeManagementRepository
            .findById(fraudKnowledgeManagement.getId())
            .get();
        updatedFraudKnowledgeManagement
            .reportNumber(UPDATED_REPORT_NUMBER)
            .caseTitle(UPDATED_CASE_TITLE)
            .fraudType(UPDATED_FRAUD_TYPE)
            .unit(UPDATED_UNIT)
            .incidentDate(UPDATED_INCIDENT_DATE)
            .reportDate(UPDATED_REPORT_DATE)
            .internalEmployee(UPDATED_INTERNAL_EMPLOYEE)
            .externalCustomer(UPDATED_EXTERNAL_CUSTOMER)
            .financialLossAmount(UPDATED_FINANCIAL_LOSS_AMOUNT)
            .causeForAnIncident(UPDATED_CAUSE_FOR_AN_INCIDENT)
            .effect(UPDATED_EFFECT)
            .recommendationsDrawn(UPDATED_RECOMMENDATIONS_DRAWN)
            .positionJG(UPDATED_POSITION_JG)
            .nameIdNo(UPDATED_NAME_ID_NO)
            .actionInvolved(UPDATED_ACTION_INVOLVED)
            .ngScreenerReport(UPDATED_NG_SCREENER_REPORT)
            .statusAfterReporting(UPDATED_STATUS_AFTER_REPORTING);

        restFraudKnowledgeManagementMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedFraudKnowledgeManagement.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedFraudKnowledgeManagement))
            )
            .andExpect(status().isOk());

        // Validate the FraudKnowledgeManagement in the database
        List<FraudKnowledgeManagement> fraudKnowledgeManagementList = fraudKnowledgeManagementRepository.findAll();
        assertThat(fraudKnowledgeManagementList).hasSize(databaseSizeBeforeUpdate);
        FraudKnowledgeManagement testFraudKnowledgeManagement = fraudKnowledgeManagementList.get(fraudKnowledgeManagementList.size() - 1);
        assertThat(testFraudKnowledgeManagement.getReportNumber()).isEqualTo(UPDATED_REPORT_NUMBER);
        assertThat(testFraudKnowledgeManagement.getCaseTitle()).isEqualTo(UPDATED_CASE_TITLE);
        assertThat(testFraudKnowledgeManagement.getFraudType()).isEqualTo(UPDATED_FRAUD_TYPE);
        assertThat(testFraudKnowledgeManagement.getUnit()).isEqualTo(UPDATED_UNIT);
        assertThat(testFraudKnowledgeManagement.getIncidentDate()).isEqualTo(UPDATED_INCIDENT_DATE);
        assertThat(testFraudKnowledgeManagement.getReportDate()).isEqualTo(UPDATED_REPORT_DATE);
        assertThat(testFraudKnowledgeManagement.getInternalEmployee()).isEqualTo(UPDATED_INTERNAL_EMPLOYEE);
        assertThat(testFraudKnowledgeManagement.getExternalCustomer()).isEqualTo(UPDATED_EXTERNAL_CUSTOMER);
        assertThat(testFraudKnowledgeManagement.getFinancialLossAmount()).isEqualTo(UPDATED_FINANCIAL_LOSS_AMOUNT);
        assertThat(testFraudKnowledgeManagement.getCauseForAnIncident()).isEqualTo(UPDATED_CAUSE_FOR_AN_INCIDENT);
        assertThat(testFraudKnowledgeManagement.getEffect()).isEqualTo(UPDATED_EFFECT);
        assertThat(testFraudKnowledgeManagement.getRecommendationsDrawn()).isEqualTo(UPDATED_RECOMMENDATIONS_DRAWN);
        assertThat(testFraudKnowledgeManagement.getPositionJG()).isEqualTo(UPDATED_POSITION_JG);
        assertThat(testFraudKnowledgeManagement.getNameIdNo()).isEqualTo(UPDATED_NAME_ID_NO);
        assertThat(testFraudKnowledgeManagement.getActionInvolved()).isEqualTo(UPDATED_ACTION_INVOLVED);
        assertThat(testFraudKnowledgeManagement.getNgScreenerReport()).isEqualTo(UPDATED_NG_SCREENER_REPORT);
        assertThat(testFraudKnowledgeManagement.getStatusAfterReporting()).isEqualTo(UPDATED_STATUS_AFTER_REPORTING);
    }

    @Test
    void putNonExistingFraudKnowledgeManagement() throws Exception {
        int databaseSizeBeforeUpdate = fraudKnowledgeManagementRepository.findAll().size();
        fraudKnowledgeManagement.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFraudKnowledgeManagementMockMvc
            .perform(
                put(ENTITY_API_URL_ID, fraudKnowledgeManagement.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(fraudKnowledgeManagement))
            )
            .andExpect(status().isBadRequest());

        // Validate the FraudKnowledgeManagement in the database
        List<FraudKnowledgeManagement> fraudKnowledgeManagementList = fraudKnowledgeManagementRepository.findAll();
        assertThat(fraudKnowledgeManagementList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchFraudKnowledgeManagement() throws Exception {
        int databaseSizeBeforeUpdate = fraudKnowledgeManagementRepository.findAll().size();
        fraudKnowledgeManagement.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFraudKnowledgeManagementMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(fraudKnowledgeManagement))
            )
            .andExpect(status().isBadRequest());

        // Validate the FraudKnowledgeManagement in the database
        List<FraudKnowledgeManagement> fraudKnowledgeManagementList = fraudKnowledgeManagementRepository.findAll();
        assertThat(fraudKnowledgeManagementList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamFraudKnowledgeManagement() throws Exception {
        int databaseSizeBeforeUpdate = fraudKnowledgeManagementRepository.findAll().size();
        fraudKnowledgeManagement.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFraudKnowledgeManagementMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(fraudKnowledgeManagement))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the FraudKnowledgeManagement in the database
        List<FraudKnowledgeManagement> fraudKnowledgeManagementList = fraudKnowledgeManagementRepository.findAll();
        assertThat(fraudKnowledgeManagementList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateFraudKnowledgeManagementWithPatch() throws Exception {
        // Initialize the database
        fraudKnowledgeManagementRepository.save(fraudKnowledgeManagement);

        int databaseSizeBeforeUpdate = fraudKnowledgeManagementRepository.findAll().size();

        // Update the fraudKnowledgeManagement using partial update
        FraudKnowledgeManagement partialUpdatedFraudKnowledgeManagement = new FraudKnowledgeManagement();
        partialUpdatedFraudKnowledgeManagement.setId(fraudKnowledgeManagement.getId());

        partialUpdatedFraudKnowledgeManagement
            .caseTitle(UPDATED_CASE_TITLE)
            .fraudType(UPDATED_FRAUD_TYPE)
            .unit(UPDATED_UNIT)
            .incidentDate(UPDATED_INCIDENT_DATE)
            .externalCustomer(UPDATED_EXTERNAL_CUSTOMER)
            .financialLossAmount(UPDATED_FINANCIAL_LOSS_AMOUNT)
            .causeForAnIncident(UPDATED_CAUSE_FOR_AN_INCIDENT)
            .effect(UPDATED_EFFECT)
            .nameIdNo(UPDATED_NAME_ID_NO)
            .ngScreenerReport(UPDATED_NG_SCREENER_REPORT);

        restFraudKnowledgeManagementMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedFraudKnowledgeManagement.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedFraudKnowledgeManagement))
            )
            .andExpect(status().isOk());

        // Validate the FraudKnowledgeManagement in the database
        List<FraudKnowledgeManagement> fraudKnowledgeManagementList = fraudKnowledgeManagementRepository.findAll();
        assertThat(fraudKnowledgeManagementList).hasSize(databaseSizeBeforeUpdate);
        FraudKnowledgeManagement testFraudKnowledgeManagement = fraudKnowledgeManagementList.get(fraudKnowledgeManagementList.size() - 1);
        assertThat(testFraudKnowledgeManagement.getReportNumber()).isEqualTo(DEFAULT_REPORT_NUMBER);
        assertThat(testFraudKnowledgeManagement.getCaseTitle()).isEqualTo(UPDATED_CASE_TITLE);
        assertThat(testFraudKnowledgeManagement.getFraudType()).isEqualTo(UPDATED_FRAUD_TYPE);
        assertThat(testFraudKnowledgeManagement.getUnit()).isEqualTo(UPDATED_UNIT);
        assertThat(testFraudKnowledgeManagement.getIncidentDate()).isEqualTo(UPDATED_INCIDENT_DATE);
        assertThat(testFraudKnowledgeManagement.getReportDate()).isEqualTo(DEFAULT_REPORT_DATE);
        assertThat(testFraudKnowledgeManagement.getInternalEmployee()).isEqualTo(DEFAULT_INTERNAL_EMPLOYEE);
        assertThat(testFraudKnowledgeManagement.getExternalCustomer()).isEqualTo(UPDATED_EXTERNAL_CUSTOMER);
        assertThat(testFraudKnowledgeManagement.getFinancialLossAmount()).isEqualTo(UPDATED_FINANCIAL_LOSS_AMOUNT);
        assertThat(testFraudKnowledgeManagement.getCauseForAnIncident()).isEqualTo(UPDATED_CAUSE_FOR_AN_INCIDENT);
        assertThat(testFraudKnowledgeManagement.getEffect()).isEqualTo(UPDATED_EFFECT);
        assertThat(testFraudKnowledgeManagement.getRecommendationsDrawn()).isEqualTo(DEFAULT_RECOMMENDATIONS_DRAWN);
        assertThat(testFraudKnowledgeManagement.getPositionJG()).isEqualTo(DEFAULT_POSITION_JG);
        assertThat(testFraudKnowledgeManagement.getNameIdNo()).isEqualTo(UPDATED_NAME_ID_NO);
        assertThat(testFraudKnowledgeManagement.getActionInvolved()).isEqualTo(DEFAULT_ACTION_INVOLVED);
        assertThat(testFraudKnowledgeManagement.getNgScreenerReport()).isEqualTo(UPDATED_NG_SCREENER_REPORT);
        assertThat(testFraudKnowledgeManagement.getStatusAfterReporting()).isEqualTo(DEFAULT_STATUS_AFTER_REPORTING);
    }

    @Test
    void fullUpdateFraudKnowledgeManagementWithPatch() throws Exception {
        // Initialize the database
        fraudKnowledgeManagementRepository.save(fraudKnowledgeManagement);

        int databaseSizeBeforeUpdate = fraudKnowledgeManagementRepository.findAll().size();

        // Update the fraudKnowledgeManagement using partial update
        FraudKnowledgeManagement partialUpdatedFraudKnowledgeManagement = new FraudKnowledgeManagement();
        partialUpdatedFraudKnowledgeManagement.setId(fraudKnowledgeManagement.getId());

        partialUpdatedFraudKnowledgeManagement
            .reportNumber(UPDATED_REPORT_NUMBER)
            .caseTitle(UPDATED_CASE_TITLE)
            .fraudType(UPDATED_FRAUD_TYPE)
            .unit(UPDATED_UNIT)
            .incidentDate(UPDATED_INCIDENT_DATE)
            .reportDate(UPDATED_REPORT_DATE)
            .internalEmployee(UPDATED_INTERNAL_EMPLOYEE)
            .externalCustomer(UPDATED_EXTERNAL_CUSTOMER)
            .financialLossAmount(UPDATED_FINANCIAL_LOSS_AMOUNT)
            .causeForAnIncident(UPDATED_CAUSE_FOR_AN_INCIDENT)
            .effect(UPDATED_EFFECT)
            .recommendationsDrawn(UPDATED_RECOMMENDATIONS_DRAWN)
            .positionJG(UPDATED_POSITION_JG)
            .nameIdNo(UPDATED_NAME_ID_NO)
            .actionInvolved(UPDATED_ACTION_INVOLVED)
            .ngScreenerReport(UPDATED_NG_SCREENER_REPORT)
            .statusAfterReporting(UPDATED_STATUS_AFTER_REPORTING);

        restFraudKnowledgeManagementMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedFraudKnowledgeManagement.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedFraudKnowledgeManagement))
            )
            .andExpect(status().isOk());

        // Validate the FraudKnowledgeManagement in the database
        List<FraudKnowledgeManagement> fraudKnowledgeManagementList = fraudKnowledgeManagementRepository.findAll();
        assertThat(fraudKnowledgeManagementList).hasSize(databaseSizeBeforeUpdate);
        FraudKnowledgeManagement testFraudKnowledgeManagement = fraudKnowledgeManagementList.get(fraudKnowledgeManagementList.size() - 1);
        assertThat(testFraudKnowledgeManagement.getReportNumber()).isEqualTo(UPDATED_REPORT_NUMBER);
        assertThat(testFraudKnowledgeManagement.getCaseTitle()).isEqualTo(UPDATED_CASE_TITLE);
        assertThat(testFraudKnowledgeManagement.getFraudType()).isEqualTo(UPDATED_FRAUD_TYPE);
        assertThat(testFraudKnowledgeManagement.getUnit()).isEqualTo(UPDATED_UNIT);
        assertThat(testFraudKnowledgeManagement.getIncidentDate()).isEqualTo(UPDATED_INCIDENT_DATE);
        assertThat(testFraudKnowledgeManagement.getReportDate()).isEqualTo(UPDATED_REPORT_DATE);
        assertThat(testFraudKnowledgeManagement.getInternalEmployee()).isEqualTo(UPDATED_INTERNAL_EMPLOYEE);
        assertThat(testFraudKnowledgeManagement.getExternalCustomer()).isEqualTo(UPDATED_EXTERNAL_CUSTOMER);
        assertThat(testFraudKnowledgeManagement.getFinancialLossAmount()).isEqualTo(UPDATED_FINANCIAL_LOSS_AMOUNT);
        assertThat(testFraudKnowledgeManagement.getCauseForAnIncident()).isEqualTo(UPDATED_CAUSE_FOR_AN_INCIDENT);
        assertThat(testFraudKnowledgeManagement.getEffect()).isEqualTo(UPDATED_EFFECT);
        assertThat(testFraudKnowledgeManagement.getRecommendationsDrawn()).isEqualTo(UPDATED_RECOMMENDATIONS_DRAWN);
        assertThat(testFraudKnowledgeManagement.getPositionJG()).isEqualTo(UPDATED_POSITION_JG);
        assertThat(testFraudKnowledgeManagement.getNameIdNo()).isEqualTo(UPDATED_NAME_ID_NO);
        assertThat(testFraudKnowledgeManagement.getActionInvolved()).isEqualTo(UPDATED_ACTION_INVOLVED);
        assertThat(testFraudKnowledgeManagement.getNgScreenerReport()).isEqualTo(UPDATED_NG_SCREENER_REPORT);
        assertThat(testFraudKnowledgeManagement.getStatusAfterReporting()).isEqualTo(UPDATED_STATUS_AFTER_REPORTING);
    }

    @Test
    void patchNonExistingFraudKnowledgeManagement() throws Exception {
        int databaseSizeBeforeUpdate = fraudKnowledgeManagementRepository.findAll().size();
        fraudKnowledgeManagement.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFraudKnowledgeManagementMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, fraudKnowledgeManagement.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(fraudKnowledgeManagement))
            )
            .andExpect(status().isBadRequest());

        // Validate the FraudKnowledgeManagement in the database
        List<FraudKnowledgeManagement> fraudKnowledgeManagementList = fraudKnowledgeManagementRepository.findAll();
        assertThat(fraudKnowledgeManagementList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchFraudKnowledgeManagement() throws Exception {
        int databaseSizeBeforeUpdate = fraudKnowledgeManagementRepository.findAll().size();
        fraudKnowledgeManagement.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFraudKnowledgeManagementMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(fraudKnowledgeManagement))
            )
            .andExpect(status().isBadRequest());

        // Validate the FraudKnowledgeManagement in the database
        List<FraudKnowledgeManagement> fraudKnowledgeManagementList = fraudKnowledgeManagementRepository.findAll();
        assertThat(fraudKnowledgeManagementList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamFraudKnowledgeManagement() throws Exception {
        int databaseSizeBeforeUpdate = fraudKnowledgeManagementRepository.findAll().size();
        fraudKnowledgeManagement.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFraudKnowledgeManagementMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(fraudKnowledgeManagement))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the FraudKnowledgeManagement in the database
        List<FraudKnowledgeManagement> fraudKnowledgeManagementList = fraudKnowledgeManagementRepository.findAll();
        assertThat(fraudKnowledgeManagementList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteFraudKnowledgeManagement() throws Exception {
        // Initialize the database
        fraudKnowledgeManagementRepository.save(fraudKnowledgeManagement);

        int databaseSizeBeforeDelete = fraudKnowledgeManagementRepository.findAll().size();

        // Delete the fraudKnowledgeManagement
        restFraudKnowledgeManagementMockMvc
            .perform(delete(ENTITY_API_URL_ID, fraudKnowledgeManagement.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<FraudKnowledgeManagement> fraudKnowledgeManagementList = fraudKnowledgeManagementRepository.findAll();
        assertThat(fraudKnowledgeManagementList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
