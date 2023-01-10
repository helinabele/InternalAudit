package com.jhipster.audit.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A Branch.
 */
@Document(collection = "branch")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Branch implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("branch_name")
    private String branchName;

    @Field("branch_id")
    private Integer branchId;

    @Field("description")
    private String description;

    @DBRef
    @Field("department")
    @JsonIgnoreProperties(value = { "divisions", "userproifle", "branch" }, allowSetters = true)
    private Set<Department> departments = new HashSet<>();

    @DBRef
    @Field("userproifle")
    @JsonIgnoreProperties(
        value = { "userprofileRegions", "userProfileDepartments", "userProfileBranches", "userProfileDivisions" },
        allowSetters = true
    )
    private Userprofile userproifle;

    @DBRef
    @Field("region")
    @JsonIgnoreProperties(value = { "branches", "userproifle" }, allowSetters = true)
    private Region region;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public Branch id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBranchName() {
        return this.branchName;
    }

    public Branch branchName(String branchName) {
        this.setBranchName(branchName);
        return this;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public Integer getBranchId() {
        return this.branchId;
    }

    public Branch branchId(Integer branchId) {
        this.setBranchId(branchId);
        return this;
    }

    public void setBranchId(Integer branchId) {
        this.branchId = branchId;
    }

    public String getDescription() {
        return this.description;
    }

    public Branch description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Department> getDepartments() {
        return this.departments;
    }

    public void setDepartments(Set<Department> departments) {
        if (this.departments != null) {
            this.departments.forEach(i -> i.setBranch(null));
        }
        if (departments != null) {
            departments.forEach(i -> i.setBranch(this));
        }
        this.departments = departments;
    }

    public Branch departments(Set<Department> departments) {
        this.setDepartments(departments);
        return this;
    }

    public Branch addDepartment(Department department) {
        this.departments.add(department);
        department.setBranch(this);
        return this;
    }

    public Branch removeDepartment(Department department) {
        this.departments.remove(department);
        department.setBranch(null);
        return this;
    }

    public Userprofile getUserproifle() {
        return this.userproifle;
    }

    public void setUserproifle(Userprofile userprofile) {
        this.userproifle = userprofile;
    }

    public Branch userproifle(Userprofile userprofile) {
        this.setUserproifle(userprofile);
        return this;
    }

    public Region getRegion() {
        return this.region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public Branch region(Region region) {
        this.setRegion(region);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Branch)) {
            return false;
        }
        return id != null && id.equals(((Branch) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Branch{" +
            "id=" + getId() +
            ", branchName='" + getBranchName() + "'" +
            ", branchId=" + getBranchId() +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
