package com.jhipster.audit.service.impl;

import com.jhipster.audit.domain.AssignTask;
import com.jhipster.audit.repository.AssignTaskRepository;
import com.jhipster.audit.service.AssignTaskService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Service Implementation for managing {@link AssignTask}.
 */
@Service
public class AssignTaskServiceImpl implements AssignTaskService {

    private final Logger log = LoggerFactory.getLogger(AssignTaskServiceImpl.class);

    private final AssignTaskRepository assignTaskRepository;

    public AssignTaskServiceImpl(AssignTaskRepository assignTaskRepository) {
        this.assignTaskRepository = assignTaskRepository;
    }

    @Override
    public AssignTask save(AssignTask assignTask) {
        log.debug("Request to save AssignTask : {}", assignTask);
        return assignTaskRepository.save(assignTask);
    }

    @Override
    public AssignTask update(AssignTask assignTask) {
        log.debug("Request to update AssignTask : {}", assignTask);
        return assignTaskRepository.save(assignTask);
    }

    @Override
    public Optional<AssignTask> partialUpdate(AssignTask assignTask) {
        log.debug("Request to partially update AssignTask : {}", assignTask);

        return assignTaskRepository
            .findById(assignTask.getId())
            .map(existingAssignTask -> {
                if (assignTask.getTitle() != null) {
                    existingAssignTask.setTitle(assignTask.getTitle());
                }
                if (assignTask.getAssignedPerson() != null) {
                    existingAssignTask.setAssignedPerson(assignTask.getAssignedPerson());
                }
                if (assignTask.getTaskAssignmentDate() != null) {
                    existingAssignTask.setTaskAssignmentDate(assignTask.getTaskAssignmentDate());
                }
                if (assignTask.getTaskStartDate() != null) {
                    existingAssignTask.setTaskStartDate(assignTask.getTaskStartDate());
                }
                if (assignTask.getTaskDueDate() != null) {
                    existingAssignTask.setTaskDueDate(assignTask.getTaskDueDate());
                }

                return existingAssignTask;
            })
            .map(assignTaskRepository::save);
    }

    @Override
    public List<AssignTask> findAll() {
        log.debug("Request to get all AssignTasks");
        return assignTaskRepository.findAll();
    }

    /**
     *  Get all the assignTasks where Task is {@code null}.
     *  @return the list of entities.
     */

    public List<AssignTask> findAllWhereTaskIsNull() {
        log.debug("Request to get all assignTasks where Task is null");
        return StreamSupport
            .stream(assignTaskRepository.findAll().spliterator(), false)
            .filter(assignTask -> assignTask.getTask() == null)
            .collect(Collectors.toList());
    }

    @Override
    public Optional<AssignTask> findOne(String id) {
        log.debug("Request to get AssignTask : {}", id);
        return assignTaskRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete AssignTask : {}", id);
        assignTaskRepository.deleteById(id);
    }
}
