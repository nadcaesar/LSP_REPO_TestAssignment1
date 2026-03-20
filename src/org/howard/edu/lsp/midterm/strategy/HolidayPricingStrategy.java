package org.howard.edu.lsp.midterm.strategy;

/**
 * Pricing strategy for holiday customers — 15% discount applied.
 */
public class HolidayPricingStrategy implements PricingStrategy {
    /**
     * Returns the price with a 15% discount.
     * @param price the original price
     * @return price multiplied by 0.85
     */
    public double calculatePrice(double price) {
        return price * 0.85;
    }
}