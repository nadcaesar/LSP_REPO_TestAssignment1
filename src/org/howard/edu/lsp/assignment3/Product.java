package org.howard.edu.lsp.assignment3;
/**
 * Constructor to create the product object and hold the row data
 */

import java.math.BigDecimal;

public class Product {
    // Public fields (keeping it simple for HW3)
    public int productId;
    public String name;
    public BigDecimal price;
    public String category;
    public String priceRange;


    public Product(int productId, String name, BigDecimal price, String category) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.category = category;
        this.priceRange = "";//Empty until transformed
    }


    public Product(int productId, String name, BigDecimal price, String category, String priceRange) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.category = category;
        this.priceRange = priceRange;
    }
}
