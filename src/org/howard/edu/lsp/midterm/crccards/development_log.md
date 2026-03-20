# Development Log — Question 1 (CRC Cards / Task Management)
 
## AI Tool Used: Claude by Anthropic
**Platform:** claude.ai  
**Model:** Claude Sonnet 4.6
 
## How AI Was Used
Claude was used to help understand Java Collections Framework concepts,
design the Task and TaskManager classes, and explain the reasoning
behind using HashMap as the internal data structure for TaskManager.
All design decisions and answers were developed interactively — the
student provided reasoning before any code was written.
 
## What Was Asked and How It Helped
 
- Asked Claude to explain Java Collections Framework from scratch
- Discussed why HashMap is appropriate for TaskManager (unique keys, fast lookup, no duplicates)
- Worked through the Task constructor, setStatus() validation logic, and toString() format
- Discussed why TaskManager collaborates with Task but Task does not collaborate with TaskManager
 
## Complete Conversation Transcript
All prompts and responses from the full study session are included below.
Scroll to the top of the claude.ai conversation and paste the full transcript here.
 
---

[mid-term.docx
docx

So here is the midterm and I have permission from my professor to use AI and I wanted to know if you can help me out with it please

6:40 PM
Let me read the midterm first.


Read the midterm document
Read the midterm document
Okay I've read the full midterm. Great news — AI is explicitly allowed, and the professor even requires you to document your conversation transcript. So we can work through this together properly.

Here's what you're dealing with:

Midterm Overview
Question	Topic	Points
Q1	Implement Task and TaskManager classes in Java	40 pts
Q2	Evaluate bad OO design + redesign with CRC cards	30 pts
Q3	Refactor PriceCalculator using Strategy Pattern	30 pts
Total	100 pts
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
From the four Collections we looked at earlier — ArrayList, LinkedList, HashSet, HashMap — which one do you think fits best here and why?

Task manager would go in HashMap because that collection avoids duplicates with unique keys for the key value pairs.

6:52 PM
Exactly right — and your reasoning is solid too. The key-value pair structure of HashMap is perfect here because:

Key = taskId (unique, no duplicates)
Value = the Task object itself
So HashMap<String, Task> gives us fast lookup by ID and duplicate prevention automatically. That's exactly the kind of design decision examiners want to see.

Let's Build Q1 Step by Step
We'll do Task first since it's simpler and TaskManager depends on it.

Here's the structure you need to fill in. I've given you the skeleton — your job is to tell me what goes inside each method before I write it:

java
public class Task {
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
toString() proiduces the taskId, description, and [status]

7:11 PM
Perfect — all three answers are correct. You understand exactly what needs to happen. Let's now turn that into real Java code.

Task.java — Complete Implementation
java
package org.howard.edu.lsp.midterm.crccards;

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
1. this. keyword When the constructor parameter has the same name as the field, Java needs this.taskId to mean "the field" versus just taskId meaning "the parameter." You'll see this pattern constantly in Java.

2. .equals() not == for Strings In Java you never compare Strings with == — you use .equals(). This is one of the most common beginner mistakes on exams.

Now let's build TaskManager
You already know it uses HashMap<String, Task>. Here's the skeleton:

java
public class TaskManager {

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
getTasksByStatus() should use an ArrayList for the tasks because its ordered and easy to append elements

7:21 PM
Perfect — all three answers are correct. Let's turn that into code.

TaskManager.java — Complete Implementation
java
package org.howard.edu.lsp.midterm.crccards;

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
1. tasks.values() A HashMap stores key-value pairs. When you want to loop through just the values (the Task objects), you call .values(). You'll use this pattern whenever you need to search through everything in a HashMap.

2. tasks.get(taskId) handles null automatically HashMap.get() returns null if the key doesn't exist — so findTask() is just one line. No if-statement needed.

Q1 is now done except for the documentation files
You need two markdown files:

1. crccards.md — explain in 2-3 sentences why TaskManager collaborates with Task but Task does not collaborate with TaskManager

Based on your CRC card work today — can you answer that in your own words before I help you write it?

2. development_log.md — this needs the complete transcript of our conversation. Since the professor requires it, just copy this entire chat conversation and paste it into that file.

What's your answer for the crccards.md question?

TaskManager collaborates with Task because TaskManager is responsible for storing, managing, and retrieving Task objects. The TaskManager must interact with Task instances in order to add them, locate them by ID, and return tasks by status.

The Task class does not collaborate with TaskManager because its responsibilities are limited to storing and updating task information. Task objects simply represent task data and do not need to manage collections of other tasks.
7:28 PM
That is an excellent answer. Seriously — that's exactly what the professor is looking for. You used the right reasoning, referenced responsibilities directly, and explained the one-way relationship clearly.

No corrections needed. That's your crccards.md file done. ✅

Q1 Complete Checklist
File	Status
Task.java	✅ Done
TaskManager.java	✅ Done
crccards.md	✅ Done
development_log.md	📋 Paste this conversation transcript
Now let's move to Q2
This question has two parts and you've actually already done most of the thinking for it today.

Part 1 — Evaluate the bad design of OrderProcessor Part 2 — Redesign it using CRC cards

Look at this OrderProcessor class and think about what we discussed earlier about god classes and single responsibility:

java
public class OrderProcessor {
    public String customerName;  // public fields!
    public String email;
    public String item;
    public double price;

    public void processOrder() {
        // calculates tax
        // prints receipt
        // saves to file
        // sends email
        // applies discount
        // logs activity
    }
}
Before I explain anything — using what you learned today, what problems do you already spot in this class?

Think about:

What we said about god classes
What we said about responsibilities
What we said about class names and field visibility
What do you see?

God classes shouldn't be responsible for everything because its doing too many unrelated jobs. The responsibilities are mixed together as it as the class combines the order data, print receipt, calculate tax, save to files, sends out emails, and activity logging 
The class names are suspicious because it follows a controller verb style class and the public fields are bad encapsulation

7:37 PM
Outstanding analysis. You identified every major issue and even used the term "encapsulation" correctly. That's impressive given where you started this morning.

Let's turn that into your evaluation.md file:

evaluation.md — Complete
markdown
# OrderProcessor Design Evaluation

## Issue 1: God Class / Too Many Responsibilities
The OrderProcessor class violates the single responsibility principle 
by doing everything in one place. A single processOrder() method 
handles tax calculation, receipt printing, file saving, email sending, 
discount application, and activity logging. These are six unrelated 
jobs that should belong to separate classes. This makes the class 
difficult to maintain — changing how emails are sent, for example, 
requires modifying the same class that handles tax calculation.

## Issue 2: Poor Encapsulation
All four fields (customerName, email, item, price) are declared as 
public. This means any other class can read or modify them directly 
without any validation or control. Fields should be private with 
controlled access through getter and setter methods.

## Issue 3: Discount Applied After Receipt is Printed
The discount logic appears after the receipt is already printed and 
the order is saved to file. This means the customer could receive a 
receipt showing the wrong total. This is a logic error caused by 
cramming too many responsibilities into one method.

## Issue 4: No Separation Between Data and Behavior
The class mixes order data (customerName, email, item, price) with 
order processing behavior (printing, saving, emailing). Data and 
behavior that change for different reasons should be in separate 
classes. If the data fields change, it affects the same class as the 
processing logic.

## Summary
This class is a textbook god class. It knows too much and does too 
much. A well-designed system would split these responsibilities across 
focused, cohesive classes — each with a single, clear job.
Now Part 2 — Redesign with CRC Cards
This is exactly what you did for the ATC system today. Split OrderProcessor into focused classes.

Based on the six jobs you identified, here are the classes I want you to name:

Job	Your class name?
Hold order data (customerName, email, item, price)	?
Calculate tax and apply discount	?
Print the receipt to screen	?
Save the order to a file	?
Send the confirmation email	?
Log activity with timestamp	?
Use the same naming pattern from this morning — domain-appropriate, verb+noun or noun only, no god class names.

What do you come up with?

Hold order data customerName, email, item, price    Order
Calculate tax and apply discount    PricingService
Print the receipt to screen    ReceiptPrinter
Save the order to a file    OrderRepository
Send the confirmation email    EmailService
Log activity with timestamp    ActivityLogger

7:44 PM
That is a perfect set of class names. Every single one is domain-appropriate, focused, and tells you exactly what it does. OrderRepository especially is a professional-level name — that's real industry terminology.

Now write the CRC cards for all six classes. You know the format from this morning:

Class:
Responsibilities:
Collaborators:
Use what you know about each class's job. Think carefully about collaborators — which classes need to talk to Order to do their job?

Take a shot at all six and paste them in when you're done!

Class: Order
Responsibilities:
- store order data
- provide order details

Collaborators:
- PricingService
- ReceiptPrinter
- OrderRepository
- EmailService
- ActivityLogger
Class: PricingService
Responsibilities:
- calculate tax
- apply discount
- compute total price

Collaborators:
- Order
Class: ReceiptPrinter
Responsibilities:
- print receipt
- display order summary

Collaborators:
- Order
- PricingService
Class: OrderRepository
Responsibilities:
- save order to file

Collaborators:
- Order
Class: EmailService
Responsibilities:
- send confirmation email

Collaborators:
- Order
Class: ActivityLogger
Responsibilities:
- log order activity
- record timestamp

Collaborators:
- Order
Show more
7:45 PM
Really strong set of cards. The responsibilities are clean, verb-based, and focused. Just one thing to fix:

The One Issue — Order Collaborators
Look at Order's collaborators:

- PricingService
- ReceiptPrinter
- OrderRepository
- EmailService
- ActivityLogger
Remember what we said this morning about FlightData? It was a pure data-holding class — it didn't reach out to other classes, other classes reached into it.

Order is the same thing. It just holds data. It doesn't need to know about PricingService or EmailService — those classes go to Order to get data, not the other way around.

Ask yourself: does a piece of paper in a filing cabinet need to know who reads it?

→ Order collaborators should be: none

Everything else is correct. ✅

Your complete design.md file
markdown
# Redesigned Order Processing System — CRC Cards

Class: Order
Responsibilities:
- Store order data (customerName, email, item, price)
- Provide order details to requesting classes
Collaborators:
- None

Class: PricingService
Responsibilities:
- Calculate tax on order price
- Apply discount based on order total
- Compute and return final price
Collaborators:
- Order

Class: ReceiptPrinter
Responsibilities:
- Print order summary to screen
- Display final price from PricingService
Collaborators:
- Order
- PricingService

Class: OrderRepository
Responsibilities:
- Save completed order to file
Collaborators:
- Order

Class: EmailService
Responsibilities:
- Send confirmation email to customer
Collaborators:
- Order

Class: ActivityLogger
Responsibilities:
- Log order activity with timestamp
Collaborators:
- Order
Q2 Complete Checklist
File	Status
evaluation.md	✅ Done
design.md	✅ Done
development_log_q2.md	📋 Paste conversation transcript
One question left — Q3: Strategy Pattern
This is the only genuinely new concept on this exam. But it connects directly to what you already understand about OO design.

Look at this PriceCalculator:

java
public double calculatePrice(String customerType, double price) {
    if (customerType.equals("REGULAR")) { finalPrice = price; }
    if (customerType.equals("MEMBER"))  { finalPrice = price * 0.90; }
    if (customerType.equals("VIP"))     { finalPrice = price * 0.80; }
    if (customerType.equals("HOLIDAY")) { finalPrice = price * 0.85; }
    return finalPrice;
}
Before I explain the Strategy Pattern — using what you know about OO design, what's wrong with this class?

Can you create the md syntax for both question 1 and question 2

7:49 PM
Here are all the files you need:


Created 5 files, read a file
Created 5 files, read a file
Here are all 5 files. Here's exactly where each one goes in your GitHub repo:

Question 1 files → org/howard/edu/lsp/midterm/crccards/doc/

crccards.md
development_log.md
Question 2 files → org/howard/edu/lsp/midterm/design/doc/

evaluation.md
design.md
development_log_q2.md
⚠️ One Critical Thing
Both development log files have a placeholder:

[PASTE FULL CONVERSATION TRANSCRIPT HERE]

You must replace that with our actual conversation before submitting. The professor explicitly said partial transcripts are not acceptable and it's worth 5 points per question. Just scroll to the top of this chat and copy everything.

Ready for Q3 — the Strategy Pattern? You still need to answer what's wrong with PriceCalculator before we write the code!

Crccards
Document · MD 
Development log
Document · MD 
Evaluation
Document · MD 
Design
Document · MD 
Development log q2
Document · MD 
it contains too many conditional rules,  the discount rules are burried in the class which would mean that the discount rules are not encapsulated and there isnt any room for user typo errors

7:58 PM
Really good observations. Let me sharpen the technical language a bit:

Your Points Refined
"Too many conditional rules" ✅ — correct, here's the technical term Every time a new customer type is added (e.g. "STUDENT", "SENIOR"), someone has to open this class and add another if statement. This violates the Open/Closed Principle — classes should be open for extension but closed for modification. You should be able to add new behavior without touching existing code.

"Discount rules are buried / not encapsulated" ✅ — exactly right Each discount calculation is hardcoded inside one method. If the MEMBER discount changes from 10% to 15%, you have to dig into this method and change it. Each discount rule should live in its own place.

"No room for typo errors" ✅ — good instinct If someone calls calculatePrice("Vip", 100) instead of "VIP", it silently returns the wrong price with no error. There's no validation.

Now — What is the Strategy Pattern?
The Strategy Pattern solves exactly this problem. The idea is simple:

Instead of one class with many if-statements, create a separate class for each behavior

Think of it like this — instead of one Swiss Army knife doing everything, you have separate specialized tools.

In code terms:

// BEFORE — one class, all logic crammed in
PriceCalculator → if REGULAR... if MEMBER... if VIP... if HOLIDAY...

// AFTER — Strategy Pattern
PricingStrategy (interface)
    ├── RegularPricingStrategy  → returns full price
    ├── MemberPricingStrategy   → returns 10% off
    ├── VipPricingStrategy      → returns 20% off
    └── HolidayPricingStrategy  → returns 15% off
Each strategy class does ONE thing — calculates price for ONE customer type.

Three Parts You Need to Build
1. An interface — defines the contract all strategies must follow

java
public interface PricingStrategy {
    double calculatePrice(double price);
}
2. Four strategy classes — one per customer type, each implements the interface

3. A refactored PriceCalculator — uses whichever strategy is passed in, no if-statements

Your Turn
Before we write the code, answer these in plain English:

What would RegularPricingStrategy.calculatePrice() return?
What would MemberPricingStrategy.calculatePrice() return?
What would VipPricingStrategy.calculatePrice() return?
What would HolidayPricingStrategy.calculatePrice() return?
You have all the numbers from the original code — what are the four answers?

RegularPricingStrategy.calculatePrice() willl return the full price

MemberPricingStrategy.calculatePrice() willl return the price with a 10% discount

VipPricingStrategy.calculatePrice() willl return the price with a 20% discount 

HolidayPricingStrategy.calculatePrice() willl return the price with a 15% discount

8:08 PM
Perfect — all four correct. Now let's build it.

Step 1 — The Interface
java
package org.howard.edu.lsp.midterm.strategy;

/**
 * Strategy interface for pricing calculations.
 * Each implementation defines a specific discount behavior.
 * @author [Your Name]
 */
public interface PricingStrategy {
    /**
     * Calculates the final price based on the strategy.
     * @param price the original price
     * @return the final calculated price
     */
    double calculatePrice(double price);
}
Step 2 — Four Strategy Classes
java
package org.howard.edu.lsp.midterm.strategy;

/**
 * Pricing strategy for regular customers — no discount applied.
 * @author [Your Name]
 */
public class RegularPricingStrategy implements PricingStrategy {
    /**
     * Returns the full price with no discount.
     * @param price the original price
     * @return the original price unchanged
     */
    public double calculatePrice(double price) {
        return price;
    }
}
java
package org.howard.edu.lsp.midterm.strategy;

/**
 * Pricing strategy for member customers — 10% discount applied.
 * @author [Your Name]
 */
public class MemberPricingStrategy implements PricingStrategy {
    /**
     * Returns the price with a 10% discount.
     * @param price the original price
     * @return price multiplied by 0.90
     */
    public double calculatePrice(double price) {
        return price * 0.90;
    }
}
java
package org.howard.edu.lsp.midterm.strategy;

/**
 * Pricing strategy for VIP customers — 20% discount applied.
 * @author [Your Name]
 */
public class VipPricingStrategy implements PricingStrategy {
    /**
     * Returns the price with a 20% discount.
     * @param price the original price
     * @return price multiplied by 0.80
     */
    public double calculatePrice(double price) {
        return price * 0.80;
    }
}
java
package org.howard.edu.lsp.midterm.strategy;

/**
 * Pricing strategy for holiday customers — 15% discount applied.
 * @author [Your Name]
 */
public class HolidayPricingStrategy implements PricingStrategy {
    /**
     * Returns the price with a 15% discount.
     * @param price the original price
     * @return price multiplied by 0.85
     */
    public double calculatePrice(double price) {
        return price * 0.85;
    }
}
Step 3 — Refactored PriceCalculator
Notice there are zero if-statements now:

java
package org.howard.edu.lsp.midterm.strategy;

/**
 * Calculates final price using a pricing strategy.
 * The strategy is injected at runtime — no if-statements needed.
 * @author [Your Name]
 */
public class PriceCalculator {

    private PricingStrategy strategy;

    /**
     * Constructs a PriceCalculator with a given pricing strategy.
     * @param strategy the pricing strategy to use
     */
    public PriceCalculator(PricingStrategy strategy) {
        this.strategy = strategy;
    }

    /**
     * Calculates the final price using the assigned strategy.
     * @param price the original price
     * @return the final price after strategy is applied
     */
    public double calculatePrice(double price) {
        return strategy.calculatePrice(price);
    }
}
Step 4 — Driver Class
java
package org.howard.edu.lsp.midterm.strategy;

/**
 * Driver class demonstrating the Strategy Pattern for pricing.
 * @author [Your Name]
 */
public class Driver {
    public static void main(String[] args) {

        double price = 100.0;

        PriceCalculator regular = new PriceCalculator(new RegularPricingStrategy());
        PriceCalculator member  = new PriceCalculator(new MemberPricingStrategy());
        PriceCalculator vip     = new PriceCalculator(new VipPricingStrategy());
        PriceCalculator holiday = new PriceCalculator(new HolidayPricingStrategy());

        System.out.println("REGULAR: " + regular.calculatePrice(price));
        System.out.println("MEMBER: "  + member.calculatePrice(price));
        System.out.println("VIP: "     + vip.calculatePrice(price));
        System.out.println("HOLIDAY: " + holiday.calculatePrice(price));
    }
}
```

Expected output:
```
REGULAR: 100.0
MEMBER: 90.0
VIP: 80.0
HOLIDAY: 85.0
Why This Design is Better
Problem Before	Fixed By Strategy Pattern
Adding a new customer type required modifying PriceCalculator	Now just add a new strategy class — don't touch existing code
All discount logic buried in one method	Each strategy class owns exactly one discount rule
No room to extend without risk of breaking things	Each strategy is isolated — changing one can't break others
Q3 Complete Checklist
File	Status
PricingStrategy.java (interface)	✅ Done
RegularPricingStrategy.java	✅ Done
MemberPricingStrategy.java	✅ Done
VipPricingStrategy.java	✅ Done
HolidayPricingStrategy.java	✅ Done
PriceCalculator.java	✅ Done
Driver.java	✅ Done
design_evaluation.md	🔲 Need to write
development_log_q3.md	📋 Paste transcript
One file left to write — design_evaluation.md. Based on your earlier answer and the table above, can you write 3-4 sentences explaining why the original PriceCalculator was problematic? You already said it in plain English — just write it in the markdown format now!]

---
*End of transcript*