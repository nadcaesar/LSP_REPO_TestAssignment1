package org.howard.edu.lsp.finalexam.question2;

/**
 * Concrete report for course information.
 * Implements report steps for course name and enrollment.
 * @author Nicholas Caesar
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