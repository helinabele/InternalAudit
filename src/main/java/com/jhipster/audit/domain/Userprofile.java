package com.jhipster.audit.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jhipster.audit.domain.enumeration.UserStatus;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A Userprofile.
 */
@Document(collection = "userprofile")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Userprofile implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("region_id")
    private Integer regionId;

    @Field("branch_id")
    private Integer branchId;

    @Field("department_id")
    private Integer departmentId;

    @Field("division_id")
    private Integer divisionId;

    @Field("user_status")
    private UserStatus userStatus;

    @DBRef
    @Field("userprofileRegion")
    @JsonIgnoreProperties(value = { "branches", "userproifle" }, allowSetters = true)
    private Set<Region> userprofileRegions = new HashSet<>();

    @DBRef
    @Field("userProfileDepartment")
    @JsonIgnoreProperties(value = { "divisions", "userproifle", "branch" }, allowSetters = true)
    private Set<Department> userProfileDepartments = new HashSet<>();

    @DBRef
    @Field("userProfileBranch")
    @JsonIgnoreProperties(value = { "departments", "userproifle", "region" }, allowSetters = true)
    private Set<Branch> userProfileBranches = new HashSet<>();

    @DBRef
    @Field("userProfileDivision")
    @JsonIgnoreProperties(value = { "userproifle", "department" }, allowSetters = true)
    private Set<Division> userProfileDivisions = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public Userprofile id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getRegionId() {
        return this.regionId;
    }

    public Userprofile regionId(Integer regionId) {
        this.setRegionId(regionId);
        return this;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }

    public Integer getBranchId() {
        return this.branchId;
    }

    public Userprofile branchId(Integer branchId) {
        this.setBranchId(branchId);
        return this;
    }

    public void setBranchId(Integer branchId) {
        this.branchId = branchId;
    }

    public Integer getDepartmentId() {
        return this.departmentId;
    }

    public Userprofile departmentId(Integer departmentId) {
        this.setDepartmentId(departmentId);
        return this;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public Integer getDivisionId() {
        return this.divisionId;
    }

    public Userprofile divisionId(Integer divisionId) {
        this.setDivisionId(divisionId);
        return this;
    }

    public void setDivisionId(Integer divisionId) {
        this.divisionId = divisionId;
    }

    public UserStatus getUserStatus() {
        return this.userStatus;
    }

    public Userprofile userStatus(UserStatus userStatus) {
        this.setUserStatus(userStatus);
        return this;
    }

    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }

    public Set<Region> getUserprofileRegions() {
        return this.userprofileRegions;
    }

    public void setUserprofileRegions(Set<Region> regions) {
        if (this.userprofileRegions != null) {
            this.userprofileRegions.forEach(i -> i.setUserproifle(null));
        }
        if (regions != null) {
            regions.forEach(i -> i.setUserproifle(this));
        }
        this.userprofileRegions = regions;
    }

    public Userprofile userprofileRegions(Set<Region> regions) {
        this.setUserprofileRegions(regions);
        return this;
    }

    public Userprofile addUserprofileRegion(Region region) {
        this.userprofileRegions.add(region);
        region.setUserproifle(this);
        return this;
    }

    public Userprofile removeUserprofileRegion(Region region) {
        this.userprofileRegions.remove(region);
        region.setUserproifle(null);
        return this;
    }

    public Set<Department> getUserProfileDepartments() {
        return this.userProfileDepartments;
    }

    public void setUserProfileDepartments(Set<Department> departments) {
        if (this.userProfileDepartments != null) {
            this.userProfileDepartments.forEach(i -> i.setUserproifle(null));
        }
        if (departments != null) {
            departments.forEach(i -> i.setUserproifle(this));
        }
        this.userProfileDepartments = departments;
    }

    public Userprofile userProfileDepartments(Set<Department> departments) {
        this.setUserProfileDepartments(departments);
        return this;
    }

    public Userprofile addUserProfileDepartment(Department department) {
        this.userProfileDepartments.add(department);
        department.setUserproifle(this);
        return this;
    }

    public Userprofile removeUserProfileDepartment(Department department) {
        this.userProfileDepartments.remove(department);
        department.setUserproifle(null);
        return this;
    }

    public Set<Branch> getUserProfileBranches() {
        return this.userProfileBranches;
    }

    public void setUserProfileBranches(Set<Branch> branches) {
        if (this.userProfileBranches != null) {
            this.userProfileBranches.forEach(i -> i.setUserproifle(null));
        }
        if (branches != null) {
            branches.forEach(i -> i.setUserproifle(this));
        }
        this.userProfileBranches = branches;
    }

    public Userprofile userProfileBranches(Set<Branch> branches) {
        this.setUserProfileBranches(branches);
        return this;
    }

    public Userprofile addUserProfileBranch(Branch branch) {
        this.userProfileBranches.add(branch);
        branch.setUserproifle(this);
        return this;
    }

    public Userprofile removeUserProfileBranch(Branch branch) {
        this.userProfileBranches.remove(branch);
        branch.setUserproifle(null);
        return this;
    }

    public Set<Division> getUserProfileDivisions() {
        return this.userProfileDivisions;
    }

    public void setUserProfileDivisions(Set<Division> divisions) {
        if (this.userProfileDivisions != null) {
            this.userProfileDivisions.forEach(i -> i.setUserproifle(null));
        }
        if (divisions != null) {
            divisions.forEach(i -> i.setUserproifle(this));
        }
        this.userProfileDivisions = divisions;
    }

    public Userprofile userProfileDivisions(Set<Division> divisions) {
        this.setUserProfileDivisions(divisions);
        return this;
    }

    public Userprofile addUserProfileDivision(Division division) {
        this.userProfileDivisions.add(division);
        division.setUserproifle(this);
        return this;
    }

    public Userprofile removeUserProfileDivision(Division division) {
        this.userProfileDivisions.remove(division);
        division.setUserproifle(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Userprofile)) {
            return false;
        }
        return id != null && id.equals(((Userprofile) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Userprofile{" +
            "id=" + getId() +
            ", regionId=" + getRegionId() +
            ", branchId=" + getBranchId() +
            ", departmentId=" + getDepartmentId() +
            ", divisionId=" + getDivisionId() +
            ", userStatus='" + getUserStatus() + "'" +
            "}";
    }
}
