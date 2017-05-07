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
    private Integer subtask_id;
    @NotNull
    private String description;
    @NotNull
    private Integer status;
    @NotNull
    private Integer fk_Task;

    public Subtask() {

    }

    public Subtask(String description, Integer status, Integer fk_Task) {
        this.description = description;
        this.status = status;
        this.fk_Task = fk_Task;
    }

    public Integer getSubtask_id() {
        return subtask_id;
    }

    public void setSubtask_id(Integer subtask_id) {
        this.subtask_id = subtask_id;
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

    public Integer getFk_Task() {
        return fk_Task;
    }

    public void setFk_Task(Integer fk_Task) {
        this.fk_Task = fk_Task;
    }
}
