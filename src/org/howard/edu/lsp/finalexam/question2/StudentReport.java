package org.howard.edu.lsp.finalexam.question2;

/**
 * Concrete report for student information.
 * Implements report steps for student name and GPA.
 * @author Nicholas Caesar
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