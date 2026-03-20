package org.howard.edu.lsp.midterm.strategy;

/**
 * Strategy interface for pricing calculations.
 * Each implementation defines a specific discount behavior.
 * @author Nicholas Caesar
 */
public interface PricingStrategy {
    /**
     * Calculates the final price based on the strategy.
     * @param price the original price
     * @return the final calculated price
     */
    double calculatePrice(double price);
}