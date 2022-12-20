package com.jhipster.audit.service.impl;

import com.jhipster.audit.domain.FraudInvestigationReport;
import com.jhipster.audit.repository.FraudInvestigationReportRepository;
import com.jhipster.audit.service.FraudInvestigationReportService;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Service Implementation for managing {@link FraudInvestigationReport}.
 */
@Service
public class FraudInvestigationReportServiceImpl implements FraudInvestigationReportService {

    private final Logger log = LoggerFactory.getLogger(FraudInvestigationReportServiceImpl.class);

    private final FraudInvestigationReportRepository fraudInvestigationReportRepository;

    public FraudInvestigationReportServiceImpl(FraudInvestigationReportRepository fraudInvestigationReportRepository) {
        this.fraudInvestigationReportRepository = fraudInvestigationReportRepository;
    }

    @Override
    public FraudInvestigationReport save(FraudInvestigationReport fraudInvestigationReport) {
        log.debug("Request to save FraudInvestigationReport : {}", fraudInvestigationReport);
        return fraudInvestigationReportRepository.save(fraudInvestigationReport);
    }

    @Override
    public FraudInvestigationReport update(FraudInvestigationReport fraudInvestigationReport) {
        log.debug("Request to update FraudInvestigationReport : {}", fraudInvestigationReport);
        return fraudInvestigationReportRepository.save(fraudInvestigationReport);
    }

    @Override
    public Optional<FraudInvestigationReport> partialUpdate(FraudInvestigationReport fraudInvestigationReport) {
        log.debug("Request to partially update FraudInvestigationReport : {}", fraudInvestigationReport);

        return fraudInvestigationReportRepository
            .findById(fraudInvestigationReport.getId())
            .map(existingFraudInvestigationReport -> {
                if (fraudInvestigationReport.getTitle() != null) {
                    existingFraudInvestigationReport.setTitle(fraudInvestigationReport.getTitle());
                }
                if (fraudInvestigationReport.getExecutiveSummary() != null) {
                    existingFraudInvestigationReport.setExecutiveSummary(fraudInvestigationReport.getExecutiveSummary());
                }
                if (fraudInvestigationReport.getIntroduction() != null) {
                    existingFraudInvestigationReport.setIntroduction(fraudInvestigationReport.getIntroduction());
                }
                if (fraudInvestigationReport.getObjective() != null) {
                    existingFraudInvestigationReport.setObjective(fraudInvestigationReport.getObjective());
                }
                if (fraudInvestigationReport.getScope() != null) {
                    existingFraudInvestigationReport.setScope(fraudInvestigationReport.getScope());
                }
                if (fraudInvestigationReport.getLimitation() != null) {
                    existingFraudInvestigationReport.setLimitation(fraudInvestigationReport.getLimitation());
                }
                if (fraudInvestigationReport.getMethodology() != null) {
                    existingFraudInvestigationReport.setMethodology(fraudInvestigationReport.getMethodology());
                }
                if (fraudInvestigationReport.getFindingAndAnalysis() != null) {
                    existingFraudInvestigationReport.setFindingAndAnalysis(fraudInvestigationReport.getFindingAndAnalysis());
                }
                if (fraudInvestigationReport.getConclusion() != null) {
                    existingFraudInvestigationReport.setConclusion(fraudInvestigationReport.getConclusion());
                }
                if (fraudInvestigationReport.getRecommendation() != null) {
                    existingFraudInvestigationReport.setRecommendation(fraudInvestigationReport.getRecommendation());
                }
                if (fraudInvestigationReport.getNameOfMembers() != null) {
                    existingFraudInvestigationReport.setNameOfMembers(fraudInvestigationReport.getNameOfMembers());
                }
                if (fraudInvestigationReport.getSignature() != null) {
                    existingFraudInvestigationReport.setSignature(fraudInvestigationReport.getSignature());
                }
                if (fraudInvestigationReport.getAnnexes() != null) {
                    existingFraudInvestigationReport.setAnnexes(fraudInvestigationReport.getAnnexes());
                }
                if (fraudInvestigationReport.getAuthor() != null) {
                    existingFraudInvestigationReport.setAuthor(fraudInvestigationReport.getAuthor());
                }

                return existingFraudInvestigationReport;
            })
            .map(fraudInvestigationReportRepository::save);
    }

    @Override
    public Page<FraudInvestigationReport> findAll(Pageable pageable) {
        log.debug("Request to get all FraudInvestigationReports");
        return fraudInvestigationReportRepository.findAll(pageable);
    }

    @Override
    public Optional<FraudInvestigationReport> findOne(String id) {
        log.debug("Request to get FraudInvestigationReport : {}", id);
        return fraudInvestigationReportRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete FraudInvestigationReport : {}", id);
        fraudInvestigationReportRepository.deleteById(id);
    }
}
