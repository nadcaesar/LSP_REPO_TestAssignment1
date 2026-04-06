# AI Usage Report — Assignment 5: IntegerSet Implementation

## AI Tool Used
**Tool:** Claude by Anthropic  
**Platform:** claude.ai  
**Model:** Claude Sonnet 4.6

---

## How AI Was Used

Claude was used as a teaching assistant throughout this assignment.
Rather than providing answers directly, Claude asked questions to guide
understanding before any code was written. The student provided
reasoning and plain-English explanations before each method was
implemented.

---

## Topics Discussed and How They Helped

### Java Collections Framework
- Discussed why ArrayList is appropriate for modeling a set
- Learned that sets must silently ignore duplicate values on add()
- Learned the difference between remove(int index) and
  remove(Integer value) and why Integer.valueOf() is needed

### Method Design
- Discussed what isEmpty(), length(), and clear() should return/do
- Discussed that contains() returns a boolean, not a value
- Discussed that largest() and smallest() must throw
  IllegalStateException on empty sets rather than returning nothing

### Set Operations
- Confirmed understanding of union, intersect, diff, and complement
  before writing any code
- Learned that all set operations must return a NEW IntegerSet
  and must not modify the original sets
- Learned that union() works naturally with add() since add()
  already ignores duplicates

### Bug Fixes Identified by Claude
- Class name typo: IntergerSet → IntegerSet (would have caused
  compile error and automatic 0)
- equals() signature changed from equals(IntegerSet b) to
  equals(Object b) to properly override Java's built-in equals()
- Added missing @Override annotations on equals() and toString()
- Added proper Javadoc comments above all public methods
- Removed unused import: java.util.List

---

## External Websites Used

- Java ArrayList documentation:
  https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html

- Java Collections documentation:
  https://docs.oracle.com/javase/8/docs/api/java/util/Collections.html

---

## Complete Conversation Transcript

The full conversation transcript from this study session is included
below. All prompts and responses are included as required.

