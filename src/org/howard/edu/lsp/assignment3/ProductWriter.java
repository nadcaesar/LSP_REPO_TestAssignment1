package org.howard.edu.lsp.assignment3;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.RoundingMode;
import java.util.List;

/**
 * Responsible ONLY for writing transformed products to the output CSV.
 */
public class ProductWriter {

    public void writeProducts(String outputPath, List<Product> products) throws IOException {
        File outFile = new File(outputPath);

        // Ensure output directory exists
        File parent = outFile.getParentFile();
        if (parent != null && !parent.exists()) {
            parent.mkdirs();
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outFile))) {
            // Always write header row
            bw.write("ProductID,Name,Price,Category,PriceRange");
            bw.newLine();

            for (Product p : products) {
                // Price must always be 2 decimals
                String priceStr = p.price.setScale(2, RoundingMode.HALF_UP).toPlainString();

                bw.write(p.productId + ","
                        + p.name + ","
                        + priceStr + ","
                        + p.category + ","
                        + p.priceRange);
                bw.newLine();
            }
        }
    }
}