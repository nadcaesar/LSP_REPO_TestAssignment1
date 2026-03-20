package org.howard.edu.lsp.midterm.strategy;

/**
 * Driver class demonstrating the Strategy Pattern for pricing.

 */
public class Driver {
    public static void main(String[] args) {

        double price = 100.0;

        PriceCalculator regular = new PriceCalculator(new RegularPricingStrategy());
        PriceCalculator member  = new PriceCalculator(new MemberPricingStrategy());
        PriceCalculator vip     = new PriceCalculator(new VipPricingStrategy());
        PriceCalculator holiday = new PriceCalculator(new HolidayPricingStrategy());

        System.out.println("REGULAR: " + regular.calculatePrice(price));
        System.out.println("MEMBER: "  + member.calculatePrice(price));
        System.out.println("VIP: "     + vip.calculatePrice(price));
        System.out.println("HOLIDAY: " + holiday.calculatePrice(price));
    }
}
