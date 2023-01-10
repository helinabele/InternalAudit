package com.jhipster.audit.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A FraudInvestigationReport.
 */
@Document(collection = "fraud_investigation_report")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class FraudInvestigationReport implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Field("title")
    private String title;

    @Field("executive_summary")
    private String executiveSummary;

    @Field("introduction")
    private String introduction;

    @Field("objective")
    private String objective;

    @Field("scope")
    private String scope;

    @Field("limitation")
    private String limitation;

    @Field("methodology")
    private String methodology;

    @Field("finding_and_analysis")
    private String findingAndAnalysis;

    @Field("conclusion")
    private String conclusion;

    @Field("recommendation")
    private String recommendation;

    @Field("name_of_members")
    private String nameOfMembers;

    @Field("signature")
    private String signature;

    @Field("annexes")
    private String annexes;

    @Field("author")
    private String author;

    @DBRef
    @Field("fraudType")
    @JsonIgnoreProperties(value = { "fraudInvestigationReports" }, allowSetters = true)
    private FraudKnowledgeManagement fraudType;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public FraudInvestigationReport id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public FraudInvestigationReport title(String title) {
        this.setTitle(title);
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getExecutiveSummary() {
        return this.executiveSummary;
    }

    public FraudInvestigationReport executiveSummary(String executiveSummary) {
        this.setExecutiveSummary(executiveSummary);
        return this;
    }

    public void setExecutiveSummary(String executiveSummary) {
        this.executiveSummary = executiveSummary;
    }

    public String getIntroduction() {
        return this.introduction;
    }

    public FraudInvestigationReport introduction(String introduction) {
        this.setIntroduction(introduction);
        return this;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getObjective() {
        return this.objective;
    }

    public FraudInvestigationReport objective(String objective) {
        this.setObjective(objective);
        return this;
    }

    public void setObjective(String objective) {
        this.objective = objective;
    }

    public String getScope() {
        return this.scope;
    }

    public FraudInvestigationReport scope(String scope) {
        this.setScope(scope);
        return this;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getLimitation() {
        return this.limitation;
    }

    public FraudInvestigationReport limitation(String limitation) {
        this.setLimitation(limitation);
        return this;
    }

    public void setLimitation(String limitation) {
        this.limitation = limitation;
    }

    public String getMethodology() {
        return this.methodology;
    }

    public FraudInvestigationReport methodology(String methodology) {
        this.setMethodology(methodology);
        return this;
    }

    public void setMethodology(String methodology) {
        this.methodology = methodology;
    }

    public String getFindingAndAnalysis() {
        return this.findingAndAnalysis;
    }

    public FraudInvestigationReport findingAndAnalysis(String findingAndAnalysis) {
        this.setFindingAndAnalysis(findingAndAnalysis);
        return this;
    }

    public void setFindingAndAnalysis(String findingAndAnalysis) {
        this.findingAndAnalysis = findingAndAnalysis;
    }

    public String getConclusion() {
        return this.conclusion;
    }

    public FraudInvestigationReport conclusion(String conclusion) {
        this.setConclusion(conclusion);
        return this;
    }

    public void setConclusion(String conclusion) {
        this.conclusion = conclusion;
    }

    public String getRecommendation() {
        return this.recommendation;
    }

    public FraudInvestigationReport recommendation(String recommendation) {
        this.setRecommendation(recommendation);
        return this;
    }

    public void setRecommendation(String recommendation) {
        this.recommendation = recommendation;
    }

    public String getNameOfMembers() {
        return this.nameOfMembers;
    }

    public FraudInvestigationReport nameOfMembers(String nameOfMembers) {
        this.setNameOfMembers(nameOfMembers);
        return this;
    }

    public void setNameOfMembers(String nameOfMembers) {
        this.nameOfMembers = nameOfMembers;
    }

    public String getSignature() {
        return this.signature;
    }

    public FraudInvestigationReport signature(String signature) {
        this.setSignature(signature);
        return this;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getAnnexes() {
        return this.annexes;
    }

    public FraudInvestigationReport annexes(String annexes) {
        this.setAnnexes(annexes);
        return this;
    }

    public void setAnnexes(String annexes) {
        this.annexes = annexes;
    }

    public String getAuthor() {
        return this.author;
    }

    public FraudInvestigationReport author(String author) {
        this.setAuthor(author);
        return this;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public FraudKnowledgeManagement getFraudType() {
        return this.fraudType;
    }

    public void setFraudType(FraudKnowledgeManagement fraudKnowledgeManagement) {
        this.fraudType = fraudKnowledgeManagement;
    }

    public FraudInvestigationReport fraudType(FraudKnowledgeManagement fraudKnowledgeManagement) {
        this.setFraudType(fraudKnowledgeManagement);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FraudInvestigationReport)) {
            return false;
        }
        return id != null && id.equals(((FraudInvestigationReport) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FraudInvestigationReport{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", executiveSummary='" + getExecutiveSummary() + "'" +
            ", introduction='" + getIntroduction() + "'" +
            ", objective='" + getObjective() + "'" +
            ", scope='" + getScope() + "'" +
            ", limitation='" + getLimitation() + "'" +
            ", methodology='" + getMethodology() + "'" +
            ", findingAndAnalysis='" + getFindingAndAnalysis() + "'" +
            ", conclusion='" + getConclusion() + "'" +
            ", recommendation='" + getRecommendation() + "'" +
            ", nameOfMembers='" + getNameOfMembers() + "'" +
            ", signature='" + getSignature() + "'" +
            ", annexes='" + getAnnexes() + "'" +
            ", author='" + getAuthor() + "'" +
            "}";
    }
}
