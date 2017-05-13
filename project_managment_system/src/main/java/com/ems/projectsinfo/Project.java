package com.ems.projectsinfo;

import com.sun.istack.internal.Nullable;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
//import java.sql.Date;
import java.util.Date;

/**
 * Created by Martynas on 5/5/2017.
 */
@Entity
@Table(name = "project")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotNull
    private String name;
    @NotNull
    private String description;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    private Date startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Nullable
    private Date endDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Nullable
    private Date deadline;

    public Project() {
        this.name = "";
        this.description = "";
        this.startDate = null;
        this.endDate = null;
    }

    public Project(String name, String description, @DateTimeFormat(pattern = "yyyy-MM-dd")Date startDate, @DateTimeFormat(pattern = "yyyy-MM-dd") Date deadline) {
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.deadline = deadline;
    }
    public Project(String name, String description,@DateTimeFormat(pattern = "yyyy-MM-dd")Date startDate) {
        this.name = name;
        this.description = description;
        this.startDate = startDate;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(@DateTimeFormat(pattern = "yyyy-MM-dd")Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(@DateTimeFormat(pattern = "yyyy-MM-dd")Date endDate) {
        this.endDate = endDate;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(@DateTimeFormat(pattern = "yyyy-MM-dd")Date deadline) {
        this.deadline = deadline;
    }
}
