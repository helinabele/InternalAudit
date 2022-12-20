package com.jhipster.audit.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A FraudKnowledgeManagement.
 */
@Document(collection = "fraud_knowledge_management")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class FraudKnowledgeManagement implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("report_number")
    private Integer reportNumber;

    @Field("case_title")
    private String caseTitle;

    @Field("fraud_type")
    private String fraudType;

    @Field("unit")
    private String unit;

    @Field("incident_date")
    private LocalDate incidentDate;

    @Field("report_date")
    private LocalDate reportDate;

    @Field("internal_employee")
    private String internalEmployee;

    @Field("external_customer")
    private String externalCustomer;

    @Field("financial_loss_amount")
    private Float financialLossAmount;

    @Field("cause_for_an_incident")
    private String causeForAnIncident;

    @Field("effect")
    private String effect;

    @Field("recommendations_drawn")
    private String recommendationsDrawn;

    @Field("position_jg")
    private String positionJG;

    @Field("name_id_no")
    private String nameIdNo;

    @Field("action_involved")
    private String actionInvolved;

    @Field("ng_screener_report")
    private String ngScreenerReport;

    @Field("status_after_reporting")
    private String statusAfterReporting;

    @DBRef
    @Field("fraudInvestigationReport")
    @JsonIgnoreProperties(value = { "fraudType" }, allowSetters = true)
    private Set<FraudInvestigationReport> fraudInvestigationReports = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public FraudKnowledgeManagement id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getReportNumber() {
        return this.reportNumber;
    }

    public FraudKnowledgeManagement reportNumber(Integer reportNumber) {
        this.setReportNumber(reportNumber);
        return this;
    }

    public void setReportNumber(Integer reportNumber) {
        this.reportNumber = reportNumber;
    }

    public String getCaseTitle() {
        return this.caseTitle;
    }

    public FraudKnowledgeManagement caseTitle(String caseTitle) {
        this.setCaseTitle(caseTitle);
        return this;
    }

    public void setCaseTitle(String caseTitle) {
        this.caseTitle = caseTitle;
    }

    public String getFraudType() {
        return this.fraudType;
    }

    public FraudKnowledgeManagement fraudType(String fraudType) {
        this.setFraudType(fraudType);
        return this;
    }

    public void setFraudType(String fraudType) {
        this.fraudType = fraudType;
    }

    public String getUnit() {
        return this.unit;
    }

    public FraudKnowledgeManagement unit(String unit) {
        this.setUnit(unit);
        return this;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public LocalDate getIncidentDate() {
        return this.incidentDate;
    }

    public FraudKnowledgeManagement incidentDate(LocalDate incidentDate) {
        this.setIncidentDate(incidentDate);
        return this;
    }

    public void setIncidentDate(LocalDate incidentDate) {
        this.incidentDate = incidentDate;
    }

    public LocalDate getReportDate() {
        return this.reportDate;
    }

    public FraudKnowledgeManagement reportDate(LocalDate reportDate) {
        this.setReportDate(reportDate);
        return this;
    }

    public void setReportDate(LocalDate reportDate) {
        this.reportDate = reportDate;
    }

    public String getInternalEmployee() {
        return this.internalEmployee;
    }

    public FraudKnowledgeManagement internalEmployee(String internalEmployee) {
        this.setInternalEmployee(internalEmployee);
        return this;
    }

    public void setInternalEmployee(String internalEmployee) {
        this.internalEmployee = internalEmployee;
    }

    public String getExternalCustomer() {
        return this.externalCustomer;
    }

    public FraudKnowledgeManagement externalCustomer(String externalCustomer) {
        this.setExternalCustomer(externalCustomer);
        return this;
    }

    public void setExternalCustomer(String externalCustomer) {
        this.externalCustomer = externalCustomer;
    }

    public Float getFinancialLossAmount() {
        return this.financialLossAmount;
    }

    public FraudKnowledgeManagement financialLossAmount(Float financialLossAmount) {
        this.setFinancialLossAmount(financialLossAmount);
        return this;
    }

    public void setFinancialLossAmount(Float financialLossAmount) {
        this.financialLossAmount = financialLossAmount;
    }

    public String getCauseForAnIncident() {
        return this.causeForAnIncident;
    }

    public FraudKnowledgeManagement causeForAnIncident(String causeForAnIncident) {
        this.setCauseForAnIncident(causeForAnIncident);
        return this;
    }

    public void setCauseForAnIncident(String causeForAnIncident) {
        this.causeForAnIncident = causeForAnIncident;
    }

    public String getEffect() {
        return this.effect;
    }

    public FraudKnowledgeManagement effect(String effect) {
        this.setEffect(effect);
        return this;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }

    public String getRecommendationsDrawn() {
        return this.recommendationsDrawn;
    }

    public FraudKnowledgeManagement recommendationsDrawn(String recommendationsDrawn) {
        this.setRecommendationsDrawn(recommendationsDrawn);
        return this;
    }

    public void setRecommendationsDrawn(String recommendationsDrawn) {
        this.recommendationsDrawn = recommendationsDrawn;
    }

    public String getPositionJG() {
        return this.positionJG;
    }

    public FraudKnowledgeManagement positionJG(String positionJG) {
        this.setPositionJG(positionJG);
        return this;
    }

    public void setPositionJG(String positionJG) {
        this.positionJG = positionJG;
    }

    public String getNameIdNo() {
        return this.nameIdNo;
    }

    public FraudKnowledgeManagement nameIdNo(String nameIdNo) {
        this.setNameIdNo(nameIdNo);
        return this;
    }

    public void setNameIdNo(String nameIdNo) {
        this.nameIdNo = nameIdNo;
    }

    public String getActionInvolved() {
        return this.actionInvolved;
    }

    public FraudKnowledgeManagement actionInvolved(String actionInvolved) {
        this.setActionInvolved(actionInvolved);
        return this;
    }

    public void setActionInvolved(String actionInvolved) {
        this.actionInvolved = actionInvolved;
    }

    public String getNgScreenerReport() {
        return this.ngScreenerReport;
    }

    public FraudKnowledgeManagement ngScreenerReport(String ngScreenerReport) {
        this.setNgScreenerReport(ngScreenerReport);
        return this;
    }

    public void setNgScreenerReport(String ngScreenerReport) {
        this.ngScreenerReport = ngScreenerReport;
    }

    public String getStatusAfterReporting() {
        return this.statusAfterReporting;
    }

    public FraudKnowledgeManagement statusAfterReporting(String statusAfterReporting) {
        this.setStatusAfterReporting(statusAfterReporting);
        return this;
    }

    public void setStatusAfterReporting(String statusAfterReporting) {
        this.statusAfterReporting = statusAfterReporting;
    }

    public Set<FraudInvestigationReport> getFraudInvestigationReports() {
        return this.fraudInvestigationReports;
    }

    public void setFraudInvestigationReports(Set<FraudInvestigationReport> fraudInvestigationReports) {
        if (this.fraudInvestigationReports != null) {
            this.fraudInvestigationReports.forEach(i -> i.setFraudType(null));
        }
        if (fraudInvestigationReports != null) {
            fraudInvestigationReports.forEach(i -> i.setFraudType(this));
        }
        this.fraudInvestigationReports = fraudInvestigationReports;
    }

    public FraudKnowledgeManagement fraudInvestigationReports(Set<FraudInvestigationReport> fraudInvestigationReports) {
        this.setFraudInvestigationReports(fraudInvestigationReports);
        return this;
    }

    public FraudKnowledgeManagement addFraudInvestigationReport(FraudInvestigationReport fraudInvestigationReport) {
        this.fraudInvestigationReports.add(fraudInvestigationReport);
        fraudInvestigationReport.setFraudType(this);
        return this;
    }

    public FraudKnowledgeManagement removeFraudInvestigationReport(FraudInvestigationReport fraudInvestigationReport) {
        this.fraudInvestigationReports.remove(fraudInvestigationReport);
        fraudInvestigationReport.setFraudType(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FraudKnowledgeManagement)) {
            return false;
        }
        return id != null && id.equals(((FraudKnowledgeManagement) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FraudKnowledgeManagement{" +
            "id=" + getId() +
            ", reportNumber=" + getReportNumber() +
            ", caseTitle='" + getCaseTitle() + "'" +
            ", fraudType='" + getFraudType() + "'" +
            ", unit='" + getUnit() + "'" +
            ", incidentDate='" + getIncidentDate() + "'" +
            ", reportDate='" + getReportDate() + "'" +
            ", internalEmployee='" + getInternalEmployee() + "'" +
            ", externalCustomer='" + getExternalCustomer() + "'" +
            ", financialLossAmount=" + getFinancialLossAmount() +
            ", causeForAnIncident='" + getCauseForAnIncident() + "'" +
            ", effect='" + getEffect() + "'" +
            ", recommendationsDrawn='" + getRecommendationsDrawn() + "'" +
            ", positionJG='" + getPositionJG() + "'" +
            ", nameIdNo='" + getNameIdNo() + "'" +
            ", actionInvolved='" + getActionInvolved() + "'" +
            ", ngScreenerReport='" + getNgScreenerReport() + "'" +
            ", statusAfterReporting='" + getStatusAfterReporting() + "'" +
            "}";
    }
}
