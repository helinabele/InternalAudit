package com.jhipster.audit.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.LocalDate;
import javax.validation.constraints.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A AssignTask.
 */
@Document(collection = "assign_task")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class AssignTask implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("title")
    private String title;

    @Field("assigned_person")
    private String assignedPerson;

    @Field("task_assignment_date")
    private LocalDate taskAssignmentDate;

    @Field("task_start_date")
    private LocalDate taskStartDate;

    @Field("task_due_date")
    private LocalDate taskDueDate;

    @DBRef
    private Task task;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public AssignTask id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public AssignTask title(String title) {
        this.setTitle(title);
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAssignedPerson() {
        return this.assignedPerson;
    }

    public AssignTask assignedPerson(String assignedPerson) {
        this.setAssignedPerson(assignedPerson);
        return this;
    }

    public void setAssignedPerson(String assignedPerson) {
        this.assignedPerson = assignedPerson;
    }

    public LocalDate getTaskAssignmentDate() {
        return this.taskAssignmentDate;
    }

    public AssignTask taskAssignmentDate(LocalDate taskAssignmentDate) {
        this.setTaskAssignmentDate(taskAssignmentDate);
        return this;
    }

    public void setTaskAssignmentDate(LocalDate taskAssignmentDate) {
        this.taskAssignmentDate = taskAssignmentDate;
    }

    public LocalDate getTaskStartDate() {
        return this.taskStartDate;
    }

    public AssignTask taskStartDate(LocalDate taskStartDate) {
        this.setTaskStartDate(taskStartDate);
        return this;
    }

    public void setTaskStartDate(LocalDate taskStartDate) {
        this.taskStartDate = taskStartDate;
    }

    public LocalDate getTaskDueDate() {
        return this.taskDueDate;
    }

    public AssignTask taskDueDate(LocalDate taskDueDate) {
        this.setTaskDueDate(taskDueDate);
        return this;
    }

    public void setTaskDueDate(LocalDate taskDueDate) {
        this.taskDueDate = taskDueDate;
    }

    public Task getTask() {
        return this.task;
    }

    public void setTask(Task task) {
        if (this.task != null) {
            this.task.setAssignTask(null);
        }
        if (task != null) {
            task.setAssignTask(this);
        }
        this.task = task;
    }

    public AssignTask task(Task task) {
        this.setTask(task);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AssignTask)) {
            return false;
        }
        return id != null && id.equals(((AssignTask) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AssignTask{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", assignedPerson='" + getAssignedPerson() + "'" +
            ", taskAssignmentDate='" + getTaskAssignmentDate() + "'" +
            ", taskStartDate='" + getTaskStartDate() + "'" +
            ", taskDueDate='" + getTaskDueDate() + "'" +
            "}";
    }
}
