package com.ems.projectsinfo;

import java.util.Date;
import java.util.List;

/**
 * Created by Martynas on 5/5/2017.
 */
public interface ProjectService {

    void create(String name, String description, Date start_date, Date end_date);
    void create(String name, String description, Date start_date);
    void create(Project project);
    Project getProject(Integer id);
    Project getProject(String name);
    Project getProject(Project project);
    List<Project> listAllProjects();
    List<Project> listAllUserProjects(Integer id);
    List<Task> listAllTasks();
    List<Subtask> listAllSubtasks();
    void delete(Integer id);
    void delete(String username);


    boolean projectExists(Integer project_id);
    boolean projectExists(String name);
    boolean projectExists(Project project);


}
