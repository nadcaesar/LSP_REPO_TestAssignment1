# Final Exam — Question 2 Design Explanation

## Template Method Pattern — How It Is Used

The abstract class Report defines the fixed workflow for report
generation. It declares four abstract methods — loadData(),
formatHeader(), formatBody(), and formatFooter() — and a final
template method generateReport() that calls them in a fixed order:
loadData → formatHeader → formatBody → formatFooter.

StudentReport and CourseReport are concrete subclasses that extend
Report and provide their own implementations of each step. StudentReport
loads and displays a student name and GPA, while CourseReport loads and
displays a course name and enrollment count. Each subclass fills in the
details of the steps without changing the order they are called.

generateReport() is marked final to prevent subclasses from overriding
it and changing the fixed workflow. The template — the order of steps —
belongs to the parent class and must remain consistent across all report
types.

The Driver demonstrates polymorphism by storing both StudentReport and
CourseReport in a List<Report> and calling generateReport() on each
element without knowing the specific type. Java automatically dispatches
the correct implementation for each report type at runtime.