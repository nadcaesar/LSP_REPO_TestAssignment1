package org.howard.edu.lsp.assignment3;

/**
 * Reads products from a CSV file and converts valid rows into Product objects.
 * Skips invalid rows and counts them.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class ProductReader {

    public int skippedRows = 0;
    public int totalRows = 0;

    public List<Product> readProducts(String path) {
        List<Product> products = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

            // Read header (ignore)
            String header = br.readLine();

            if (header == null) {
                return products; // empty file
            }

            String line;
            while ((line = br.readLine()) != null) {

                totalRows++;

                // skip blank lines
                if (line.trim().isEmpty()) {
                    skippedRows++;
                    continue;
                }

                String[] fields = line.split(",", -1);

                // must have exactly 4 fields
                if (fields.length != 4) {
                    skippedRows++;
                    continue;
                }

                String idStr = fields[0].trim();
                String name = fields[1].trim();
                String priceStr = fields[2].trim();
                String category = fields[3].trim();

                try {
                    int id = Integer.parseInt(idStr);
                    BigDecimal price = new BigDecimal(priceStr);

                    Product product = new Product(id, name, price, category);
                    products.add(product);

                } catch (NumberFormatException e) {
                    skippedRows++;
                }
            }

        } catch (IOException e) {
            System.out.println("ERROR: Input file not found: " + path);
        }

        return products;
    }
}