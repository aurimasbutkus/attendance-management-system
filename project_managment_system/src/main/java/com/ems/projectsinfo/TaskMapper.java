package com.ems.projectsinfo;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Martynas on 5/6/2017.
 */
public class TaskMapper implements RowMapper<Task> {
    public Task mapRow(ResultSet rs, int rowNum) throws SQLException {
        Task task = new Task();
        task.setId(rs.getInt("id"));
        task.setDescription(rs.getString("description"));
        task.setCreationDate(rs.getDate("creation_date"));
        task.setDeadline(rs.getDate("deadline"));
        task.setCompletionDate(rs.getDate("completion_date"));
        task.setFkProject(rs.getInt("fk_Project"));
        return task;
    }
}