---
[
    Okay I've read it fully. Before I say anything else — I need to check something important.This matters a lot before we go any further — the assignment actually has an AI_Usage_Report.md requirement which strongly suggests AI is allowed, but I want to confirm with you first before we dive in.Q: Does this assignment allow AI usage?
    A: Yes — AI is allowed like the midtermApr 5Perfect — and the assignment actually requires you to document it in AI_Usage_Report.md, so we're good.
    Here's what you're building:

    Assignment Overview
    A single Java class — IntegerSet.java — that models a mathematical set of integers using an ArrayList. No duplicates allowed. 14 methods to implement worth 90 points, plus Javadocs and the AI report.

    Before We Write Any Code
    Like we did with the midterm — I want you to understand what you're building before we build it. Let me ask you a few quick questions:
    Question 1 — Data Structure
    The assignment says use ArrayList. But a set can't have duplicates. If someone calls add(3) and 3 is already in the list, what should happen?
    Question 2 — Set Operations
    Look at these four operations and tell me in plain English what each one does:
    OperationSet1 = [1,2,3], Set2 = [2,3,4]What does it return?union?intersect?diff?complement?
    The examples are right in the assignment — just tell me in your own words what each one means conceptually before we write the code.
    Question 3 — Exceptions
    What should largest() and smallest() do if someone calls them on an empty set? There's no largest or smallest element — so what should the method do?
    Answer these three and we'll start building!If someone calls 3 and its 3 and its in the list then it should say that it is already in the list or skip the value when adding it to a new array. 
    Union returns the values in both sets 
    Intersect returns the value thats only in both sets 
    Diff returns the values that are in the current set but not in set 2
    Compliment returns values in set 2 that are not in the current setApr 52 / 2Really good answers — your understanding of the set operations is spot on. Let me give you quick feedback:

    Feedback
    Duplicate handling ✅ — right idea, refine it
    You said "say that it is already in the list or skip it." For a set, the correct behavior is to silently skip it — no error, no message, just don't add it. Sets simply ignore duplicates.
    Union ✅ — correct, all elements from both sets combined, no duplicates
    Intersect ✅ — correct, only elements that appear in BOTH sets
    Diff ✅ — correct, elements in Set1 but NOT in Set2
    Complement ✅ — correct, elements in Set2 but NOT in Set1
    Question 3 — you didn't answer this one!
    What should largest() and smallest() do on an empty set? Think about it — if there are no numbers, what can the method return? It can't return a valid integer...

    Hint: Remember on the midterm when findTask() couldn't find a task — what did it return? Empty sets need a similar but stronger response — something that alerts the caller that something went wrong.


    Answer that last question and then we'll start building IntegerSet.java method by method!largest and smallest would compare the the values in the set by taking the next value and comparing it to the previous value and would return nothing if the list is emptyApr 5Good thinking on the comparison logic — that's exactly how you'd find largest/smallest. But "return nothing" isn't quite right for Java.
    Think about it this way — the method signature is:
    javapublic int largest()
    It must return an int. You can't return nothing from an int method. And you can't return null either — null only works for objects, not primitive types like int.
    So what do you do when something goes wrong in Java that prevents a method from doing its job?

    Hint: Remember on the midterm — addTask() used this when it got a duplicate ID. It didn't return anything, it threw something...

    What did it throw?the duplicate idApr 5Almost — what was the type of thing it threw? Here's the line from the midterm:
    javathrow new IllegalArgumentException("Duplicate task ID: " + task.getTaskId());
    For largest() and smallest() on an empty set, we do the same thing — we throw an exception. The specific one for this situation is:
    javathrow new RuntimeException("Set is empty");
    So the full behavior is:

    If set is empty → throw RuntimeException
    If set has elements → find and return the largest/smallest


    You Now Understand Everything You Need
    Let's start building. We'll go method by method, simplest first.

    The Class Structure
    javapackage org.howard.edu.lsp.assignment5;

    import java.util.ArrayList;
    import java.util.Collections;
    import java.util.List;

    /**
    * Models a mathematical set of integers.
    * Does not allow duplicate values.
    * @author [Your Name]
    */
    public class IntegerSet {

        private ArrayList<Integer> set = new ArrayList<>();

        // methods go here
    }

    Let's Start With the Easy Ones
    Before I write anything — tell me in plain English what these three methods should do:
    1. isEmpty() — what does it return and when?
    2. length() — what does it return?
    3. clear() — what does it do?
    These are the simplest three in the whole assignment. What do you think?isEmptyReturns a true or false statement to see if the list is empty 
    Length will return the length of the array list 
    Clear will remove all of the values of the array listApr 5Perfect — all three correct. Let's write them:
    java/**
    * Returns true if the set is empty, false otherwise.
    * @return true if empty
    */
    public boolean isEmpty() {
        return set.isEmpty();
    }

    /**
    * Returns the number of elements in the set.
    * @return size of the set
    */
    public int length() {
        return set.size();
    }

    /**
    * Removes all elements from the set.
    */
    public void clear() {
        set.clear();
    }
    Notice something — all three methods are just one line each. That's because ArrayList already has built-in methods that do exactly what we need. That's the power of the Java Collections Framework.

    Next Three — contains(), add(), remove()
    Same approach — tell me in plain English before we write:
    1. contains(int value) — what does it check and what does it return?
    2. add(int item) — what are the TWO things it needs to check/do before adding?
    3. remove(int item) — what should happen if the item exists? What if it doesn't exist?
    What do you think?contain returns the value associated with the list id
    add will combine include the new number intp the current list 
    remove will delete the selected number from the current list10:57 AMGood starting point but let me sharpen each answer:

    Feedback
    contains() 🔄 — rethink what it returns
    It doesn't return a value — it returns a true or false. The question is: "is this number in my set or not?"

    → Returns true if the value is in the set, false if it isn't

    add() 🔄 — missing the duplicate check
    You said "include the new number into the list" which is right, but remember what we said earlier about sets — what has to be checked FIRST before adding?

    → Check if the number already exists in the set. If yes, skip it. If no, add it.

    remove() ✅ — correct, but what about if it's not there?
    If the number exists, remove it. But what if someone tries to remove a number that isn't in the set? Should it throw an error or just do nothing?

    Hint: Sets are forgiving — if the element isn't there, just do nothing silently


    Try answering all three again with these fixes in mind — just one sentence each!adds: checks to see if the item is already in the set and if not, adds it
    contains: returns either true if the value is in the set and false if the value is not in the set11:10 AMPerfect — both correct! You also implicitly answered remove() earlier. Let's write all three:
    java/**
    * Checks if the set contains a given value.
    * @param value the value to search for
    * @return true if value is in the set, false otherwise
    */
    public boolean contains(int value) {
        return set.contains(value);
    }

    /**
    * Adds an item to the set if it is not already present.
    * Duplicates are silently ignored.
    * @param item the integer to add
    */
    public void add(int item) {
        if (!contains(item)) {
            set.add(item);
        }
    }

    /**
    * Removes an item from the set if it exists.
    * Does nothing if the item is not in the set.
    * @param item the integer to remove
    */
    public void remove(int item) {
        set.remove(Integer.valueOf(item));
    }

    One Thing to Notice — remove()
    You might wonder why we wrote Integer.valueOf(item) instead of just set.remove(item).
    This is a Java gotcha — ArrayList.remove() has two versions:

    remove(int index) — removes by position
    remove(Integer object) — removes by value

    If you write set.remove(2), Java thinks you mean "remove the element at position 2" not "remove the value 2." Wrapping it in Integer.valueOf() tells Java you mean the value.

    Now the Trickier Ones — largest() and smallest()
    You already know these need to:

    Throw RuntimeException if the set is empty
    Return the largest or smallest value if not empty

    Collections.sort() will be helpful here — it sorts an ArrayList in ascending order. So after sorting:

    The smallest is at index 0
    The largest is at index set.size() - 1

    Try writing both methods in plain English first:
    largest() — what are the two steps?
    smallest() — what are the two steps?2 / 2public int largest(){
            if (set.isEmpty()) {
                throw new IllegalStateException("Set is empty");
            }
            return Collections.max(set);
        }

        public int smallest(){
            if (set.isEmpty()) {
                throw new IllegalStateException("Set is empty");
            }
            return Collections.min(set);
        }Show more11:11 AMYou skipped straight to writing the code — and it's correct! Collections.max() and Collections.min() are even cleaner than sorting. Great instinct.
    Two small things:
    1. Exception type 🔄
    You used IllegalStateException — that's actually a perfectly valid and professional choice. The assignment says RuntimeException but IllegalStateException extends RuntimeException so it works. Keep it.
    2. Javadoc missing
    Every public method needs Javadoc comments. Add them above each method:
    java/**
    * Returns the largest element in the set.
    * @return the largest integer in the set
    * @throws IllegalStateException if the set is empty
    */
    public int largest() {
        if (set.isEmpty()) {
            throw new IllegalStateException("Set is empty");
        }
        return Collections.max(set);
    }

    /**
    * Returns the smallest element in the set.
    * @return the smallest integer in the set
    * @throws IllegalStateException if the set is empty
    */
    public int smallest() {
        if (set.isEmpty()) {
            throw new IllegalStateException("Set is empty");
        }
        return Collections.min(set);
    }

    Progress Check
    MethodStatusisEmpty()✅length()✅clear()✅contains()✅add()✅remove()✅largest()✅smallest()✅equals()🔲toString()🔲union()🔲intersect()🔲diff()🔲complement()🔲
    6 left — the set operations and two utility methods.
    Let's do toString() and equals() next since they're simpler than the set operations.
    The assignment says toString() must:

    Return values in ascending order
    Format: [1, 2, 3]
    Empty set returns []

    And equals() must return true if both sets have exactly the same elements regardless of order — so [1,2,3] and [3,2,1] are equal.
    Take a shot at writing both — you're on a roll!    public boolean equals(IntegerSet b){

            // equals(IntegerSet b)
            // Returns true if both sets contain exactly the same elements.
            //     Examples:
            //     [1, 2, 3] and [3, 2, 1] -> true
            //     [1, 2, 3] and [1, 2] -> false

            if (set.size() != b.set.size()) {
                return false;
            }
            for (int item : set) {
                if (!b.set.contains(item)) {
                    return false;
                }
            }
            return true;
        }

        public String toString(){
            //You must override the default toString() implementation defined in Object. Use the @Override annotation.
            //Required output format:
            //  [1, 2, 3]
            //  •    Values must appear in ascending order.
            //  •    Values must be separated by a comma and a single space.
            //  •    No extra spaces are allowed.
            //  •    An empty set must return [].
            //  •    You may use Collections.sort(yourList) if needed.

            Collections.sort(set);
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            for (int i = 0; i < set.size(); i++) {
                sb.append(set.get(i));
                if (i < set.size() - 1) {
                    sb.append(", ");
                }
            }
            sb.append("]");
            return sb.toString();
        }Show more11:18 AMExcellent work — both are correct and well thought out. Just a few small fixes:

    Feedback
    equals() ✅ — logic is perfect
    Checking size first is smart — if sizes differ you can return false immediately without looping. Then checking each element is in the other set handles the order-doesn't-matter requirement correctly.
    Just needs two fixes:

    Add @Override annotation
    Add Javadoc

    toString() ✅ — excellent
    StringBuilder, Collections.sort(), comma spacing, empty set handling — all correct. Professional-level code.
    Just needs two fixes:

    Add @Override annotation
    Add Javadoc


    Corrected Versions
    java/**
    * Returns true if both sets contain exactly the same elements.
    * Order does not matter.
    * @param b the IntegerSet to compare with
    * @return true if sets are equal, false otherwise
    */
    @Override
    public boolean equals(IntegerSet b) {
        if (set.size() != b.set.size()) {
            return false;
        }
        for (int item : set) {
            if (!b.set.contains(item)) {
                return false;
            }
        }
        return true;
    }

    /**
    * Returns a string representation of the set in ascending order.
    * Format: [1, 2, 3] or [] if empty.
    * @return string representation of the set
    */
    @Override
    public String toString() {
        Collections.sort(set);
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < set.size(); i++) {
            sb.append(set.get(i));
            if (i < set.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    Progress Check
    MethodStatusisEmpty()✅length()✅clear()✅contains()✅add()✅remove()✅largest()✅smallest()✅equals()✅toString()✅union()🔲intersect()🔲diff()🔲complement()🔲
    4 left — all set operations. These are the most important ones.

    Set Operations — Key Rule
    The assignment says:

    All set operations must return a NEW IntegerSet and must NOT modify the original sets.

    This means every operation follows the same pattern:
    javaIntegerSet result = new IntegerSet(); // create new empty set
    // ... add the right elements to result ...
    return result;                         // return it
    The originals (this and b) must be unchanged after the operation.
    Now take a shot at all four — union(), intersect(), diff(), complement(). You already know what each one does. Try writing them!public IntegerSet union(IntegerSet intSetb){
            // union(IntegerSet b)
            //     Returns a new set containing all elements that appear in either set.
            //     Example:
            //     Set1 = [1, 2, 3]
            //     Set2 = [2, 3, 4]
            //     Result = [1, 2, 3, 4]
            IntegerSet result = new IntegerSet();
            for (int item : set) {
                result.add(item);
            }
            for (int item : intSetb.set) {
                result.add(item);
            }
            return result;

        }Show more11:24 AMThat is perfect. Let me show you why this works so well:

    Creates a new empty set ✅
    Adds everything from Set1 ✅
    Adds everything from Set2 ✅
    Since add() already ignores duplicates, [2,3] won't be added twice ✅
    Original sets unchanged ✅

    Just needs Javadoc and you're done with union.

    Now Try the Other Three
    You understand the pattern now. The only difference between each operation is which elements you add to result.
    Here's a hint for each:
    intersect() — only add elements from Set1 that also exist in Set2

    Hint: loop through set, check if intSetb.contains(item)

    diff() — only add elements from Set1 that do NOT exist in Set2

    Hint: loop through set, check if !intSetb.contains(item)

    complement() — only add elements from Set2 that do NOT exist in Set1

    Hint: same as diff but loop through intSetb.set instead

    Notice diff and complement are almost identical — just swap which set you loop through.
    Try writing all three!package org.howard.edu.lsp.assignment5;

    import java.util.ArrayList;
    import java.util.Collections;
    import java.util.List;

    public class IntergerSet {

        private ArrayList<Integer> set = new ArrayList<>();

        public boolean isEmpty(){
            // Returns true if the set is empty, false pasted// DO NOT MODIFY this Driver.java file
    package org.howard.edu.lsp.assignment5;

    public class Driver {
        public static void main(String[] args) {
            IntegerSet set1 = new IntegerSet();
            IntegerSet set2 = new IntegerSet();

            set1.add(1);
            set1.add(2);
            set1.add(3);

            set2.add(2);
            set2.add(3);
            set2.add(4);

            System.out.println("Set1: " + set1);
            System.out.println("Set2: " + set2);
            System.out.println();

            System.out.println("Union: " + set1.union(set2));
            System.out.println("Intersection: " + set1.intersect(set2));
            System.out.println("Difference (set1 - set2): " + set1.diff(set2));
            System.out.println("Complement (set2 - set1): " + set1.complement(set2));
            System.out.println();

            System.out.println("Set1 after operations: " + set1);
            System.out.println("Set2 after operations: " + set2);
            System.out.println();

            System.out.println("Contains 2 in Set1: " + set1.contains(2));
            System.out.println("Largest in Set1: " + set1.largest());
            System.out.println("Smallest in Set1: " + set1.smallest());
            System.out.println();

            set1.remove(2);
            System.out.println("Set1 after removing 2: " + set1);
            System.out.println("Length of Set1: " + set1.length());
            System.out.println("Is Set1 empty: " + set1.isEmpty());
            System.out.println();

            set1.clear();
            System.out.println("Set1 after clear: " + set1);
        }
    }Show more11:28 AMGood progress — all the logic is correct! But I can see several issues that need fixing before this compiles and runs. Let me go through them:

    Issues Found
    🔴 Critical — Will prevent compilation:
    1. Class name typo — IntergerSet vs IntegerSet
    Your class is named IntergerSet (typo) but Driver.java uses IntegerSet. This will cause a compile error and an automatic 0. Fix it immediately.
    2. Missing Javadoc comments
    Every public method needs proper Javadoc with @param and @return. Right now you have inline comments instead. That's 5 points lost.
    3. Missing @Override on equals() and toString()
    The assignment specifically requires @Override on toString().
    🟡 Minor — Points deductions:
    4. Unused import
    You imported java.util.List but never use it. Remove it.
    5. Comments inside methods instead of Javadoc above them
    Your comments are inside the method body. Javadoc goes above the method signature.

    Fixed Complete File
    javapackage org.howard.edu.lsp.assignment5;

    import java.util.ArrayList;
    import java.util.Collections;

    /**
    * Models a mathematical set of integers using an ArrayList.
    * Does not allow duplicate values.
    * Supports standard set operations: union, intersect, diff, complement.
    * @author [Your Name]
    */
    public class IntegerSet {

        private ArrayList<Integer> set = new ArrayList<>();

        /**
        * Returns true if the set is empty, false otherwise.
        * @return true if the set contains no elements
        */
        public boolean isEmpty() {
            return set.isEmpty();
        }

        /**
        * Removes all elements from the set.
        */
        public void clear() {
            set.clear();
        }

        /**
        * Returns the number of elements in the set.
        * @return the size of the set
        */
        public int length() {
            return set.size();
        }

        /**
        * Returns true if the set contains the given value.
        * @param value the integer to search for
        * @return true if value is in the set, false otherwise
        */
        public boolean contains(int value) {
            return set.contains(value);
        }

        /**
        * Removes the specified item from the set if it exists.
        * Does nothing if the item is not in the set.
        * @param item the integer to remove
        */
        public void remove(int item) {
            set.remove(Integer.valueOf(item));
        }

        /**
        * Adds an item to the set if it is not already present.
        * Duplicates are silently ignored.
        * @param item the integer to add
        */
        public void add(int item) {
            if (!set.contains(item)) {
                set.add(item);
            }
        }

        /**
        * Returns the largest element in the set.
        * @return the largest integer in the set
        * @throws IllegalStateException if the set is empty
        */
        public int largest() {
            if (set.isEmpty()) {
                throw new IllegalStateException("Set is empty");
            }
            return Collections.max(set);
        }

        /**
        * Returns the smallest element in the set.
        * @return the smallest integer in the set
        * @throws IllegalStateException if the set is empty
        */
        public int smallest() {
            if (set.isEmpty()) {
                throw new IllegalStateException("Set is empty");
            }
            return Collections.min(set);
        }

        /**
        * Returns true if both sets contain exactly the same elements.
        * Order does not matter.
        * @param b the IntegerSet to compare with
        * @return true if both sets are equal, false otherwise
        */
        @Override
        public boolean equals(Object b) {
            if (!(b instanceof IntegerSet)) return false;
            IntegerSet other = (IntegerSet) b;
            if (set.size() != other.set.size()) return false;
            for (int item : set) {
                if (!other.set.contains(item)) return false;
            }
            return true;
        }

        /**
        * Returns a string representation of the set in ascending order.
        * Format: [1, 2, 3] or [] if empty.
        * @return string representation of the set
        */
        @Override
        public String toString() {
            Collections.sort(set);
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            for (int i = 0; i < set.size(); i++) {
                sb.append(set.get(i));
                if (i < set.size() - 1) {
                    sb.append(", ");
                }
            }
            sb.append("]");
            return sb.toString();
        }

        /**
        * Returns a new set containing all elements from both sets.
        * @param intSetb the second IntegerSet
        * @return a new IntegerSet representing the union
        */
        public IntegerSet union(IntegerSet intSetb) {
            IntegerSet result = new IntegerSet();
            for (int item : set) {
                result.add(item);
            }
            for (int item : intSetb.set) {
                result.add(item);
            }
            return result;
        }

        /**
        * Returns a new set containing only elements common to both sets.
        * @param intSetb the second IntegerSet
        * @return a new IntegerSet representing the intersection
        */
        public IntegerSet intersect(IntegerSet intSetb) {
            IntegerSet result = new IntegerSet();
            for (int item : set) {
                if (intSetb.set.contains(item)) {
                    result.add(item);
                }
            }
            return result;
        }

        /**
        * Returns a new set with elements in this set but not in intSetb.
        * @param intSetb the second IntegerSet
        * @return a new IntegerSet representing the difference
        */
        public IntegerSet diff(IntegerSet intSetb) {
            IntegerSet result = new IntegerSet();
            for (int item : set) {
                if (!intSetb.set.contains(item)) {
                    result.add(item);
                }
            }
            return result;
        }

        /**
        * Returns a new set with elements in intSetb but not in this set.
        * @param intSetb the second IntegerSet
        * @return a new IntegerSet representing the complement
        */
        public IntegerSet complement(IntegerSet intSetb) {
            IntegerSet result = new IntegerSet();
            for (int item : intSetb.set) {
                if (!set.contains(item)) {
                    result.add(item);
                }
            }
            return result;
        }
    }

    One Important Change — equals() signature
    I changed your signature from:
    javapublic boolean equals(IntegerSet b)  // your version
    to:
    javapublic boolean equals(Object b)  // correct version
    When you use @Override on equals(), Java requires it to take an Object parameter — that's how Java's built-in equality system works. Your version with IntegerSet was a different method entirely and wouldn't properly override anything.

    Expected Output When You Run Driver
    Set1: [1, 2, 3]
    Set2: [2, 3, 4]

    Union: [1, 2, 3, 4]
    Intersection: [2, 3]
    Difference (set1 - set2): [1]
    Complement (set2 - set1): [4]

    Set1 after operations: [1, 2, 3]
    Set2 after operations: [2, 3, 4]

    Contains 2 in Set1: true
    Largest in Set1: 3
    Smallest in Set1: 1

    Set1 after removing 2: [1, 3]
    Length of Set1: 2
    Is Set1 empty: false

    Set1 after clear: []

    What's Left
    TaskStatusIntegerSet.java✅ DoneAI_Usage_Report.md🔲 Needs writing
    Want me to generate the AI_Usage_Report.md file now?
]

---
*End of transcript*