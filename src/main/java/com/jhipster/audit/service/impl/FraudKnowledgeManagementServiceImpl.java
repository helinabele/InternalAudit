package com.jhipster.audit.service.impl;

import com.jhipster.audit.domain.FraudKnowledgeManagement;
import com.jhipster.audit.repository.FraudKnowledgeManagementRepository;
import com.jhipster.audit.service.FraudKnowledgeManagementService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Service Implementation for managing {@link FraudKnowledgeManagement}.
 */
@Service
public class FraudKnowledgeManagementServiceImpl implements FraudKnowledgeManagementService {

    private final Logger log = LoggerFactory.getLogger(FraudKnowledgeManagementServiceImpl.class);

    private final FraudKnowledgeManagementRepository fraudKnowledgeManagementRepository;

    public FraudKnowledgeManagementServiceImpl(FraudKnowledgeManagementRepository fraudKnowledgeManagementRepository) {
        this.fraudKnowledgeManagementRepository = fraudKnowledgeManagementRepository;
    }

    @Override
    public FraudKnowledgeManagement save(FraudKnowledgeManagement fraudKnowledgeManagement) {
        log.debug("Request to save FraudKnowledgeManagement : {}", fraudKnowledgeManagement);
        return fraudKnowledgeManagementRepository.save(fraudKnowledgeManagement);
    }

    @Override
    public FraudKnowledgeManagement update(FraudKnowledgeManagement fraudKnowledgeManagement) {
        log.debug("Request to update FraudKnowledgeManagement : {}", fraudKnowledgeManagement);
        return fraudKnowledgeManagementRepository.save(fraudKnowledgeManagement);
    }

    @Override
    public Optional<FraudKnowledgeManagement> partialUpdate(FraudKnowledgeManagement fraudKnowledgeManagement) {
        log.debug("Request to partially update FraudKnowledgeManagement : {}", fraudKnowledgeManagement);

        return fraudKnowledgeManagementRepository
            .findById(fraudKnowledgeManagement.getId())
            .map(existingFraudKnowledgeManagement -> {
                if (fraudKnowledgeManagement.getReportNumber() != null) {
                    existingFraudKnowledgeManagement.setReportNumber(fraudKnowledgeManagement.getReportNumber());
                }
                if (fraudKnowledgeManagement.getCaseTitle() != null) {
                    existingFraudKnowledgeManagement.setCaseTitle(fraudKnowledgeManagement.getCaseTitle());
                }
                if (fraudKnowledgeManagement.getFraudType() != null) {
                    existingFraudKnowledgeManagement.setFraudType(fraudKnowledgeManagement.getFraudType());
                }
                if (fraudKnowledgeManagement.getUnit() != null) {
                    existingFraudKnowledgeManagement.setUnit(fraudKnowledgeManagement.getUnit());
                }
                if (fraudKnowledgeManagement.getIncidentDate() != null) {
                    existingFraudKnowledgeManagement.setIncidentDate(fraudKnowledgeManagement.getIncidentDate());
                }
                if (fraudKnowledgeManagement.getReportDate() != null) {
                    existingFraudKnowledgeManagement.setReportDate(fraudKnowledgeManagement.getReportDate());
                }
                if (fraudKnowledgeManagement.getInternalEmployee() != null) {
                    existingFraudKnowledgeManagement.setInternalEmployee(fraudKnowledgeManagement.getInternalEmployee());
                }
                if (fraudKnowledgeManagement.getExternalCustomer() != null) {
                    existingFraudKnowledgeManagement.setExternalCustomer(fraudKnowledgeManagement.getExternalCustomer());
                }
                if (fraudKnowledgeManagement.getFinancialLossAmount() != null) {
                    existingFraudKnowledgeManagement.setFinancialLossAmount(fraudKnowledgeManagement.getFinancialLossAmount());
                }
                if (fraudKnowledgeManagement.getCauseForAnIncident() != null) {
                    existingFraudKnowledgeManagement.setCauseForAnIncident(fraudKnowledgeManagement.getCauseForAnIncident());
                }
                if (fraudKnowledgeManagement.getEffect() != null) {
                    existingFraudKnowledgeManagement.setEffect(fraudKnowledgeManagement.getEffect());
                }
                if (fraudKnowledgeManagement.getRecommendationsDrawn() != null) {
                    existingFraudKnowledgeManagement.setRecommendationsDrawn(fraudKnowledgeManagement.getRecommendationsDrawn());
                }
                if (fraudKnowledgeManagement.getPositionJG() != null) {
                    existingFraudKnowledgeManagement.setPositionJG(fraudKnowledgeManagement.getPositionJG());
                }
                if (fraudKnowledgeManagement.getNameIdNo() != null) {
                    existingFraudKnowledgeManagement.setNameIdNo(fraudKnowledgeManagement.getNameIdNo());
                }
                if (fraudKnowledgeManagement.getActionInvolved() != null) {
                    existingFraudKnowledgeManagement.setActionInvolved(fraudKnowledgeManagement.getActionInvolved());
                }
                if (fraudKnowledgeManagement.getNgScreenerReport() != null) {
                    existingFraudKnowledgeManagement.setNgScreenerReport(fraudKnowledgeManagement.getNgScreenerReport());
                }
                if (fraudKnowledgeManagement.getStatusAfterReporting() != null) {
                    existingFraudKnowledgeManagement.setStatusAfterReporting(fraudKnowledgeManagement.getStatusAfterReporting());
                }

                return existingFraudKnowledgeManagement;
            })
            .map(fraudKnowledgeManagementRepository::save);
    }

    @Override
    public List<FraudKnowledgeManagement> findAll() {
        log.debug("Request to get all FraudKnowledgeManagements");
        return fraudKnowledgeManagementRepository.findAll();
    }

    @Override
    public Optional<FraudKnowledgeManagement> findOne(String id) {
        log.debug("Request to get FraudKnowledgeManagement : {}", id);
        return fraudKnowledgeManagementRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete FraudKnowledgeManagement : {}", id);
        fraudKnowledgeManagementRepository.deleteById(id);
    }
}
