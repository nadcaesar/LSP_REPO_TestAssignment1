package org.howard.edu.lsp.midterm.strategy;

/**
 * Pricing strategy for regular customers — no discount applied.
 */
public class RegularPricingStrategy implements PricingStrategy {
    /**
     * Returns the full price with no discount.
     * @param price the original price
     * @return the original price unchanged
     */
    public double calculatePrice(double price) {
        return price;
    }
}