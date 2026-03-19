# Development Log — Question 1

## External Resources Used

### AI Tool: Claude (Anthropic)

This solution was completed with the assistance of Claude (claude.ai).
The complete conversation transcript used to help produce this solution is included below as required.

**Note:** The full conversation transcript from this study session should be pasted below this line.
All prompts and responses must be included per the assignment requirement.

---


So here is the midterm and I have permission from my professor to use AI and I wanted to know if you can help me out with it please6:40 PM2 / 2Let me read the midterm first.Read the midterm documentRead the midterm documentOkay I've read the full midterm. Great news — AI is explicitly allowed, and the professor even requires you to document your conversation transcript. So we can work through this together properly.
Here's what you're dealing with:

Midterm Overview
QuestionTopicPointsQ1Implement Task and TaskManager classes in Java40 ptsQ2Evaluate bad OO design + redesign with CRC cards30 ptsQ3Refactor PriceCalculator using Strategy Pattern30 ptsTotal100 pts

Important observations
Q2 is basically what you did today — evaluating bad OO design and writing CRC cards. You already know how to do this.
Q1 uses Java Collections — specifically HashMap is the perfect data structure for TaskManager since it needs to find tasks by ID and prevent duplicates. This connects directly to what we were just studying.
Q3 introduces the Strategy Pattern — this is a design pattern from your course resources. New concept but very learnable.

How I want to help you
Rather than just giving you the answers, I want to make sure you actually understand what you're submitting — especially since this is an exam. Let's go question by question, I'll explain the concepts, and we'll build the solution together.
Let's start with Q1 since it's worth the most points and uses Collections which we were just covering.
The key decision for TaskManager is which data structure to use. Based on the requirements:

Store tasks
Find a task by its ID quickly
Prevent duplicate task IDs

From the four Collections we looked at earlier — ArrayList, LinkedList, HashSet, HashMap — which one do you think fits best here and why?Task manager would go in HashMap because that collection avoids duplicates with unique keys for the key value pairs.6:52 PMExactly right — and your reasoning is solid too. The key-value pair structure of HashMap is perfect here because:

Key = taskId (unique, no duplicates)
Value = the Task object itself

So HashMap<String, Task> gives us fast lookup by ID and duplicate prevention automatically. That's exactly the kind of design decision examiners want to see.

Let's Build Q1 Step by Step
We'll do Task first since it's simpler and TaskManager depends on it.
Here's the structure you need to fill in. I've given you the skeleton — your job is to tell me what goes inside each method before I write it:
javapublic class Task {
    // Fields
    private String taskId;
    private String description;
    private String status;

    // Constructor
    public Task(String taskId, String description) {
        // What goes here?
        // Remember: default status must be "OPEN"
    }

    // Getters
    public String getTaskId() { }
    public String getDescription() { }
    public String getStatus() { }

    // Setter
    public void setStatus(String status) {
        // Valid values: OPEN, IN_PROGRESS, COMPLETE
        // Anything else → set to "UNKNOWN"
        // What logic goes here?
    }

    // toString
    public String toString() {
        // Must produce: T1 Write report [OPEN]
        // What goes here?
    }
}
Answer these three questions in plain English before we write the code:

What does the constructor need to do?
What does setStatus() need to check before setting the status?
What does toString() need to return?
The constructor has to save the taskID, the description, and set a default status  to Open. 
setStatus() needs to check if the task is open, in progress, or completed. and if its not  
toString() proiduces the taskId, description, and [status]7:11 PMPerfect — all three answers are correct. You understand exactly what needs to happen. Let's now turn that into real Java code.

Task.java — Complete Implementation
javapackage org.howard.edu.lsp.midterm.crccards;

/**
 * Represents a single task in the Task Management System.
 * @author [Your Name]
 */
public class Task {

    private String taskId;
    private String description;
    private String status;

    /**
     * Constructs a Task with a given ID and description.
     * Default status is set to "OPEN".
     * @param taskId the unique identifier for the task
     * @param description a brief description of the task
     */
    public Task(String taskId, String description) {
        this.taskId = taskId;
        this.description = description;
        this.status = "OPEN"; // default status
    }

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
     * Returns the current task status.
     * @return status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the task status if valid, otherwise sets it to "UNKNOWN".
     * Valid values are: OPEN, IN_PROGRESS, COMPLETE.
     * @param status the new status to set
     */
    public void setStatus(String status) {
        if (status.equals("OPEN") || 
            status.equals("IN_PROGRESS") || 
            status.equals("COMPLETE")) {
            this.status = status;
        } else {
            this.status = "UNKNOWN";
        }
    }

