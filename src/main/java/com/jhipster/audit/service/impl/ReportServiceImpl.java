package com.jhipster.audit.service.impl;

import com.jhipster.audit.domain.Report;
import com.jhipster.audit.repository.ReportRepository;
import com.jhipster.audit.service.ReportService;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Service Implementation for managing {@link Report}.
 */
@Service
public class ReportServiceImpl implements ReportService {

    private final Logger log = LoggerFactory.getLogger(ReportServiceImpl.class);

    private final ReportRepository reportRepository;

    public ReportServiceImpl(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    @Override
    public Report save(Report report) {
        log.debug("Request to save Report : {}", report);
        return reportRepository.save(report);
    }

    @Override
    public Report update(Report report) {
        log.debug("Request to update Report : {}", report);
        return reportRepository.save(report);
    }

    @Override
    public Optional<Report> partialUpdate(Report report) {
        log.debug("Request to partially update Report : {}", report);

        return reportRepository
            .findById(report.getId())
            .map(existingReport -> {
                if (report.getTitle() != null) {
                    existingReport.setTitle(report.getTitle());
                }
                if (report.getExecutiveSummary() != null) {
                    existingReport.setExecutiveSummary(report.getExecutiveSummary());
                }
                if (report.getIntroduction() != null) {
                    existingReport.setIntroduction(report.getIntroduction());
                }
                if (report.getObjective() != null) {
                    existingReport.setObjective(report.getObjective());
                }
                if (report.getScope() != null) {
                    existingReport.setScope(report.getScope());
                }
                if (report.getLimitation() != null) {
                    existingReport.setLimitation(report.getLimitation());
                }
                if (report.getMethodology() != null) {
                    existingReport.setMethodology(report.getMethodology());
                }
                if (report.getFindingAndAnalysis() != null) {
                    existingReport.setFindingAndAnalysis(report.getFindingAndAnalysis());
                }
                if (report.getConclusion() != null) {
                    existingReport.setConclusion(report.getConclusion());
                }
                if (report.getRecommendation() != null) {
                    existingReport.setRecommendation(report.getRecommendation());
                }
                if (report.getNameOfMembers() != null) {
                    existingReport.setNameOfMembers(report.getNameOfMembers());
                }
                if (report.getSignature() != null) {
                    existingReport.setSignature(report.getSignature());
                }
                if (report.getAnnexes() != null) {
                    existingReport.setAnnexes(report.getAnnexes());
                }
                if (report.getAuthor() != null) {
                    existingReport.setAuthor(report.getAuthor());
                }

                return existingReport;
            })
            .map(reportRepository::save);
    }

    @Override
    public Page<Report> findAll(Pageable pageable) {
        log.debug("Request to get all Reports");
        return reportRepository.findAll(pageable);
    }

    @Override
    public Optional<Report> findOne(String id) {
        log.debug("Request to get Report : {}", id);
        return reportRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete Report : {}", id);
        reportRepository.deleteById(id);
    }
}
