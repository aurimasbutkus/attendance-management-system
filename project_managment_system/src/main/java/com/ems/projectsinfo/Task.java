package com.ems.projectsinfo;

import com.sun.istack.internal.Nullable;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by Martynas on 5/6/2017.
 */
@Entity
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotNull
    private String description;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    private Date creationDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Nullable
    private Date deadline;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Nullable
    private Date completionDate;
    @NotNull
    private Integer fkProject;

    public Task() {

    }

    public Task(String description, Date creationDate, @DateTimeFormat(pattern = "yyyy-MM-dd")Date deadline, Date completionDate, Integer fkProject) {
        this.description = description;
        this.creationDate = creationDate;
        this.deadline = deadline;
        this.completionDate = completionDate;
        this.fkProject = fkProject;
    }

    public Task(String description, Date creationDate, @DateTimeFormat(pattern = "yyyy-MM-dd")Date deadline, Integer fkProject) {
        this.description = description;
        this.creationDate = creationDate;
        this.deadline = deadline;
        this.fkProject = fkProject;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(@DateTimeFormat(pattern = "yyyy-MM-dd")Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(@DateTimeFormat(pattern = "yyyy-MM-dd")Date deadline) {
        this.deadline = deadline;
    }

    public Date getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(@DateTimeFormat(pattern = "yyyy-MM-dd")Date completionDate) {
        this.completionDate = completionDate;
    }

    public Integer getFkProject() {
        return fkProject;
    }

    public void setFkProject(Integer fkProject) {
        this.fkProject = fkProject;
    }
}
