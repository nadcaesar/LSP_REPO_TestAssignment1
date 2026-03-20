package org.howard.edu.lsp.midterm.strategy;

/**
 * Pricing strategy for member customers — 10% discount applied.
 */
public class MemberPricingStrategy implements PricingStrategy {
    /**
     * Returns the price with a 10% discount.
     * @param price the original price
     * @return price multiplied by 0.90
     */
    public double calculatePrice(double price) {
        return price * 0.90;
    }
}