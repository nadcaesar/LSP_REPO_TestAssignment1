# AI_PROMPTS_A3.md — Assignment 3 (ETL Pipeline with AI)

This document records the prompts I used while completing Assignment 3 and how the responses affected my implementation.

---

## Prompt 1 — Understanding the assignment
**Prompt:**
I need you to pay attention to HW3 rn and I need help with it. Can you explain what it is I have to do or how I would go about it?

**Result / What I used:**
I learned that HW3 is not about changing the ETL logic, but redesigning the program into an object-oriented structure.  
I identified the required responsibilities:
- Product object
- Reader
- Transformer
- Writer
- Pipeline coordinator

---

## Prompt 2 — Using class notes and planning reflection
**Prompt:**
Here are my notes from class. Also know that for this assignment I need to include a 1-2 page comparison between assignment 2 and assignment 3 and also include an AI transcript of the prompts I asked you for a MD file. My comfort level with the class is pretty low because I don’t know much about Java.

**Result / What I used:**
Connected lecture concepts (objects, responsibilities, collaboration) to the ETL redesign and planned both the reflection document and transcript file.

---

## Prompt 3 — Simpler implementation
**Prompt:**
keep it simple for all of the files that we have to create and keep track of those changes for me to include in the reflection doc

**Result / What I used:**
Chose a beginner-friendly object-oriented design using a small number of classes and simple fields instead of advanced Java features.

---

## Prompt 4 — Product design decision
**Prompt:**
Option 1

**Context:**
This answered the question about whether the Product object should store PriceRange.

**Result / What I used:**
Updated the design so the Product object stores the computed PriceRange directly, simplifying the transformer and writer logic.

---

## Prompt 5 — Compilation output confusion
**Prompt:**
it Compiled but I dont know where it compiled

**Result / What I used:**
Learned that Java places compiled `.class` files into the `out/` directory when using `javac -d out`, following the package folder structure.

---

## Prompt 6 — Running the program
**Prompt:**
If I wanted to run the file what would that command be and what other changes to the command can I make

**Result / What I used:**
Used the command:
javac -d out src/org/howard/edu/lsp/assignment3/*.java
java -cp out org.howard.edu.lsp.assignment3.ETLPipeline


I also learned that:
- `-cp` sets the classpath
- the package name must match the folder structure
- the root directory of compiled classes must be specified when running Java

---

## Prompt 7 — Moving to next components
**Prompt:**
Next please

**Result / What I used:**
Implemented the coordinator class (ETLPipeline) that connects the Reader, Transformer, and Writer.

---

## Prompt 8 — Generating reflection and transcript
**Prompt:**
Can you do that please

**Result / What I used:**
Generated a reflection comparing procedural vs object-oriented design and produced the transcript template.

---

## Testing and Verification (My own validation step)

To verify correctness, I created a second folder:

This folder contained the same `products.csv` input file.

I ran the pipeline twice:
1. Using `data/products.csv` → output `data/transformed_products.csv`
2. Using `datab/products.csv` → output `datab/transformed_products.csv`

I compared both outputs with the expected output and confirmed they were identical.

This confirmed that the refactored object-oriented design produced the same behavior as Assignment 2.