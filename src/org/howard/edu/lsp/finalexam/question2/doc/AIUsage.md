# AI Usage Report — Final Exam Question 2

## AI Tools Used

Claude by Anthropic (claude.ai)

## Prompts Used

1. Which class should be abstract in the Template Method pattern?
2. Which methods should be abstract vs final?
3. Why should generateReport() be marked final?
4. How does the Driver demonstrate polymorphism?
5. How do StudentReport and CourseReport implement the abstract steps?

## How AI Helped

Claude explained the Template Method pattern by asking questions first
and letting the student reason through the design before writing code.
The distinction between abstract steps and the final template method
was clarified through guided questions rather than direct answers.

## Reflection

I learned that the Template Method pattern separates what stays the
same (the order of steps) from what changes (the specific implementation
of each step). Marking generateReport() as final enforces that the
workflow cannot be accidentally broken by a subclass.

---

[Q2 — Template Method Pattern
Before we write any code, same approach as always — plain English first.
The Template Method pattern works like this:

A parent class defines the skeleton of an algorithm — the steps and their order. Child classes fill in the specific details of each step.

In this exam the workflow is fixed:
loadData → formatHeader → formatBody → formatFooter → generateReport
generateReport() calls all four steps in order — that's the template. StudentReport and CourseReport each implement the steps differently.
Before we write code — answer these:

Which class should be abstract — the parent or the children?
Which methods should be abstract — the template itself or the individual steps?
generateReport() calls all steps in order — should subclasses be allowed to override it?

What do you think?Q1 answersDocument · MD DownloadQ1 aiusageDocument · MD DownloadDownload allYou said: The parent should be abstractThe parent should be abstractThe methods that should be abstract should be the individual steps because when calling student report or course report, the subclasses are overriddenThe subclass should not be allowed to override it7:08 PM2 / 2Claude responded: Perfect — all three correct.Perfect — all three correct. Your reasoning is exactly right. Let's turn that into code.

Key Java concepts before we write
Abstract class — cannot be instantiated directly, meant to be extended
javapublic abstract class Report { ... }
Abstract method — no body in parent, child MUST implement it
javapublic abstract void loadData();
public abstract void formatHeader();
Final method — cannot be overridden by children
javapublic final void generateReport() { ... }  // locked — no overriding

The Abstract Parent Class — Your Turn
Fill in the blanks in plain English before I write the code:
javapublic abstract class Report {

    // Abstract methods — children must implement these
    public abstract void _____();   // loads the data
    public abstract void _____();   // formats the header
    public abstract void _____();   // formats the body
    public abstract void _____();   // formats the footer

    // Template method — fixed, cannot be overridden
    // What should this method do step by step?
    public final void generateReport() {
        // 1. ?
        // 2. ?
        // 3. ?
        // 4. ?
    }
}
You already know the workflow:

loadData → formatHeader → formatBody → formatFooter

Tell me what generateReport() should do step by step and I'll write the full class!You said: it should call the functions in the order of loadData → formatHeader → formatBody → formatFooterit should call the functions in the order of loadData → formatHeader → formatBody → formatFooter7:09 PMClaude responded: Exactly right.Exactly right. Now let's build all the code:

Abstract Parent Class
javapackage org.howard.edu.lsp.finalexam.question2;

/**
 * Abstract base class defining the Template Method for report generation.
 * Subclasses must implement the individual steps but cannot change the order.
 * @author [Your Name]
 */
public abstract class Report {

    /**
     * Loads data required for the report.
     */
    public abstract void loadData();

    /**
     * Formats and prints the report header.
     */
    public abstract void formatHeader();

    /**
     * Formats and prints the report body.
     */
    public abstract void formatBody();

    /**
     * Formats and prints the report footer.
     */
    public abstract void formatFooter();

    /**
     * Template method — defines the fixed order of report generation.
     * Cannot be overridden by subclasses.
     */
    public final void generateReport() {
        loadData();
        formatHeader();
        formatBody();
        formatFooter();
    }
}

StudentReport
javapackage org.howard.edu.lsp.finalexam.question2;

/**
 * Concrete report for student information.
 * Implements report steps for student name and GPA.
 * @author [Your Name]
 */
public class StudentReport extends Report {

    private String studentName;
    private double gpa;

    /**
     * Loads student data into the report.
     */
    @Override
    public void loadData() {
        studentName = "John Doe";
        gpa = 3.8;
    }

    /**
     * Prints the student report header.
     */
    @Override
    public void formatHeader() {
        System.out.println("=== HEADER ===");
        System.out.println("Student Report");
        System.out.println();
    }

    /**
     * Prints the student report body with name and GPA.
     */
    @Override
    public void formatBody() {
        System.out.println("=== BODY ===");
        System.out.println("Student Name: " + studentName);
        System.out.println("GPA: " + gpa);
        System.out.println();
    }

    /**
     * Prints the student report footer.
     */
    @Override
    public void formatFooter() {
        System.out.println("=== FOOTER ===");
        System.out.println("End of Student Report");
        System.out.println();
    }
}

CourseReport
javapackage org.howard.edu.lsp.finalexam.question2;

/**
 * Concrete report for course information.
 * Implements report steps for course name and enrollment.
 * @author [Your Name]
 */
public class CourseReport extends Report {

    private String courseName;
    private int enrollment;

    /**
     * Loads course data into the report.
     */
    @Override
    public void loadData() {
        courseName = "CSCI 363";
        enrollment = 45;
    }

    /**
     * Prints the course report header.
     */
    @Override
    public void formatHeader() {
        System.out.println("=== HEADER ===");
        System.out.println("Course Report");
        System.out.println();
    }

    /**
     * Prints the course report body with course name and enrollment.
     */
    @Override
    public void formatBody() {
        System.out.println("=== BODY ===");
        System.out.println("Course: " + courseName);
        System.out.println("Enrollment: " + enrollment);
        System.out.println();
    }

    /**
     * Prints the course report footer.
     */
    @Override
    public void formatFooter() {
        System.out.println("=== FOOTER ===");
        System.out.println("End of Course Report");
        System.out.println();
    }
}

Driver
javapackage org.howard.edu.lsp.finalexam.question2;

import java.util.ArrayList;
import java.util.List;

/**
 * Driver class demonstrating Template Method pattern with polymorphism.
 * @author [Your Name]
 */
public class Driver {
    public static void main(String[] args) {
        List<Report> reports = new ArrayList<>();
        reports.add(new StudentReport());
        reports.add(new CourseReport());

        for (Report report : reports) {
            report.generateReport();
        }
    }
}

Design Explanation — answers.md
Now you need 3-5 sentences explaining how Template Method is used. Based on everything we just built — can you explain it in your own words?
Tell me:

What does Report define?
What do StudentReport and CourseReport do?
Why is generateReport() marked final?
How does the Driver demonstrate polymorphism?

Write it in plain English and I'll clean it up into the answers.md!You said: Report defines the fixed workflowReport defines the fixed workflowStudentReport generates the student name and gpa and CourseReport shows the course name and enrollmentGenerate report is marked final because it the fixed order of the report generation7:20 PMClaude responded: Great — all correct.Great — all correct. You missed Q4 about polymorphism so let me add that:

The Driver stores both StudentReport and CourseReport in a List<Report> and calls generateReport() on each — without knowing which specific type it is. Java automatically calls the right implementation for each. That's polymorphism.]