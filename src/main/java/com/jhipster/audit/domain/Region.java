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
 * A Region.
 */
@Document(collection = "region")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Region implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("region_name")
    private String regionName;

    @Field("region_id")
    private Integer regionId;

    @Field("description")
    private String description;

    @DBRef
    @Field("branch")
    @JsonIgnoreProperties(value = { "departments", "userproifle", "region" }, allowSetters = true)
    private Set<Branch> branches = new HashSet<>();

    @DBRef
    @Field("userproifle")
    @JsonIgnoreProperties(
        value = { "userprofileRegions", "userProfileDepartments", "userProfileBranches", "userProfileDivisions" },
        allowSetters = true
    )
    private Userprofile userproifle;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public Region id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRegionName() {
        return this.regionName;
    }

    public Region regionName(String regionName) {
        this.setRegionName(regionName);
        return this;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public Integer getRegionId() {
        return this.regionId;
    }

    public Region regionId(Integer regionId) {
        this.setRegionId(regionId);
        return this;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }

    public String getDescription() {
        return this.description;
    }

    public Region description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Branch> getBranches() {
        return this.branches;
    }

    public void setBranches(Set<Branch> branches) {
        if (this.branches != null) {
            this.branches.forEach(i -> i.setRegion(null));
        }
        if (branches != null) {
            branches.forEach(i -> i.setRegion(this));
        }
        this.branches = branches;
    }

    public Region branches(Set<Branch> branches) {
        this.setBranches(branches);
        return this;
    }

    public Region addBranch(Branch branch) {
        this.branches.add(branch);
        branch.setRegion(this);
        return this;
    }

    public Region removeBranch(Branch branch) {
        this.branches.remove(branch);
        branch.setRegion(null);
        return this;
    }

    public Userprofile getUserproifle() {
        return this.userproifle;
    }

    public void setUserproifle(Userprofile userprofile) {
        this.userproifle = userprofile;
    }

    public Region userproifle(Userprofile userprofile) {
        this.setUserproifle(userprofile);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Region)) {
            return false;
        }
        return id != null && id.equals(((Region) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Region{" +
            "id=" + getId() +
            ", regionName='" + getRegionName() + "'" +
            ", regionId=" + getRegionId() +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
