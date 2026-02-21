package org.howard.edu.lsp.assignment3;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 * Responsible ONLY for transforming products.
 */
public class ProductTransformer {

    public List<Product> transformAll(List<Product> inputProducts) {
        List<Product> output = new ArrayList<>();

        for (Product p : inputProducts) {
            output.add(transformOne(p));
        }

        return output;
    }

    public Product transformOne(Product p) {
        // 1) Uppercase name
        String upperName = p.name.toUpperCase();

        // Keep original category for rule #3
        String originalCategory = p.category;
        String category = p.category;

        // 2) Discount if Electronics
        BigDecimal finalPrice = p.price;
        if ("Electronics".equals(category)) {
            finalPrice = finalPrice.multiply(new BigDecimal("0.90"));
        }

        // Round HALF_UP to 2 decimals
        finalPrice = finalPrice.setScale(2, RoundingMode.HALF_UP);

        // 3) Premium Electronics rule
        if (finalPrice.compareTo(new BigDecimal("500.00")) > 0
                && "Electronics".equals(originalCategory)) {
            category = "Premium Electronics";
        }

        // 4) PriceRange from final rounded price
        String priceRange = computePriceRange(finalPrice);

        // Return a new transformed product (complete record)
        return new Product(p.productId, upperName, finalPrice, category, priceRange);
    }

    private String computePriceRange(BigDecimal price) {
        if (price.compareTo(new BigDecimal("10.00")) <= 0) return "Low";
        if (price.compareTo(new BigDecimal("100.00")) <= 0) return "Medium";
        if (price.compareTo(new BigDecimal("500.00")) <= 0) return "High";
        return "Premium";
    }
}