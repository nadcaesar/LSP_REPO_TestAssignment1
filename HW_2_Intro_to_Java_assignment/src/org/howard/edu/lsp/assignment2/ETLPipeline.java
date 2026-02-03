/*          HW 2: ETL Pipeline
1) Extract
Read a CSV file named products.csv from the data/ directory. The file contains the following columns:
• ProductID (integer)
• Name (string)
• Price (decimal number)
• Category (string)

The first row is a header and must not be transformed.
2) Transform (Apply in This Exact Order)
1. Convert all product names to UPPERCASE.
2. If the category is "Electronics", apply a 10% discount to the price.
3. If the final rounded price is strictly greater than $500.00 AND the original category was "Electronics", change the category to "Premium Electronics".
4. Add a new field PriceRange based on the final rounded price:
   • <= $10.00 → Low
   • > $10.00 and <= $100.00 → Medium
   • > $100.00 and <= $500.00 → High
   • > $500.00 → Premium
3) Load
Write the transformed data to data/transformed_products.csv.

Output column order:
ProductID, Name, Price, Category, PriceRange

Data Input:
7,USB Cable,9.99,Electronics
8,  Office Chair  ,150.00, Furniture 
badid,Pen,1.00,Stationery
14,Notebook,abc,Stationery
15,TooFewFields,9.99
16,Too,Many,Fields,Here
9,4K TV,1200.00,Electronics
10,Gift Card,10.00,Other
11,Mouse,25.555,Electronics
12,Table,500.00,Furniture
13,Camera,556.00,Electronics

Data output
ProductID,Name,Price,Category,PriceRange
7,USB CABLE,8.99,Electronics,Low
8,OFFICE CHAIR,150.00,Furniture,High
9,4K TV,1080.00,Premium Electronics,Premium
10,GIFT CARD,10.00,Other,Low
11,MOUSE,23.00,Electronics,Medium
12,TABLE,500.00,Furniture,High
13,CAMERA,500.40,Premium Electronics,Premium

Always write a header row.
*/
package org.howard.edu.lsp.assignment2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.io.BufferedReader;
import java.io.BufferedWriter;

/* 
    Name: Nicholas Caesar 
*/


public class ETLPipeline {   
    
    private static String computePriceRange(BigDecimal price) {
        //Using the final rounded price for ranges

        if (price.compareTo(new BigDecimal(10.00)) <= 0) {
            return "Low";
        } else if (price.compareTo(new BigDecimal(100.00)) <= 0)  {
            return "Medium";
        } else if (price.compareTo(new BigDecimal(500.00)) <= 0)  {
            return "High";
        } else {
            return "Premium";
        }
    }

    private static String formatDecimals(BigDecimal price){
        return price.setScale(2, RoundingMode.HALF_UP).toPlainString();
    }

    private static void printSummary(int rowsRead, int rowsTransformed, int rowsSkipped, File outputFile) {
        System.out.println("ETL Process Summary:");
        System.out.println("Total Rows Read: " + rowsRead);
        System.out.println("Total Rows Transformed: " + rowsTransformed);
        System.out.println("Total Rows Skipped: " + rowsSkipped);
        System.out.println("Transformed data written to: " + outputFile.getAbsolutePath());
    }

    public static final String InputPath = "data/products.csv";
    public static final String OutputPath = "data/transformed_products.csv";
    public static void main(String[] args) {
        
        
        File inputFile = new File(InputPath);
        File outputFile = new File(OutputPath);
        

        //Error handling for file not found for input and output
        if (!inputFile.exists() || !inputFile.isFile()) {
            System.out.println("Input file not found: " + InputPath);
            return;
        }

        File outputDir = outputFile.getParentFile();
        if (outputDir != null && !outputDir.exists()) {
            outputDir.mkdirs();
        }

        int rowsRead = 0;
        int rowsTransformed = 0;
        int rowSkipped = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(inputFile));
        BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile))) {                

            String header = br.readLine();
            bw.write("ProductID,Name,Price,Category,PriceRange");
            bw.newLine();

            if (header == null) {
                printSummary(rowsRead, rowsTransformed, rowSkipped, outputFile);
                return;
            }

            //To process each line in the input file after the header line
            String line;
            while ((line = br.readLine()) != null) {
                rowsRead++;

                //We want to skip blank lines
                if (line.trim().isEmpty()) {
                    rowSkipped++;
                    continue;
                }

                

                //Validate number of fields but first we keep empty fields by using -1 as the second argument

                String[] fields = line.split(",", -1);
                if (fields.length != 4) {
                    rowSkipped++;
                    continue;
                }

                String productIdstr = fields[0].trim();
                String name = fields[1].trim().toUpperCase();
                String pricestr = fields[2].trim();
                String category = fields[3].trim();

                int productId;
                BigDecimal price;

                try {
                    productId = Integer.parseInt(productIdstr);
                    price = new BigDecimal(pricestr);
                } catch (NumberFormatException e) {
                    rowSkipped++;
                    continue;
                }

                // Save original category
                String originalCategory = category;

                // 1) Apply 10% discount if Electronics
                BigDecimal finalPrice = price;
                if ("Electronics".equals(category)) {
                    finalPrice = finalPrice.multiply(new BigDecimal("0.90"));
                }

                // 2) Round HALF_UP to 2 decimals
                finalPrice = finalPrice.setScale(2, RoundingMode.HALF_UP);

                // 3) Premium Electronics check (use FINAL rounded price)
                if (finalPrice.compareTo(new BigDecimal("500.00")) > 0
                        && "Electronics".equals(originalCategory)) {
                    category = "Premium Electronics";
                }

                // 4) Compute PriceRange from FINAL rounded price
                String priceRange = computePriceRange(finalPrice);

                // ---- LOAD ----
                bw.write(productId + "," 
                        + name + "," 
                        + formatDecimals(finalPrice) + "," 
                        + category + "," 
                        + priceRange);
                bw.newLine();

                rowsTransformed++;
            }
        } catch (IOException e) {
            System.out.println("Error processing files: " + e.getMessage());
            return;
        }
    }
}