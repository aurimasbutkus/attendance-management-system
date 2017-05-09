package com.ems.projectsinfo;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Martynas on 5/6/2017.
 */
@Entity
@Table(name = "subtask")
public class Subtask {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotNull
    private String description;
    @NotNull
    private Integer status;
    @NotNull
    private Integer fkTask;

    public Subtask() {

    }

    public Subtask(String description, Integer status, Integer fkTask) {
        this.description = description;
        this.status = status;
        this.fkTask = fkTask;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getFkTask() {
        return fkTask;
    }

    public void setFkTask(Integer fkTask) {
        this.fkTask = fkTask;
    }
}
