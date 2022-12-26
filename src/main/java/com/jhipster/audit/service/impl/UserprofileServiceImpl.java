package com.jhipster.audit.service.impl;

import com.jhipster.audit.domain.Userprofile;
import com.jhipster.audit.repository.UserprofileRepository;
import com.jhipster.audit.service.UserprofileService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Service Implementation for managing {@link Userprofile}.
 */
@Service
public class UserprofileServiceImpl implements UserprofileService {

    private final Logger log = LoggerFactory.getLogger(UserprofileServiceImpl.class);

    private final UserprofileRepository userprofileRepository;

    public UserprofileServiceImpl(UserprofileRepository userprofileRepository) {
        this.userprofileRepository = userprofileRepository;
    }

    @Override
    public Userprofile save(Userprofile userprofile) {
        log.debug("Request to save Userprofile : {}", userprofile);
        return userprofileRepository.save(userprofile);
    }

    @Override
    public Userprofile update(Userprofile userprofile) {
        log.debug("Request to update Userprofile : {}", userprofile);
        return userprofileRepository.save(userprofile);
    }

    @Override
    public Optional<Userprofile> partialUpdate(Userprofile userprofile) {
        log.debug("Request to partially update Userprofile : {}", userprofile);

        return userprofileRepository
            .findById(userprofile.getId())
            .map(existingUserprofile -> {
                if (userprofile.getRegionId() != null) {
                    existingUserprofile.setRegionId(userprofile.getRegionId());
                }
                if (userprofile.getBranchId() != null) {
                    existingUserprofile.setBranchId(userprofile.getBranchId());
                }
                if (userprofile.getDepartmentId() != null) {
                    existingUserprofile.setDepartmentId(userprofile.getDepartmentId());
                }
                if (userprofile.getDivisionId() != null) {
                    existingUserprofile.setDivisionId(userprofile.getDivisionId());
                }
                if (userprofile.getUserStatus() != null) {
                    existingUserprofile.setUserStatus(userprofile.getUserStatus());
                }

                return existingUserprofile;
            })
            .map(userprofileRepository::save);
    }

    @Override
    public List<Userprofile> findAll() {
        log.debug("Request to get all Userprofiles");
        return userprofileRepository.findAll();
    }

    @Override
    public Optional<Userprofile> findOne(String id) {
        log.debug("Request to get Userprofile : {}", id);
        return userprofileRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete Userprofile : {}", id);
        userprofileRepository.deleteById(id);
    }
}
