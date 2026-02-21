package org.howard.edu.lsp.assignment3;


import java.io.IOException;
import java.util.List;

/**
 * Name: Nicholas Caesar
 *
 * ETL Pipeline (Assignment 3)
 * Coordinates reading, transforming, and writing.
 */
public class ETLPipeline {

    private static final String INPUT_PATH = "datab/products.csv";
    private static final String OUTPUT_PATH = "datab/transformed_products.csv";

    public static void main(String[] args) {

        // 1) Reader: Extract
        ProductReader reader = new ProductReader();
        List<Product> products = reader.readProducts(INPUT_PATH);

        // If input file missing, ProductReader prints an error and returns empty list.
        // We still write header row (spec: always write header).
        // 2) Transformer: Transform
        ProductTransformer transformer = new ProductTransformer();
        List<Product> transformed = transformer.transformAll(products);

        // 3) Writer: Load
        ProductWriter writer = new ProductWriter();
        try {
            writer.writeProducts(OUTPUT_PATH, transformed);
        } catch (IOException e) {
            System.out.println("ERROR: Could not write output file: " + OUTPUT_PATH);
            return;
        }

        // 4) Summary
        printSummary(reader.totalRows, transformed.size(), reader.skippedRows, OUTPUT_PATH);
    }

    private static void printSummary(int rowsRead, int rowsTransformed, int rowsSkipped, String outputPath) {
        System.out.println("Run Summary");
        System.out.println("-----------");
        System.out.println("Rows read (non-header): " + rowsRead);
        System.out.println("Rows transformed: " + rowsTransformed);
        System.out.println("Rows skipped: " + rowsSkipped);
        System.out.println("Output written to: " + outputPath);
    }
}