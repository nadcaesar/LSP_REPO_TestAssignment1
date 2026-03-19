package org.howard.edu.lsp.midterm.crccards;
/**
 * Represents a single task in the Task Management System.
 * @author Nicholas Caesar
 */
public class Task {
    // Fields
    private String taskId;
    private String description;
    private String status;

    // Constructor
    /**
     * Constructs a Task with a given ID and description.
     * Default status is set to "OPEN".
     * @param taskId the unique identifier for the task
     * @param description a brief description of the task
     */
    public Task(String taskId, String description) {
        // What goes here?
        // Remember: default status must be "OPEN"
        this.taskId = taskId;
        this.description = description;
        this.status = "OPEN"; //Default status
    }

    // Getters
    /**
     * Returns the task ID.
     * @return taskId
     */
    public String getTaskId() {
        return taskId;
    }

    /**
     * Returns the task description.
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the task status.
     * @return status
     */

    public String getStatus() {
        return status;
    }

    // Setter
    /**
     * Sets the task status if valid, otherwise sets it to "UNKNOWN".
     * Valid values are: OPEN, IN_PROGRESS, COMPLETE.
     * @param status the new status to set
     */
    public void setStatus(String status) {
        // Valid values: OPEN, IN_PROGRESS, COMPLETE
        // Anything else → set to "UNKNOWN"
        // What logic goes here?
        if (status.equals("OPEN") || status.equals("IN_PROGRESS") || status.equals("COMPLETE")) {
            this.status = status;
        } 
        else {
            this.status = "UNKNOWN";
        }

    }

    // toString
    /**
     * Returns a string representation of the task.
     * Format: taskId description [status]
     * @return formatted task string
     */
    public String toString() {
        // Must produce: T1 Write report [OPEN]
        // What goes here?
        return taskId + " " + description + " [" + status + "]";
    }
}