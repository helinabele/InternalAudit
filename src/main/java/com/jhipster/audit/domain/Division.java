package com.jhipster.audit.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A Division.
 */
@Document(collection = "division")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Division implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("description")
    private String description;

    @Field("division_id")
    private Integer divisionId;

    @Field("division_name")
    private String divisionName;

    @DBRef
    @Field("userproifle")
    @JsonIgnoreProperties(
        value = { "userprofileRegions", "userProfileDepartments", "userProfileBranches", "userProfileDivisions" },
        allowSetters = true
    )
    private Userprofile userproifle;

    @DBRef
    @Field("department")
    @JsonIgnoreProperties(value = { "divisions", "userproifle", "branch" }, allowSetters = true)
    private Department department;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public Division id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return this.description;
    }

    public Division description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDivisionId() {
        return this.divisionId;
    }

    public Division divisionId(Integer divisionId) {
        this.setDivisionId(divisionId);
        return this;
    }

    public void setDivisionId(Integer divisionId) {
        this.divisionId = divisionId;
    }

    public String getDivisionName() {
        return this.divisionName;
    }

    public Division divisionName(String divisionName) {
        this.setDivisionName(divisionName);
        return this;
    }

    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }

    public Userprofile getUserproifle() {
        return this.userproifle;
    }

    public void setUserproifle(Userprofile userprofile) {
        this.userproifle = userprofile;
    }

    public Division userproifle(Userprofile userprofile) {
        this.setUserproifle(userprofile);
        return this;
    }

    public Department getDepartment() {
        return this.department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Division department(Department department) {
        this.setDepartment(department);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Division)) {
            return false;
        }
        return id != null && id.equals(((Division) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Division{" +
            "id=" + getId() +
            ", description='" + getDescription() + "'" +
            ", divisionId=" + getDivisionId() +
            ", divisionName='" + getDivisionName() + "'" +
            "}";
    }
}
