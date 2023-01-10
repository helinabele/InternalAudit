package com.jhipster.audit.service.impl;

import com.jhipster.audit.domain.Division;
import com.jhipster.audit.repository.DivisionRepository;
import com.jhipster.audit.service.DivisionService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Service Implementation for managing {@link Division}.
 */
@Service
public class DivisionServiceImpl implements DivisionService {

    private final Logger log = LoggerFactory.getLogger(DivisionServiceImpl.class);

    private final DivisionRepository divisionRepository;

    public DivisionServiceImpl(DivisionRepository divisionRepository) {
        this.divisionRepository = divisionRepository;
    }

    @Override
    public Division save(Division division) {
        log.debug("Request to save Division : {}", division);
        return divisionRepository.save(division);
    }

    @Override
    public Division update(Division division) {
        log.debug("Request to update Division : {}", division);
        return divisionRepository.save(division);
    }

    @Override
    public Optional<Division> partialUpdate(Division division) {
        log.debug("Request to partially update Division : {}", division);

        return divisionRepository
            .findById(division.getId())
            .map(existingDivision -> {
                if (division.getDescription() != null) {
                    existingDivision.setDescription(division.getDescription());
                }
                if (division.getDivisionId() != null) {
                    existingDivision.setDivisionId(division.getDivisionId());
                }
                if (division.getDivisionName() != null) {
                    existingDivision.setDivisionName(division.getDivisionName());
                }

                return existingDivision;
            })
            .map(divisionRepository::save);
    }

    @Override
    public List<Division> findAll() {
        log.debug("Request to get all Divisions");
        return divisionRepository.findAll();
    }

    public Page<Division> findAllWithEagerRelationships(Pageable pageable) {
        return divisionRepository.findAllWithEagerRelationships(pageable);
    }

    @Override
    public Optional<Division> findOne(String id) {
        log.debug("Request to get Division : {}", id);
        return divisionRepository.findOneWithEagerRelationships(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete Division : {}", id);
        divisionRepository.deleteById(id);
    }
}
