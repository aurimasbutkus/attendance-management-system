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
        task.setTask_id(null);
        task.setDescription(rs.getString("description"));
        task.setCreation_date(rs.getDate("creation_date"));
        task.setDeadline(rs.getDate("deadline"));
        task.setCompletion_date(rs.getDate("completion_date"));
        task.setFk_Project(rs.getInt("fk_Project"));
        return task;
    }
}
