package com.jhipster.audit.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.jhipster.audit.IntegrationTest;
import com.jhipster.audit.domain.Userprofile;
import com.jhipster.audit.domain.enumeration.UserStatus;
import com.jhipster.audit.repository.UserprofileRepository;
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
 * Integration tests for the {@link UserprofileResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class UserprofileResourceIT {

    private static final Integer DEFAULT_REGION_ID = 1;
    private static final Integer UPDATED_REGION_ID = 2;

    private static final Integer DEFAULT_BRANCH_ID = 1;
    private static final Integer UPDATED_BRANCH_ID = 2;

    private static final Integer DEFAULT_DEPARTMENT_ID = 1;
    private static final Integer UPDATED_DEPARTMENT_ID = 2;

    private static final Integer DEFAULT_DIVISION_ID = 1;
    private static final Integer UPDATED_DIVISION_ID = 2;

    private static final UserStatus DEFAULT_USER_STATUS = UserStatus.SPRING;
    private static final UserStatus UPDATED_USER_STATUS = UserStatus.SUMMER;

    private static final String ENTITY_API_URL = "/api/userprofiles";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private UserprofileRepository userprofileRepository;

    @Autowired
    private MockMvc restUserprofileMockMvc;

    private Userprofile userprofile;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Userprofile createEntity() {
        Userprofile userprofile = new Userprofile()
            .regionId(DEFAULT_REGION_ID)
            .branchId(DEFAULT_BRANCH_ID)
            .departmentId(DEFAULT_DEPARTMENT_ID)
            .divisionId(DEFAULT_DIVISION_ID)
            .userStatus(DEFAULT_USER_STATUS);
        return userprofile;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Userprofile createUpdatedEntity() {
        Userprofile userprofile = new Userprofile()
            .regionId(UPDATED_REGION_ID)
            .branchId(UPDATED_BRANCH_ID)
            .departmentId(UPDATED_DEPARTMENT_ID)
            .divisionId(UPDATED_DIVISION_ID)
            .userStatus(UPDATED_USER_STATUS);
        return userprofile;
    }

    @BeforeEach
    public void initTest() {
        userprofileRepository.deleteAll();
        userprofile = createEntity();
    }

    @Test
    void createUserprofile() throws Exception {
        int databaseSizeBeforeCreate = userprofileRepository.findAll().size();
        // Create the Userprofile
        restUserprofileMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(userprofile)))
            .andExpect(status().isCreated());

        // Validate the Userprofile in the database
        List<Userprofile> userprofileList = userprofileRepository.findAll();
        assertThat(userprofileList).hasSize(databaseSizeBeforeCreate + 1);
        Userprofile testUserprofile = userprofileList.get(userprofileList.size() - 1);
        assertThat(testUserprofile.getRegionId()).isEqualTo(DEFAULT_REGION_ID);
        assertThat(testUserprofile.getBranchId()).isEqualTo(DEFAULT_BRANCH_ID);
        assertThat(testUserprofile.getDepartmentId()).isEqualTo(DEFAULT_DEPARTMENT_ID);
        assertThat(testUserprofile.getDivisionId()).isEqualTo(DEFAULT_DIVISION_ID);
        assertThat(testUserprofile.getUserStatus()).isEqualTo(DEFAULT_USER_STATUS);
    }

    @Test
    void createUserprofileWithExistingId() throws Exception {
        // Create the Userprofile with an existing ID
        userprofile.setId("existing_id");

        int databaseSizeBeforeCreate = userprofileRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restUserprofileMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(userprofile)))
            .andExpect(status().isBadRequest());

        // Validate the Userprofile in the database
        List<Userprofile> userprofileList = userprofileRepository.findAll();
        assertThat(userprofileList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllUserprofiles() throws Exception {
        // Initialize the database
        userprofileRepository.save(userprofile);

        // Get all the userprofileList
        restUserprofileMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(userprofile.getId())))
            .andExpect(jsonPath("$.[*].regionId").value(hasItem(DEFAULT_REGION_ID)))
            .andExpect(jsonPath("$.[*].branchId").value(hasItem(DEFAULT_BRANCH_ID)))
            .andExpect(jsonPath("$.[*].departmentId").value(hasItem(DEFAULT_DEPARTMENT_ID)))
            .andExpect(jsonPath("$.[*].divisionId").value(hasItem(DEFAULT_DIVISION_ID)))
            .andExpect(jsonPath("$.[*].userStatus").value(hasItem(DEFAULT_USER_STATUS.toString())));
    }

    @Test
    void getUserprofile() throws Exception {
        // Initialize the database
        userprofileRepository.save(userprofile);

        // Get the userprofile
        restUserprofileMockMvc
            .perform(get(ENTITY_API_URL_ID, userprofile.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(userprofile.getId()))
            .andExpect(jsonPath("$.regionId").value(DEFAULT_REGION_ID))
            .andExpect(jsonPath("$.branchId").value(DEFAULT_BRANCH_ID))
            .andExpect(jsonPath("$.departmentId").value(DEFAULT_DEPARTMENT_ID))
            .andExpect(jsonPath("$.divisionId").value(DEFAULT_DIVISION_ID))
            .andExpect(jsonPath("$.userStatus").value(DEFAULT_USER_STATUS.toString()));
    }

    @Test
    void getNonExistingUserprofile() throws Exception {
        // Get the userprofile
        restUserprofileMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    void putExistingUserprofile() throws Exception {
        // Initialize the database
        userprofileRepository.save(userprofile);

        int databaseSizeBeforeUpdate = userprofileRepository.findAll().size();

        // Update the userprofile
        Userprofile updatedUserprofile = userprofileRepository.findById(userprofile.getId()).get();
        updatedUserprofile
            .regionId(UPDATED_REGION_ID)
            .branchId(UPDATED_BRANCH_ID)
            .departmentId(UPDATED_DEPARTMENT_ID)
            .divisionId(UPDATED_DIVISION_ID)
            .userStatus(UPDATED_USER_STATUS);

        restUserprofileMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedUserprofile.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedUserprofile))
            )
            .andExpect(status().isOk());

        // Validate the Userprofile in the database
        List<Userprofile> userprofileList = userprofileRepository.findAll();
        assertThat(userprofileList).hasSize(databaseSizeBeforeUpdate);
        Userprofile testUserprofile = userprofileList.get(userprofileList.size() - 1);
        assertThat(testUserprofile.getRegionId()).isEqualTo(UPDATED_REGION_ID);
        assertThat(testUserprofile.getBranchId()).isEqualTo(UPDATED_BRANCH_ID);
        assertThat(testUserprofile.getDepartmentId()).isEqualTo(UPDATED_DEPARTMENT_ID);
        assertThat(testUserprofile.getDivisionId()).isEqualTo(UPDATED_DIVISION_ID);
        assertThat(testUserprofile.getUserStatus()).isEqualTo(UPDATED_USER_STATUS);
    }

    @Test
    void putNonExistingUserprofile() throws Exception {
        int databaseSizeBeforeUpdate = userprofileRepository.findAll().size();
        userprofile.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUserprofileMockMvc
            .perform(
                put(ENTITY_API_URL_ID, userprofile.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(userprofile))
            )
            .andExpect(status().isBadRequest());

        // Validate the Userprofile in the database
        List<Userprofile> userprofileList = userprofileRepository.findAll();
        assertThat(userprofileList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchUserprofile() throws Exception {
        int databaseSizeBeforeUpdate = userprofileRepository.findAll().size();
        userprofile.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restUserprofileMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(userprofile))
            )
            .andExpect(status().isBadRequest());

        // Validate the Userprofile in the database
        List<Userprofile> userprofileList = userprofileRepository.findAll();
        assertThat(userprofileList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamUserprofile() throws Exception {
        int databaseSizeBeforeUpdate = userprofileRepository.findAll().size();
        userprofile.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restUserprofileMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(userprofile)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Userprofile in the database
        List<Userprofile> userprofileList = userprofileRepository.findAll();
        assertThat(userprofileList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateUserprofileWithPatch() throws Exception {
        // Initialize the database
        userprofileRepository.save(userprofile);

        int databaseSizeBeforeUpdate = userprofileRepository.findAll().size();

        // Update the userprofile using partial update
        Userprofile partialUpdatedUserprofile = new Userprofile();
        partialUpdatedUserprofile.setId(userprofile.getId());

        partialUpdatedUserprofile.regionId(UPDATED_REGION_ID).branchId(UPDATED_BRANCH_ID);

        restUserprofileMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedUserprofile.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedUserprofile))
            )
            .andExpect(status().isOk());

        // Validate the Userprofile in the database
        List<Userprofile> userprofileList = userprofileRepository.findAll();
        assertThat(userprofileList).hasSize(databaseSizeBeforeUpdate);
        Userprofile testUserprofile = userprofileList.get(userprofileList.size() - 1);
        assertThat(testUserprofile.getRegionId()).isEqualTo(UPDATED_REGION_ID);
        assertThat(testUserprofile.getBranchId()).isEqualTo(UPDATED_BRANCH_ID);
        assertThat(testUserprofile.getDepartmentId()).isEqualTo(DEFAULT_DEPARTMENT_ID);
        assertThat(testUserprofile.getDivisionId()).isEqualTo(DEFAULT_DIVISION_ID);
        assertThat(testUserprofile.getUserStatus()).isEqualTo(DEFAULT_USER_STATUS);
    }

    @Test
    void fullUpdateUserprofileWithPatch() throws Exception {
        // Initialize the database
        userprofileRepository.save(userprofile);

        int databaseSizeBeforeUpdate = userprofileRepository.findAll().size();

        // Update the userprofile using partial update
        Userprofile partialUpdatedUserprofile = new Userprofile();
        partialUpdatedUserprofile.setId(userprofile.getId());

        partialUpdatedUserprofile
            .regionId(UPDATED_REGION_ID)
            .branchId(UPDATED_BRANCH_ID)
            .departmentId(UPDATED_DEPARTMENT_ID)
            .divisionId(UPDATED_DIVISION_ID)
            .userStatus(UPDATED_USER_STATUS);

        restUserprofileMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedUserprofile.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedUserprofile))
            )
            .andExpect(status().isOk());

        // Validate the Userprofile in the database
        List<Userprofile> userprofileList = userprofileRepository.findAll();
        assertThat(userprofileList).hasSize(databaseSizeBeforeUpdate);
        Userprofile testUserprofile = userprofileList.get(userprofileList.size() - 1);
        assertThat(testUserprofile.getRegionId()).isEqualTo(UPDATED_REGION_ID);
        assertThat(testUserprofile.getBranchId()).isEqualTo(UPDATED_BRANCH_ID);
        assertThat(testUserprofile.getDepartmentId()).isEqualTo(UPDATED_DEPARTMENT_ID);
        assertThat(testUserprofile.getDivisionId()).isEqualTo(UPDATED_DIVISION_ID);
        assertThat(testUserprofile.getUserStatus()).isEqualTo(UPDATED_USER_STATUS);
    }

    @Test
    void patchNonExistingUserprofile() throws Exception {
        int databaseSizeBeforeUpdate = userprofileRepository.findAll().size();
        userprofile.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUserprofileMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, userprofile.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(userprofile))
            )
            .andExpect(status().isBadRequest());

        // Validate the Userprofile in the database
        List<Userprofile> userprofileList = userprofileRepository.findAll();
        assertThat(userprofileList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchUserprofile() throws Exception {
        int databaseSizeBeforeUpdate = userprofileRepository.findAll().size();
        userprofile.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restUserprofileMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(userprofile))
            )
            .andExpect(status().isBadRequest());

        // Validate the Userprofile in the database
        List<Userprofile> userprofileList = userprofileRepository.findAll();
        assertThat(userprofileList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamUserprofile() throws Exception {
        int databaseSizeBeforeUpdate = userprofileRepository.findAll().size();
        userprofile.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restUserprofileMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(userprofile))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Userprofile in the database
        List<Userprofile> userprofileList = userprofileRepository.findAll();
        assertThat(userprofileList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteUserprofile() throws Exception {
        // Initialize the database
        userprofileRepository.save(userprofile);

        int databaseSizeBeforeDelete = userprofileRepository.findAll().size();

        // Delete the userprofile
        restUserprofileMockMvc
            .perform(delete(ENTITY_API_URL_ID, userprofile.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Userprofile> userprofileList = userprofileRepository.findAll();
        assertThat(userprofileList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
