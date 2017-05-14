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



    void create(Task task);
    void create(Subtask subtask);
    Project getProject(Integer id);
    Project getProject(String name);
    Project getProject(Project project);
    Task getTask(Integer id);
    Subtask getSubtask(Integer id);
    List<Project> listAllProjects();
    List<Project> listAllUserProjects(Integer id);
    List<Task> listAllTasks();
    List<Task> listAllProjectTasks(Integer id);
    List<Subtask> listAllSubtasks();
    void delete(Integer id);
    void deleteTask(Integer id);
    void deleteSubtask(Integer id);
    void delete(String username);

    void createTask(Task task);
    void removeTask(Integer id);

    boolean projectExists(Integer id);
    boolean projectExists(String name);
    boolean projectExists(Project project);

    void updateEverything(Project project);
    void updateEverything(Task task);
    void updateEverything(Subtask subtask);
}
