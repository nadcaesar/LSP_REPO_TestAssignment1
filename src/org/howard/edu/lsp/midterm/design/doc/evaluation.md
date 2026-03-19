# OrderProcessor Design Evaluation

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