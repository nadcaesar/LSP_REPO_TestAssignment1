package org.howard.edu.lsp.midterm.crccards;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Manages a collection of Task objects.
 * Uses a HashMap to store tasks by their unique task ID.
 * @author Nicholas Caesar
 */
public class TaskManager {
    

    // What goes here for the HashMap declaration?
     private HashMap<String, Task> tasks = new HashMap<>();

    /**
     * Adds a new task to the manager.
     * Throws IllegalArgumentException if task ID already exists.
     * @param task the Task object to add
     */

    
    public void addTask(Task task) {
        // 1. Check if taskId already exists → throw IllegalArgumentException
        // 2. If not, add it to the HashMap
        // What's the key? What's the value?
        if (tasks.containsKey(task.getTaskId())) {
            throw new IllegalArgumentException(
                "Duplicate task ID: " + task.getTaskId()
            );
        }
        tasks.put(task.getTaskId(), task);
    }

    /**
     * Finds a task by its ID.
     * Returns null if the task is not found.
     * @param taskId the ID to search for
     * @return the matching Task, or null if not found
     */
    public Task findTask(String taskId) {
        // Look up taskId in HashMap
        // If found → return the Task
        // If not found → return null
        return tasks.get(taskId);

    }


    /**
     * Returns all tasks matching the given status.
     * @param status the status to filter by
     * @return a List of matching Task objects
     */
    public List<Task> getTasksByStatus(String status) {
        // Loop through ALL tasks in the HashMap
        // If a task's status matches → add it to a results list
        // Return the results list
        List<Task> results = new ArrayList<>();
        for (Task task : tasks.values()) {
            if (task.getStatus().equals(status)) {
                results.add(task);
            }
        }
        return results;
    }
}