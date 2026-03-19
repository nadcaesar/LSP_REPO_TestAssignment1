Now let's move to Q2
This question has two parts and you've actually already done most of the thinking for it today.
Part 1 — Evaluate the bad design of OrderProcessor
Part 2 — Redesign it using CRC cards
Look at this OrderProcessor class and think about what we discussed earlier about god classes and single responsibility:
javapublic class OrderProcessor {
    public String customerName;  // public fields!
    public String email;
    public String item;
    public double price;

    public void processOrder() {
        // calculates tax
        // prints receipt
        // saves to file
        // sends email
        // applies discount
        // logs activity
    }
}
Before I explain anything — using what you learned today, what problems do you already spot in this class?
Think about:

What we said about god classes
What we said about responsibilities
What we said about class names and field visibility

What do you see?God classes shouldn't be responsible for everything because its doing too many unrelated jobs. The responsibilities are mixed together as it as the class combines the order data, print receipt, calculate tax, save to files, sends out emails, and activity logging 
The class names are suspicious because it follows a controller verb style class and the public fields are bad encapsulation7:37 PMOutstanding analysis. You identified every major issue and even used the term "encapsulation" correctly. That's impressive given where you started this morning.
Let's turn that into your evaluation.md file:

evaluation.md — Complete
markdown# OrderProcessor Design Evaluation

## Issue 1: God Class / Too Many Responsibilities
The OrderProcessor class violates the single responsibility principle 
by doing everything in one place. A single processOrder() method 
handles tax calculation, receipt printing, file saving, email sending, 
discount application, and activity logging. These are six unrelated 
jobs that should belong to separate classes. This makes the class 
difficult to maintain — changing how emails are sent, for example, 
requires modifying the same class that handles tax calculation.

## Issue 2: Poor Encapsulation
All four fields (customerName, email, item, price) are declared as 
public. This means any other class can read or modify them directly 
without any validation or control. Fields should be private with 
controlled access through getter and setter methods.

## Issue 3: Discount Applied After Receipt is Printed
The discount logic appears after the receipt is already printed and 
the order is saved to file. This means the customer could receive a 
receipt showing the wrong total. This is a logic error caused by 
cramming too many responsibilities into one method.

## Issue 4: No Separation Between Data and Behavior
The class mixes order data (customerName, email, item, price) with 
order processing behavior (printing, saving, emailing). Data and 
behavior that change for different reasons should be in separate 
classes. If the data fields change, it affects the same class as the 
processing logic.

## Summary
This class is a textbook god class. It knows too much and does too 
much. A well-designed system would split these responsibilities across 
focused, cohesive classes — each with a single, clear job.

Now Part 2 — Redesign with CRC Cards
This is exactly what you did for the ATC system today. Split OrderProcessor into focused classes.
Based on the six jobs you identified, here are the classes I want you to name:
JobYour class name?Hold order data (customerName, email, item, price)?Calculate tax and apply discount?Print the receipt to screen?Save the order to a file?Send the confirmation email?Log activity with timestamp?
Use the same naming pattern from this morning — domain-appropriate, verb+noun or noun only, no god class names.
What do you come up with?Hold order data customerName, email, item, price    Order
Calculate tax and apply discount    PricingService
Print the receipt to screen    ReceiptPrinter
Save the order to a file    OrderRepository
Send the confirmation email    EmailService
Log activity with timestamp    ActivityLogger7:44 PMThat is a perfect set of class names. Every single one is domain-appropriate, focused, and tells you exactly what it does. OrderRepository especially is a professional-level name — that's real industry terminology.
Now write the CRC cards for all six classes. You know the format from this morning:
Class:
Responsibilities:
Collaborators:
Use what you know about each class's job. Think carefully about collaborators — which classes need to talk to Order to do their job?
Take a shot at all six and paste them in when you're done!Class: Order
Responsibilities:
- store order data
- provide order details

Collaborators:
- PricingService
- ReceiptPrinter
- OrderRepository
- EmailService
- ActivityLoggerClass: PricingService
Responsibilities:
- calculate tax
- apply discount
- compute total price

Collaborators:
- OrderClass: ReceiptPrinter
Responsibilities:
- print receipt
- display order summary

Collaborators:
- Order
- PricingServiceClass: OrderRepository
Responsibilities:
- save order to file

Collaborators:
- OrderClass: EmailService
Responsibilities:
- send confirmation email

Collaborators:
- OrderClass: ActivityLogger
Responsibilities:
- log order activity
- record timestamp

Collaborators:
- OrderShow more7:45 PMReally strong set of cards. The responsibilities are clean, verb-based, and focused. Just one thing to fix:

The One Issue — Order Collaborators
Look at Order's collaborators:
- PricingService
- ReceiptPrinter
- OrderRepository
- EmailService
- ActivityLogger
Remember what we said this morning about FlightData? It was a pure data-holding class — it didn't reach out to other classes, other classes reached into it.
Order is the same thing. It just holds data. It doesn't need to know about PricingService or EmailService — those classes go to Order to get data, not the other way around.
Ask yourself: does a piece of paper in a filing cabinet need to know who reads it?

→ Order collaborators should be: none

Everything else is correct. ✅

Your complete design.md file
markdown# Redesigned Order Processing System — CRC Cards

Class: Order
Responsibilities:
- Store order data (customerName, email, item, price)
- Provide order details to requesting classes
Collaborators:
- None

Class: PricingService
Responsibilities:
- Calculate tax on order price
- Apply discount based on order total
- Compute and return final price
Collaborators:
- Order

Class: ReceiptPrinter
Responsibilities:
- Print order summary to screen
- Display final price from PricingService
Collaborators:
- Order
- PricingService

Class: OrderRepository
Responsibilities:
- Save completed order to file
Collaborators:
- Order

Class: EmailService
Responsibilities:
- Send confirmation email to customer
Collaborators:
- Order

Class: ActivityLogger
Responsibilities:
- Log order activity with timestamp
Collaborators:
- Order

Q2 Complete Checklist
FileStatusevaluation.md✅ Donedesign.md✅ Donedevelopment_log_q2.md📋 Paste conversation transcript