package com.ems.teaminfo;

import com.sun.istack.internal.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Martynas on 5/7/2017.
 */
@Entity
@Table(name = "team")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer team_id;
    @Nullable
    private String description;
    @NotNull
    private String name;

    public Team()
    {

    }

    public Team(String name, String description)
    {
        this.name = name;
        this.description = description;
    }

    public Team(String name)
    {
        this.name = name;
    }

    public Integer getTeam_id() {
        return team_id;
    }

    public void setTeam_id(Integer team_id) {
        this.team_id = team_id;
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
}
