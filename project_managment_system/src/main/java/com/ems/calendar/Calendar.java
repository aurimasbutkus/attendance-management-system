package com.ems.calendar;

import com.ems.projectsinfo.Task;

import java.util.List;

/**
 * Created by JIM on 07/05/2017.
 */
public class Calendar {

    private List<Task> tasks;

    public Calendar(List<Task> tasks) {
        setTasks(tasks);
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
