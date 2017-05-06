package com.ems.projectsinfo;

import com.sun.istack.internal.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;

/**
 * Created by Martynas on 5/6/2017.
 */
@Entity
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer task_id;
    @NotNull
    private String description;
    @NotNull
    private Date creation_date;
    @Nullable
    private Date deadline;
    @Nullable
    private Date completion_date;
    @NotNull
    private Integer fk_Project;

    public Task() {

    }

    public Task(String description, Date creation_date, Date deadline, Date completion_date, Integer fk_Project) {
        this.description = description;
        this.creation_date = creation_date;
        this.deadline = deadline;
        this.completion_date = completion_date;
        this.fk_Project = fk_Project;
    }

    public Task(String description, Date creation_date, Date deadline, Integer fk_Project) {
        this.description = description;
        this.creation_date = creation_date;
        this.deadline = deadline;
        this.fk_Project = fk_Project;
    }

    public Integer getTask_id() {
        return task_id;
    }

    public void setTask_id(Integer task_id) {
        this.task_id = task_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(Date creation_date) {
        this.creation_date = creation_date;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public Date getCompletion_date() {
        return completion_date;
    }

    public void setCompletion_date(Date completion_date) {
        this.completion_date = completion_date;
    }

    public Integer getFk_Project() {
        return fk_Project;
    }

    public void setFk_Project(Integer fk_Project) {
        this.fk_Project = fk_Project;
    }
}
