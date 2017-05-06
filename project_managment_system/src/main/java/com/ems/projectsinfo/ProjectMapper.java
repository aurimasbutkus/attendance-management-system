package com.ems.projectsinfo;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Martynas on 5/5/2017.
 */
public class ProjectMapper implements RowMapper<Project> {
    public Project mapRow(ResultSet rs, int rowNum) throws SQLException {
        Project project = new Project();
        project.setProject_id(null);
        project.setName(rs.getString("name"));
        project.setDescription(rs.getString("description"));
        project.setStart_date(rs.getDate("start_date"));
        project.setEnd_date(rs.getDate("end_date"));

        return project;
    }
}
