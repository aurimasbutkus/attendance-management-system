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
            String SQL = "insert into project (name, description, start_date, end_date, deadline) values (?, ?, ?, ?, ?)";
            jdbcTemplateObject.update( SQL, project.getName(), project.getDescription(), project.getStartDate(), project.getEndDate(), project.getDeadline());
            System.out.println("Created Project = " +  project.getName());
        }
        else System.out.println("Project name already exists! = " + project.getName());
    }

    @Override
    public void create(String name, String description, Date start_date) {
        if(!projectExists(name)){
            if(start_date == null) start_date = new Date(System.currentTimeMillis());
            String SQL = "insert into project (name, description, start_date, deadline) values (?, ?, ?, ?)";
            jdbcTemplateObject.update( SQL, name, description, start_date, null);
            System.out.println("Created Project = " + name);
        }
        else System.out.println("Project name already exists! = " + name);
    }

    @Override
    public void create(String name, String description, Date start_date, Date deadline) {
        if(!projectExists(name)){
            if(start_date == null) start_date = new Date(System.currentTimeMillis());
            String SQL = "insert into project (name, description, start_date, deadline) values (?, ?, ?, ?)";
            jdbcTemplateObject.update( SQL, name, description, start_date, deadline);
            System.out.println("Created Project = " + name);
        }
        else System.out.println("Project name already exists! = " + name);
    }

    @Override
    public void createTask(Task task)
    {
        String SQL = "insert into task (description, creation_date, deadline, completion_date, fk_Project) values (?, ?, ?, ?, ?)";
        jdbcTemplateObject.update(SQL, task.getDescription(), task.getCreationDate(), task.getDeadline(), task.getCompletionDate(), task.getFkProject());
    }

    @Override
    public void removeTask(Integer id)
    {
        String SQL = "delete from task where task.id = ?";
        jdbcTemplateObject.update(SQL, id);
        System.out.println("Deleted task with id = " + id );
    }

    @Override
    public boolean projectExists(Integer id) {
        String SQL = "select * from project where id = ?";
        return id != null && id != 0 && validate(SQL, id);
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
    public Project getProject(Integer id) {
        String SQL = "select * from project where id = ?";
        try {
            return jdbcTemplateObject.queryForObject(SQL, new Object[]{id}, new ProjectMapper());
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
        String SQL = "select project.* from project, account, team, project_teams where account.fk_Team = team.id" +
                " and project_teams.fk_Team = team.id and project_teams.fk_Project = project.id and account.id = ?";
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
        String SQL = "delete from project where id = ?";
        jdbcTemplateObject.update(SQL, id);
        System.out.println("Deleted Record with ID = " + id );
    }
    @Override
    public void delete(String name) {
        String SQL = "delete from project where name = ?";
        jdbcTemplateObject.update(SQL, name);
        System.out.println("Deleted Record with name = " + name );
    }
    @Override
    public void updateEverything(Project project){
        String SQL = "update project set name = ?, description = ?, start_date = ?," +
                " end_date = ?, deadline = ? where id = ?";
        jdbcTemplateObject.update(SQL, project.getName(), project.getDescription(), project.getStartDate(),
        project.getEndDate(), project.getDeadline(), project.getId());
        System.out.println("Updated project info with id: " + project.getId() );
    }

}