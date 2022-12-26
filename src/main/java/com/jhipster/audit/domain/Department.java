package com.jhipster.audit.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.validation.constraints.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A Department.
 */
@Document(collection = "department")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Department implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("department_name")
    private String departmentName;

    @Field("department_id")
    private Integer departmentId;

    @Field("description")
    private String description;

    @DBRef
    @Field("division")
    @JsonIgnoreProperties(value = { "userproifle", "department" }, allowSetters = true)
    private Set<Division> divisions = new HashSet<>();

    @DBRef
    @Field("userproifle")
    @JsonIgnoreProperties(
        value = { "userprofileRegions", "userProfileDepartments", "userProfileBranches", "userProfileDivisions" },
        allowSetters = true
    )
    private Userprofile userproifle;

    @DBRef
    @Field("branch")
    @JsonIgnoreProperties(value = { "departments", "userproifle", "region" }, allowSetters = true)
    private Branch branch;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public Department id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return this.departmentName;
    }

    public Department departmentName(String departmentName) {
        this.setDepartmentName(departmentName);
        return this;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Integer getDepartmentId() {
        return this.departmentId;
    }

    public Department departmentId(Integer departmentId) {
        this.setDepartmentId(departmentId);
        return this;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public String getDescription() {
        return this.description;
    }

    public Department description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Division> getDivisions() {
        return this.divisions;
    }

    public void setDivisions(Set<Division> divisions) {
        if (this.divisions != null) {
            this.divisions.forEach(i -> i.setDepartment(null));
        }
        if (divisions != null) {
            divisions.forEach(i -> i.setDepartment(this));
        }
        this.divisions = divisions;
    }

    public Department divisions(Set<Division> divisions) {
        this.setDivisions(divisions);
        return this;
    }

    public Department addDivision(Division division) {
        this.divisions.add(division);
        division.setDepartment(this);
        return this;
    }

    public Department removeDivision(Division division) {
        this.divisions.remove(division);
        division.setDepartment(null);
        return this;
    }

    public Userprofile getUserproifle() {
        return this.userproifle;
    }

    public void setUserproifle(Userprofile userprofile) {
        this.userproifle = userprofile;
    }

    public Department userproifle(Userprofile userprofile) {
        this.setUserproifle(userprofile);
        return this;
    }

    public Branch getBranch() {
        return this.branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public Department branch(Branch branch) {
        this.setBranch(branch);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Department)) {
            return false;
        }
        return id != null && id.equals(((Department) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Department{" +
            "id=" + getId() +
            ", departmentName='" + getDepartmentName() + "'" +
            ", departmentId=" + getDepartmentId() +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