    /**
     * Returns a string representation of the task.
     * Format: taskId description [status]
     * @return formatted task string
     */
    public String toString() {
        return taskId + " " + description + " [" + status + "]";
    }
}

Two things to notice
1. this. keyword
When the constructor parameter has the same name as the field, Java needs this.taskId to mean "the field" versus just taskId meaning "the parameter." You'll see this pattern constantly in Java.
1. .equals() not == for Strings
In Java you never compare Strings with == — you use .equals(). This is one of the most common beginner mistakes on exams.

Now let's build TaskManager
You already know it uses HashMap<String, Task>. Here's the skeleton:
javapublic class TaskManager {

    // What goes here for the HashMap declaration?
    
    public void addTask(Task task) {
        // 1. Check if taskId already exists → throw IllegalArgumentException
        // 2. If not, add it to the HashMap
        // What's the key? What's the value?
    }

    public Task findTask(String taskId) {
        // Look up taskId in HashMap
        // If found → return the Task
        // If not found → return null
    }

    public List<Task> getTasksByStatus(String status) {
        // Loop through ALL tasks in the HashMap
        // If a task's status matches → add it to a results list
        // Return the results list
    }
}
Answer in plain English before we write the code:

How do you check if a taskId already exists in a HashMap?
How do you add a key-value pair to a HashMap?
For getTasksByStatus() — what kind of Collection would you use to build the results list, and why?
we check if a taskId already exists by checking if it contains the key
you add a key value pair by having the key   = taskId and value = Task object
getTasksByStatus() should use an ArrayList for the tasks because its ordered and easy to append elements7:21 PMPerfect — all three answers are correct. Let's turn that into code.

TaskManager.java — Complete Implementation
javapackage org.howard.edu.lsp.midterm.crccards;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Manages a collection of Task objects.
 * Uses a HashMap to store tasks by their unique task ID.
 * @author [Your Name]
 */
public class TaskManager {

    private HashMap<String, Task> tasks = new HashMap<>();

    /**
     * Adds a new task to the manager.
     * Throws IllegalArgumentException if task ID already exists.
     * @param task the Task object to add
     */
    public void addTask(Task task) {
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
        return tasks.get(taskId);        
        // HashMap.get() returns null automatically if key doesn't exist
    }

    /**
     * Returns all tasks matching the given status.
     * @param status the status to filter by
     * @return a List of matching Task objects
     */
    public List<Task> getTasksByStatus(String status) {
        List<Task> result = new ArrayList<>();
        for (Task task : tasks.values()) {
            if (task.getStatus().equals(status)) {
                result.add(task);
            }
        }
        return result;
    }
}

Two things to notice
1. tasks.values()
A HashMap stores key-value pairs. When you want to loop through just the values (the Task objects), you call .values(). You'll use this pattern whenever you need to search through everything in a HashMap.
2. tasks.get(taskId) handles null automatically
HashMap.get() returns null if the key doesn't exist — so findTask() is just one line. No if-statement needed.

Q1 is now done except for the documentation files
You need two markdown files:
1. crccards.md — explain in 2-3 sentences why TaskManager collaborates with Task but Task does not collaborate with TaskManager
Based on your CRC card work today — can you answer that in your own words before I help you write it?
2. development_log.md — this needs the complete transcript of our conversation. Since the professor requires it, just copy this entire chat conversation and paste it into that file.
What's your answer for the crccards.md question?TaskManager collaborates with Task because TaskManager is responsible for storing, managing, and retrieving Task objects. The TaskManager must interact with Task instances in order to add them, locate them by ID, and return tasks by status.

The Task class does not collaborate with TaskManager because its responsibilities are limited to storing and updating task information. Task objects simply represent task data and do not need to manage collections of other tasks.7:28 PMThat is an excellent answer. Seriously — that's exactly what the professor is looking for. You used the right reasoning, referenced responsibilities directly, and explained the one-way relationship clearly.
No corrections needed. That's your crccards.md file done. ✅

Q1 Complete Checklist
FileStatusTask.java✅ DoneTaskManager.java✅ Donecrccards.md✅ Donedevelopment_log.md📋 Paste this conversation transcript