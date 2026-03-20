package org.howard.edu.lsp.midterm.strategy;

/**
 * Calculates final price using a pricing strategy.
 * The strategy is injected at runtime — no if-statements needed.
 */
public class PriceCalculator {

    private PricingStrategy strategy;

    /**
     * Constructs a PriceCalculator with a given pricing strategy.
     * @param strategy the pricing strategy to use
     */
    public PriceCalculator(PricingStrategy strategy) {
        this.strategy = strategy;
    }

    /**
     * Calculates the final price using the assigned strategy.
     * @param price the original price
     * @return the final price after strategy is applied
     */
    public double calculatePrice(double price) {
        return strategy.calculatePrice(price);
    }
}