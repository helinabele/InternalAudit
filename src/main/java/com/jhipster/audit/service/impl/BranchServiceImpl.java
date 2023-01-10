package com.jhipster.audit.service.impl;

import com.jhipster.audit.domain.Branch;
import com.jhipster.audit.repository.BranchRepository;
import com.jhipster.audit.service.BranchService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Service Implementation for managing {@link Branch}.
 */
@Service
public class BranchServiceImpl implements BranchService {

    private final Logger log = LoggerFactory.getLogger(BranchServiceImpl.class);

    private final BranchRepository branchRepository;

    public BranchServiceImpl(BranchRepository branchRepository) {
        this.branchRepository = branchRepository;
    }

    @Override
    public Branch save(Branch branch) {
        log.debug("Request to save Branch : {}", branch);
        return branchRepository.save(branch);
    }

    @Override
    public Branch update(Branch branch) {
        log.debug("Request to update Branch : {}", branch);
        return branchRepository.save(branch);
    }

    @Override
    public Optional<Branch> partialUpdate(Branch branch) {
        log.debug("Request to partially update Branch : {}", branch);

        return branchRepository
            .findById(branch.getId())
            .map(existingBranch -> {
                if (branch.getBranchName() != null) {
                    existingBranch.setBranchName(branch.getBranchName());
                }
                if (branch.getBranchId() != null) {
                    existingBranch.setBranchId(branch.getBranchId());
                }
                if (branch.getDescription() != null) {
                    existingBranch.setDescription(branch.getDescription());
                }

                return existingBranch;
            })
            .map(branchRepository::save);
    }

    @Override
    public List<Branch> findAll() {
        log.debug("Request to get all Branches");
        return branchRepository.findAll();
    }

    public Page<Branch> findAllWithEagerRelationships(Pageable pageable) {
        return branchRepository.findAllWithEagerRelationships(pageable);
    }

    @Override
    public Optional<Branch> findOne(String id) {
        log.debug("Request to get Branch : {}", id);
        return branchRepository.findOneWithEagerRelationships(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete Branch : {}", id);
        branchRepository.deleteById(id);
    }
}
