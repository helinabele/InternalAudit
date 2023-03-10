package com.jhipster.audit.service.impl;

import com.jhipster.audit.domain.Region;
import com.jhipster.audit.repository.RegionRepository;
import com.jhipster.audit.service.RegionService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Service Implementation for managing {@link Region}.
 */
@Service
public class RegionServiceImpl implements RegionService {

    private final Logger log = LoggerFactory.getLogger(RegionServiceImpl.class);

    private final RegionRepository regionRepository;

    public RegionServiceImpl(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    @Override
    public Region save(Region region) {
        log.debug("Request to save Region : {}", region);
        return regionRepository.save(region);
    }

    @Override
    public Region update(Region region) {
        log.debug("Request to update Region : {}", region);
        return regionRepository.save(region);
    }

    @Override
    public Optional<Region> partialUpdate(Region region) {
        log.debug("Request to partially update Region : {}", region);

        return regionRepository
            .findById(region.getId())
            .map(existingRegion -> {
                if (region.getRegionName() != null) {
                    existingRegion.setRegionName(region.getRegionName());
                }
                if (region.getRegionId() != null) {
                    existingRegion.setRegionId(region.getRegionId());
                }
                if (region.getDescription() != null) {
                    existingRegion.setDescription(region.getDescription());
                }

                return existingRegion;
            })
            .map(regionRepository::save);
    }

    @Override
    public List<Region> findAll() {
        log.debug("Request to get all Regions");
        return regionRepository.findAll();
    }

    public Page<Region> findAllWithEagerRelationships(Pageable pageable) {
        return regionRepository.findAllWithEagerRelationships(pageable);
    }

    @Override
    public Optional<Region> findOne(String id) {
        log.debug("Request to get Region : {}", id);
        return regionRepository.findOneWithEagerRelationships(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete Region : {}", id);
        regionRepository.deleteById(id);
    }
}
