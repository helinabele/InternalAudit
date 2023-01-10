package com.jhipster.audit.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.jhipster.audit.IntegrationTest;
import com.jhipster.audit.domain.AssignTask;
import com.jhipster.audit.repository.AssignTaskRepository;
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
 * Integration tests for the {@link AssignTaskResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class AssignTaskResourceIT {

    private static final String DEFAULT_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_TITLE = "BBBBBBBBBB";

    private static final String DEFAULT_ASSIGNED_PERSON = "AAAAAAAAAA";
    private static final String UPDATED_ASSIGNED_PERSON = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_TASK_ASSIGNMENT_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_TASK_ASSIGNMENT_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_TASK_START_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_TASK_START_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_TASK_DUE_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_TASK_DUE_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String ENTITY_API_URL = "/api/assign-tasks";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private AssignTaskRepository assignTaskRepository;

    @Autowired
    private MockMvc restAssignTaskMockMvc;

    private AssignTask assignTask;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AssignTask createEntity() {
        AssignTask assignTask = new AssignTask()
            .title(DEFAULT_TITLE)
            .assignedPerson(DEFAULT_ASSIGNED_PERSON)
            .taskAssignmentDate(DEFAULT_TASK_ASSIGNMENT_DATE)
            .taskStartDate(DEFAULT_TASK_START_DATE)
            .taskDueDate(DEFAULT_TASK_DUE_DATE);
        return assignTask;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AssignTask createUpdatedEntity() {
        AssignTask assignTask = new AssignTask()
            .title(UPDATED_TITLE)
            .assignedPerson(UPDATED_ASSIGNED_PERSON)
            .taskAssignmentDate(UPDATED_TASK_ASSIGNMENT_DATE)
            .taskStartDate(UPDATED_TASK_START_DATE)
            .taskDueDate(UPDATED_TASK_DUE_DATE);
        return assignTask;
    }

    @BeforeEach
    public void initTest() {
        assignTaskRepository.deleteAll();
        assignTask = createEntity();
    }

    @Test
    void createAssignTask() throws Exception {
        int databaseSizeBeforeCreate = assignTaskRepository.findAll().size();
        // Create the AssignTask
        restAssignTaskMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(assignTask)))
            .andExpect(status().isCreated());

        // Validate the AssignTask in the database
        List<AssignTask> assignTaskList = assignTaskRepository.findAll();
        assertThat(assignTaskList).hasSize(databaseSizeBeforeCreate + 1);
        AssignTask testAssignTask = assignTaskList.get(assignTaskList.size() - 1);
        assertThat(testAssignTask.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testAssignTask.getAssignedPerson()).isEqualTo(DEFAULT_ASSIGNED_PERSON);
        assertThat(testAssignTask.getTaskAssignmentDate()).isEqualTo(DEFAULT_TASK_ASSIGNMENT_DATE);
        assertThat(testAssignTask.getTaskStartDate()).isEqualTo(DEFAULT_TASK_START_DATE);
        assertThat(testAssignTask.getTaskDueDate()).isEqualTo(DEFAULT_TASK_DUE_DATE);
    }

    @Test
    void createAssignTaskWithExistingId() throws Exception {
        // Create the AssignTask with an existing ID
        assignTask.setId("existing_id");

        int databaseSizeBeforeCreate = assignTaskRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restAssignTaskMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(assignTask)))
            .andExpect(status().isBadRequest());

        // Validate the AssignTask in the database
        List<AssignTask> assignTaskList = assignTaskRepository.findAll();
        assertThat(assignTaskList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void checkTitleIsRequired() throws Exception {
        int databaseSizeBeforeTest = assignTaskRepository.findAll().size();
        // set the field null
        assignTask.setTitle(null);

        // Create the AssignTask, which fails.

        restAssignTaskMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(assignTask)))
            .andExpect(status().isBadRequest());

        List<AssignTask> assignTaskList = assignTaskRepository.findAll();
        assertThat(assignTaskList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void getAllAssignTasks() throws Exception {
        // Initialize the database
        assignTaskRepository.save(assignTask);

        // Get all the assignTaskList
        restAssignTaskMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(assignTask.getId())))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE)))
            .andExpect(jsonPath("$.[*].assignedPerson").value(hasItem(DEFAULT_ASSIGNED_PERSON)))
            .andExpect(jsonPath("$.[*].taskAssignmentDate").value(hasItem(DEFAULT_TASK_ASSIGNMENT_DATE.toString())))
            .andExpect(jsonPath("$.[*].taskStartDate").value(hasItem(DEFAULT_TASK_START_DATE.toString())))
            .andExpect(jsonPath("$.[*].taskDueDate").value(hasItem(DEFAULT_TASK_DUE_DATE.toString())));
    }

    @Test
    void getAssignTask() throws Exception {
        // Initialize the database
        assignTaskRepository.save(assignTask);

        // Get the assignTask
        restAssignTaskMockMvc
            .perform(get(ENTITY_API_URL_ID, assignTask.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(assignTask.getId()))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE))
            .andExpect(jsonPath("$.assignedPerson").value(DEFAULT_ASSIGNED_PERSON))
            .andExpect(jsonPath("$.taskAssignmentDate").value(DEFAULT_TASK_ASSIGNMENT_DATE.toString()))
            .andExpect(jsonPath("$.taskStartDate").value(DEFAULT_TASK_START_DATE.toString()))
            .andExpect(jsonPath("$.taskDueDate").value(DEFAULT_TASK_DUE_DATE.toString()));
    }

    @Test
    void getNonExistingAssignTask() throws Exception {
        // Get the assignTask
        restAssignTaskMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    void putExistingAssignTask() throws Exception {
        // Initialize the database
        assignTaskRepository.save(assignTask);

        int databaseSizeBeforeUpdate = assignTaskRepository.findAll().size();

        // Update the assignTask
        AssignTask updatedAssignTask = assignTaskRepository.findById(assignTask.getId()).get();
        updatedAssignTask
            .title(UPDATED_TITLE)
            .assignedPerson(UPDATED_ASSIGNED_PERSON)
            .taskAssignmentDate(UPDATED_TASK_ASSIGNMENT_DATE)
            .taskStartDate(UPDATED_TASK_START_DATE)
            .taskDueDate(UPDATED_TASK_DUE_DATE);

        restAssignTaskMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedAssignTask.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedAssignTask))
            )
            .andExpect(status().isOk());

        // Validate the AssignTask in the database
        List<AssignTask> assignTaskList = assignTaskRepository.findAll();
        assertThat(assignTaskList).hasSize(databaseSizeBeforeUpdate);
        AssignTask testAssignTask = assignTaskList.get(assignTaskList.size() - 1);
        assertThat(testAssignTask.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testAssignTask.getAssignedPerson()).isEqualTo(UPDATED_ASSIGNED_PERSON);
        assertThat(testAssignTask.getTaskAssignmentDate()).isEqualTo(UPDATED_TASK_ASSIGNMENT_DATE);
        assertThat(testAssignTask.getTaskStartDate()).isEqualTo(UPDATED_TASK_START_DATE);
        assertThat(testAssignTask.getTaskDueDate()).isEqualTo(UPDATED_TASK_DUE_DATE);
    }

    @Test
    void putNonExistingAssignTask() throws Exception {
        int databaseSizeBeforeUpdate = assignTaskRepository.findAll().size();
        assignTask.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAssignTaskMockMvc
            .perform(
                put(ENTITY_API_URL_ID, assignTask.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(assignTask))
            )
            .andExpect(status().isBadRequest());

        // Validate the AssignTask in the database
        List<AssignTask> assignTaskList = assignTaskRepository.findAll();
        assertThat(assignTaskList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchAssignTask() throws Exception {
        int databaseSizeBeforeUpdate = assignTaskRepository.findAll().size();
        assignTask.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAssignTaskMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(assignTask))
            )
            .andExpect(status().isBadRequest());

        // Validate the AssignTask in the database
        List<AssignTask> assignTaskList = assignTaskRepository.findAll();
        assertThat(assignTaskList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamAssignTask() throws Exception {
        int databaseSizeBeforeUpdate = assignTaskRepository.findAll().size();
        assignTask.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAssignTaskMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(assignTask)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the AssignTask in the database
        List<AssignTask> assignTaskList = assignTaskRepository.findAll();
        assertThat(assignTaskList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateAssignTaskWithPatch() throws Exception {
        // Initialize the database
        assignTaskRepository.save(assignTask);

        int databaseSizeBeforeUpdate = assignTaskRepository.findAll().size();

        // Update the assignTask using partial update
        AssignTask partialUpdatedAssignTask = new AssignTask();
        partialUpdatedAssignTask.setId(assignTask.getId());

        partialUpdatedAssignTask.assignedPerson(UPDATED_ASSIGNED_PERSON).taskStartDate(UPDATED_TASK_START_DATE);

        restAssignTaskMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAssignTask.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAssignTask))
            )
            .andExpect(status().isOk());

        // Validate the AssignTask in the database
        List<AssignTask> assignTaskList = assignTaskRepository.findAll();
        assertThat(assignTaskList).hasSize(databaseSizeBeforeUpdate);
        AssignTask testAssignTask = assignTaskList.get(assignTaskList.size() - 1);
        assertThat(testAssignTask.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testAssignTask.getAssignedPerson()).isEqualTo(UPDATED_ASSIGNED_PERSON);
        assertThat(testAssignTask.getTaskAssignmentDate()).isEqualTo(DEFAULT_TASK_ASSIGNMENT_DATE);
        assertThat(testAssignTask.getTaskStartDate()).isEqualTo(UPDATED_TASK_START_DATE);
        assertThat(testAssignTask.getTaskDueDate()).isEqualTo(DEFAULT_TASK_DUE_DATE);
    }

    @Test
    void fullUpdateAssignTaskWithPatch() throws Exception {
        // Initialize the database
        assignTaskRepository.save(assignTask);

        int databaseSizeBeforeUpdate = assignTaskRepository.findAll().size();

        // Update the assignTask using partial update
        AssignTask partialUpdatedAssignTask = new AssignTask();
        partialUpdatedAssignTask.setId(assignTask.getId());

        partialUpdatedAssignTask
            .title(UPDATED_TITLE)
            .assignedPerson(UPDATED_ASSIGNED_PERSON)
            .taskAssignmentDate(UPDATED_TASK_ASSIGNMENT_DATE)
            .taskStartDate(UPDATED_TASK_START_DATE)
            .taskDueDate(UPDATED_TASK_DUE_DATE);

        restAssignTaskMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAssignTask.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAssignTask))
            )
            .andExpect(status().isOk());

        // Validate the AssignTask in the database
        List<AssignTask> assignTaskList = assignTaskRepository.findAll();
        assertThat(assignTaskList).hasSize(databaseSizeBeforeUpdate);
        AssignTask testAssignTask = assignTaskList.get(assignTaskList.size() - 1);
        assertThat(testAssignTask.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testAssignTask.getAssignedPerson()).isEqualTo(UPDATED_ASSIGNED_PERSON);
        assertThat(testAssignTask.getTaskAssignmentDate()).isEqualTo(UPDATED_TASK_ASSIGNMENT_DATE);
        assertThat(testAssignTask.getTaskStartDate()).isEqualTo(UPDATED_TASK_START_DATE);
        assertThat(testAssignTask.getTaskDueDate()).isEqualTo(UPDATED_TASK_DUE_DATE);
    }

    @Test
    void patchNonExistingAssignTask() throws Exception {
        int databaseSizeBeforeUpdate = assignTaskRepository.findAll().size();
        assignTask.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAssignTaskMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, assignTask.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(assignTask))
            )
            .andExpect(status().isBadRequest());

        // Validate the AssignTask in the database
        List<AssignTask> assignTaskList = assignTaskRepository.findAll();
        assertThat(assignTaskList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchAssignTask() throws Exception {
        int databaseSizeBeforeUpdate = assignTaskRepository.findAll().size();
        assignTask.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAssignTaskMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(assignTask))
            )
            .andExpect(status().isBadRequest());

        // Validate the AssignTask in the database
        List<AssignTask> assignTaskList = assignTaskRepository.findAll();
        assertThat(assignTaskList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamAssignTask() throws Exception {
        int databaseSizeBeforeUpdate = assignTaskRepository.findAll().size();
        assignTask.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAssignTaskMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(assignTask))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the AssignTask in the database
        List<AssignTask> assignTaskList = assignTaskRepository.findAll();
        assertThat(assignTaskList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteAssignTask() throws Exception {
        // Initialize the database
        assignTaskRepository.save(assignTask);

        int databaseSizeBeforeDelete = assignTaskRepository.findAll().size();

        // Delete the assignTask
        restAssignTaskMockMvc
            .perform(delete(ENTITY_API_URL_ID, assignTask.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AssignTask> assignTaskList = assignTaskRepository.findAll();
        assertThat(assignTaskList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
