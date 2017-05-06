package com.ems.projectsinfo;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;
//import java.util.Date;

/**
 * Created by Martynas on 5/5/2017.
 */
@Entity
@Table(name = "project")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer project_id;
    @NotNull
    private String name;
    @NotNull
    private String description;
    @NotNull
    private Date start_date;
    private Date end_date;

    public Project() {

    }

    public Project(String name, String description, Date start_date, Date end_date) {
        this.name = name;
        this.description = description;
        this.start_date = start_date;
        this.end_date = end_date;
    }
    public Project(String name, String description, Date start_date) {
        this.name = name;
        this.description = description;
        this.start_date = start_date;
    }



    public Integer getProject_id() {
        return project_id;
    }

    public void setProject_id(Integer project_id) {
        this.project_id = project_id;
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

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

}
