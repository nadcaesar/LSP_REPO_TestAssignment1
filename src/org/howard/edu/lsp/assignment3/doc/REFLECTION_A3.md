# Assignment 3 Reflection (HW2 vs HW3): ETL Pipeline Redesign

## Overview
In Assignment 2, I built a working ETL pipeline that reads a CSV of products, applies transformations in a fixed order, and writes a transformed CSV output. The solution was primarily procedural: most of the logic lived inside one main class and a single control flow (read → parse → transform → write).

In Assignment 3, the goal was to keep the same ETL behavior but redesign the program using an object-oriented approach. Instead of a single “do-everything” pipeline, I refactored the project into a small set of collaborating classes. This redesign follows the object-oriented mindset from lecture: systems should be built from well-encapsulated classes where responsibility distribution and collaboration patterns are manageable. :contentReference[oaicite:2]{index=2}

## Assignment 2 Design (Procedural ETL)
My Assignment 2 solution focused on getting correct output and robust handling of invalid lines. Conceptually, it did:
- Extract: open the input file, read each line after the header
- Transform: uppercase names, apply the electronics discount, round, apply premium electronics rule, compute PriceRange
- Load: write the output file with a header row and transformed rows

However, most responsibilities were mixed together inside the main ETL class:
- File reading and parsing were inside the same loop that performed transformations.
- Transformation logic and output formatting happened in the same place.
- Debugging was harder because everything happened in one large method.

This approach worked, but it tightly coupled file I/O logic with business rules.

## Assignment 3 Design (Object-Oriented Redesign)
In Assignment 3, I redesigned the ETL pipeline as a small collection of classes that collaborate to complete the ETL task. This aligns with the definition from lecture: a *class* is a blueprint for objects, and an *object* is an instance of a class. :contentReference[oaicite:3]{index=3}

### New Class Structure (Simple OO)
I implemented a simple design with these core classes:

1. **Product**
   - A data container representing one product record (ProductID, Name, Price, Category, PriceRange).
   - This created an explicit domain object instead of using loose variables in a loop.

2. **ProductReader**
   - Responsible only for reading/parsing the CSV file and returning a list of Product objects.
   - Tracks counts like total rows read and rows skipped.

3. **ProductTransformer**
   - Responsible only for the transformation rules, in the required order.
   - Returns transformed Product objects.

4. **ProductWriter**
   - Responsible only for writing output: always writes the header row and then writes each transformed product.

5. **ETLPipeline**
   - A coordinator that connects the components:
     reader → transformer → writer
   - Prints the run summary after completion.

This structure is an example of “collaboration between classes” while keeping responsibilities simple and not overly complex. :contentReference[oaicite:4]{index=4}

## Key Improvements from HW2 → HW3
### 1) Separation of Concerns
The main improvement is that file I/O concerns are separated from transformation concerns:
- Reader handles input parsing.
- Transformer handles business rules.
- Writer handles output formatting.
- Pipeline coordinates.

This makes each class easier to understand and reduces the chance that changing one part (like file parsing) breaks another part (like transformation rules).

### 2) Better Abstraction with a Domain Object
In HW2, each row’s data existed as temporary variables. In HW3, each row is represented as a Product object, which bundles related data together. This is closer to object-oriented thinking: objects carry state (data), and behavior can be applied in specialized classes. :contentReference[oaicite:5]{index=5}

### 3) Maintainability and Debugging
Because each class has a single job, debugging becomes more targeted:
- If the output file is wrong, I can inspect ProductWriter.
- If transformation results are wrong, I can inspect ProductTransformer.
- If rows are being skipped unexpectedly, I can inspect ProductReader.

### 4) Avoiding “Too Many Tiny Classes”
A risk in object-oriented design is “proliferation of classes,” where designs become hard to debug and maintain (“ravioli code”). :contentReference[oaicite:6]{index=6}  
To avoid that, I kept the design intentionally small: only the classes needed for the ETL responsibilities. I did not create separate classes for each small operation. This strikes a balance between clean responsibilities and practical simplicity.

## Tradeoffs / Costs of the OO Design
The main downside of the HW3 design is that there are more files to manage and more moving parts compared to HW2. In a very small program, the procedural approach can feel quicker to write.

However, the OO version is more scalable. If new transformations are added, or file formats change, the design supports modifications without rewriting the entire pipeline. In larger projects, this modular structure is worth the extra setup.

## Conclusion
Assignment 2 focused on producing correct ETL output with robust validation in a mostly procedural style. Assignment 3 kept the same ETL requirements but improved the design using object-oriented decomposition. By splitting reading, transforming, and writing into separate collaborating classes, the code became easier to understand, debug, and extend, while also avoiding excessive class proliferation. :contentReference[oaicite:7]{index=7} :contentReference[oaicite:8]{index=8}