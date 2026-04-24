# Final Exam — Question 5 Answers

## Heuristic 1

**Name:**
All Data Should Be Hidden Within Its Class

**Statement:**
All instance variables in a class should be declared private and only
accessed through methods defined within the same class. No external
class should be able to directly read or modify another class's data.

**Explanation:**
This heuristic enforces encapsulation — one of the core principles of
object-oriented design. By keeping data private, a class controls how
its state is read and modified, preventing external code from putting
the object into an invalid state. This improves maintainability because
the internal representation of a class can change without breaking any
outside code that depends on it — as long as the public methods remain
the same. In lecture this was illustrated through the OrderProcessor
example, where public fields like customerName and price allowed any
class to modify them directly without validation, which was identified
as a design flaw.

---

## Heuristic 2

**Name:**
Minimize the Public Interface of a Class

**Statement:**
A class should only expose the methods that external callers genuinely
need. Everything else — internal helpers, implementation details —
should be private or package-private.

**Explanation:**
Minimizing the public interface reduces the surface area that outside
code can depend on, making the class easier to change and maintain. If
an internal helper method is public, other classes may start calling it
directly, creating hidden dependencies that make refactoring dangerous.
Keeping it private means the class can change its internal
implementation freely without breaking anything outside. In lecture
this was illustrated through the RequestManager example, where
getNextId() was public but should have been private since it is only
ever called internally by addRequest(). Exposing it unnecessarily
widened the public interface beyond what external callers need.

---

## Heuristic 3

**Name:**
Do Not Create God Classes

**Statement:**
Avoid creating classes that do too much or know too much. A god class
is one that either performs too many unrelated operations or keeps
track of too much information that should be distributed across
multiple focused objects.

**Explanation:**
God classes violate the single responsibility principle — each class
should capture one and only one abstraction. When a class does
everything, it becomes difficult to understand, test, and maintain.
A change to one responsibility risks breaking another unrelated
responsibility in the same class. In lecture this was illustrated
through the OrderProcessor example, which combined order data storage,
tax calculation, receipt printing, file saving, email sending, discount
application, and activity logging all in one class. The correct design
split these into focused classes — PricingService, ReceiptPrinter,
OrderRepository, EmailService, and ActivityLogger — each with a single
clear job.
