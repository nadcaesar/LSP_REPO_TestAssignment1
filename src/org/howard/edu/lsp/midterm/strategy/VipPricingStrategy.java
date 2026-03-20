package org.howard.edu.lsp.midterm.strategy;

/**
 * Pricing strategy for VIP customers — 20% discount applied.
 */
public class VipPricingStrategy implements PricingStrategy {
    /**
     * Returns the price with a 20% discount.
     * @param price the original price
     * @return price multiplied by 0.80
     */
    public double calculatePrice(double price) {
        return price * 0.80;
    }
}