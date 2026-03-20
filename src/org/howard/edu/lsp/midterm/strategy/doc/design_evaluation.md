# PriceCalculator Design Evaluation

## Issue 1: Too Many Conditional Rules

The PriceCalculator class contains too many conditional if-statements
in a single method. Every time a new customer type needs to be added,
the existing class must be modified by adding another if-statement.
This makes the class harder to maintain as the system grows and
violates the principle that classes should be open for extension but
closed for modification.

## Issue 2: Discount Rules Are Not Encapsulated

The discount rules for each customer type are buried inside a single
method rather than being separated into their own classes. This means
that if a discount percentage changes, a developer must dig into the
method and modify it directly, risking unintended side effects on the
other discount rules in the same method.

## Issue 3: No Handling of Invalid Input

There is no validation or error handling for unrecognized customer
types. If a caller passes in a misspelled or unexpected value such as
"Vip" instead of "VIP", the method silently returns the wrong price
with no warning or error. A well-designed system should handle
unexpected input explicitly.

## Summary

The original PriceCalculator class mixes all pricing logic into one
place, making it fragile and difficult to extend. The Strategy Pattern
solves this by giving each customer type its own dedicated class,
isolating each discount rule and making the system easy to extend
without modifying existing code.