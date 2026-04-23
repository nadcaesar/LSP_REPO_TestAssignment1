package org.howard.edu.lsp.finalexam.question2;

/**
 * Abstract base class defining the Template Method for report generation.
 * Subclasses must implement the individual steps but cannot change the order.
 * @author Nicholas Caesar
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