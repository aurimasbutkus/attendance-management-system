package com.ems.projectsinfo;

/**
 * Created by Martynas on 2017-05-05.
 */


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class ProjectJDBC implements ProjectService {

    @Autowired
    private JdbcTemplate jdbcTemplateObject;

    @Override
    public void create(Project project) {
        if(!projectExists(project)){
            String SQL = "insert into project (name, description, start_date, end_date) values (?, ?, ?, ?)";
            jdbcTemplateObject.update( SQL, project.getName(), project.getDescription(), project.getStart_date(), project.getEnd_date());
            System.out.println("Created Project = " +  project.getName());
        }
        else System.out.println("Project name already exists! = " + project.getName());
    }

    @Override
    public void create(String name, String description, Date start_date) {
        if(!projectExists(name)){
            String SQL = "insert into project (name, description, start_date, end_date) values (?, ?, ?, ?)";
            jdbcTemplateObject.update( SQL, name, description, start_date, null);
            System.out.println("Created Project = " + name);
        }
        else System.out.println("Project name already exists! = " + name);
    }

    @Override
    public void create(String name, String description, Date start_date, Date end_date) {
        if(!projectExists(name)){
            String SQL = "insert into project (name, description, start_date, end_date) values (?, ?, ?, ?)";
            jdbcTemplateObject.update( SQL, name, description, start_date, end_date);
            System.out.println("Created Project = " + name);
        }
        else System.out.println("Project name already exists! = " + name);
    }

    @Override
    public boolean projectExists(Integer project_id) {
        String SQL = "select * from project where project_id = ?";
        return project_id != null && project_id != 0 && validate(SQL, project_id);
    }
    @Override
    public boolean projectExists(String name) {
        String SQL = "select * from project where name = ?";
        return name != null && validate(SQL, name);
    }
    @Override
    public boolean projectExists(Project project) {
        String SQL = "select * from project where name = ?";
        return project.getName() != null && validate(SQL, project.getName());
    }
    private boolean validate(String SQL, Object o){
        try {
            jdbcTemplateObject.queryForObject(SQL, new Object[]{o}, new ProjectMapper());
            return true;
        }
        catch (EmptyResultDataAccessException e) {
            return false;
        }
    }
    @Override
    public Project getProject(Integer project_id) {
        String SQL = "select * from project where project_id = ?";
        try {
            return jdbcTemplateObject.queryForObject(SQL, new Object[]{project_id}, new ProjectMapper());
        }
        catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    @Override
    public Project getProject(String name) {
        String SQL = "select * from project where name = ?";
        try {
            return jdbcTemplateObject.queryForObject(SQL, new Object[]{name}, new ProjectMapper());
        }
        catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    @Override
    public Project getProject(Project project) {
        String SQL = "select * from project where name = ?";
        try {
            return jdbcTemplateObject.queryForObject(SQL, new Object[]{project.getName()}, new ProjectMapper());
        }
        catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    @Override
    public List<Project> listAllProjects() {
        String SQL = "select * from project";
        return jdbcTemplateObject.query(SQL, new ProjectMapper());
    }

    @Override
    public List<Project> listAllUserProjects(Integer id) {
        String SQL = "select project.* from project, account, team, project_teams where account.fk_Team = team.team_id and project_teams.fk_Team = team.team_id and project_teams.fk_Project = project.project_id and account.id = ?";
        return jdbcTemplateObject.query(SQL, new Object[]{id}, new ProjectMapper());
    }

    @Override
    public List<Task> listAllTasks() {
        String SQL = "select * from task";
        return jdbcTemplateObject.query(SQL, new TaskMapper());
    }

    @Override
    public List<Task> listAllProjectTasks(Integer id) {
        String SQL = "select * from task where task.fk_Project = ?";
        return jdbcTemplateObject.query(SQL, new Object[]{id}, new TaskMapper());
    }

    @Override
    public List<Subtask> listAllSubtasks() {
        String SQL = "select * from subtask";
        return jdbcTemplateObject.query(SQL, new SubtaskMapper());
    }

    @Override
    public void delete(Integer id) {
        String SQL = "delete from project where project_id = ?";
        jdbcTemplateObject.update(SQL, id);
        System.out.println("Deleted Record with ID = " + id );
    }
    @Override
    public void delete(String name) {
        String SQL = "delete from project where name = ?";
        jdbcTemplateObject.update(SQL, name);
        System.out.println("Deleted Record with name = " + name );
    }

}