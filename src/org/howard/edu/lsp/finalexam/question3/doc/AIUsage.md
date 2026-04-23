# AI Usage Report — Final Exam Question 3

## AI Tools Used

Claude by Anthropic (claude.ai)

## Prompts Used

1. What is the expected output of average(90, 80, 70)?
2. What letter grade should 85.0 return?
3. What are good boundary values to test for letterGrade and isPassing?
4. What input values trigger the IllegalArgumentException?
5. How does assertThrows work in JUnit 5?

## How AI Helped

Claude guided the student through identifying normal cases, boundary
values, and exception cases before writing any code. The student
provided the expected values for each test before Claude assembled
the final test class, ensuring understanding of each assertion used.

## Reflection

I learned that boundary testing means testing the exact values at the
edge of a condition — like exactly 90.0 for an A and exactly 60.0 for
passing — not just values in the middle. assertThrows confirms that
invalid inputs are properly rejected by the method.

---

[Q3 — JUnit Tests for GradeCalculator
Same approach — understand before we write.
Here's the GradeCalculator class you're testing:
javapublic double average(int score1, int score2, int score3)
// calculates average of 3 scores, throws IllegalArgumentException if any score < 0 or > 100

public String letterGrade(double average)
// A=90+, B=80+, C=70+, D=60+, F=below 60

public boolean isPassing(double average)
// returns true if average >= 60

You Need 7 Tests Total
RequirementWhat to test1 test for average()Normal case1 test for letterGrade()Normal case1 test for isPassing()Normal case2 boundary testsEdge values like exactly 90, exactly 602 exception testsScore below 0, score above 100

Before we write — answer these:
Normal cases:

What inputs would you give average(90, 80, 70) and what result do you expect?
If average is 85.0, what letter grade should come back?
If average is 75.0, is that passing?

Boundary cases:
4. What happens at exactly 90.0 — what letter grade?
5. What happens at exactly 60.0 — is that passing?
Exception cases:
6. What score value would trigger the exception?
7. What score value above the limit would trigger it?
Answer these and we'll write all 7 tests!You said: (90+80+70) then the sum is divided by 3(90+80+70) then the sum is divided by 3If the average is 85 then the letter grade shgould be a BIf the average is 75 then yes that is passingthe letter grade is an Ait is technically passing as 60.0 is the minimumIf there was an incorrect input for the score to be less than 0If you get extra credit on an assignment and get more than 1007:35 PMClaude responded: Perfect — all 7 correct.Perfect — all 7 correct. Let's write the tests:

GradeCalculatorTest.java
javapackage org.howard.edu.lsp.finalexam.question3;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit 5 test cases for GradeCalculator.
 * @author [Your Name]
 */
public class GradeCalculatorTest {

    GradeCalculator calc = new GradeCalculator();

    // ─── Normal Cases ────────────────────────────────────────────

    /**
     * Test average of three normal scores.
     * (90 + 80 + 70) / 3 = 80.0
     */
    @Test
    void testAverageNormalCase() {
        assertEquals(80.0, calc.average(90, 80, 70));
    }

    /**
     * Test letter grade for average of 85 — should return B.
     */
    @Test
    void testLetterGradeB() {
        assertEquals("B", calc.letterGrade(85.0));
    }

    /**
     * Test isPassing for average of 75 — should return true.
     */
    @Test
    void testIsPassingTrue() {
        assertTrue(calc.isPassing(75.0));
    }

    // ─── Boundary Cases ──────────────────────────────────────────

    /**
     * Boundary test — exactly 90.0 should return A.
     */
    @Test
    void testLetterGradeExactlyA() {
        assertEquals("A", calc.letterGrade(90.0));
    }

    /**
     * Boundary test — exactly 60.0 is the minimum passing grade.
     */
    @Test
    void testIsPassingExactly60() {
        assertTrue(calc.isPassing(60.0));
    }

    // ─── Exception Cases ─────────────────────────────────────────

    /**
     * Exception test — score below 0 should throw IllegalArgumentException.
     */
    @Test
    void testAverageScoreBelowZero() {
        assertThrows(IllegalArgumentException.class, () -> {
            calc.average(-1, 80, 70);
        });
    }

    /**
     * Exception test — score above 100 should throw IllegalArgumentException.
     */
    @Test
    void testAverageScoreAbove100() {
        assertThrows(IllegalArgumentException.class, () -> {
            calc.average(101, 80, 70);
        });
    }
}

Why Each Test Works
TestAssertion UsedWhytestAverageNormalCaseassertEqualschecks exact value 80.0testLetterGradeBassertEqualschecks exact string "B"testIsPassingTrueassertTruechecks boolean is truetestLetterGradeExactlyAassertEqualsboundary — exactly 90testIsPassingExactly60assertTrueboundary — exactly 60testAverageScoreBelowZeroassertThrowsexpects exception on -1testAverageScoreAbove100assertThrowsexpects exception on 101

Now let me generate all three files:Created 2 files, read a fileCreated 2 files, read a fileBoth files ready. Place them here:
org/howard/edu/lsp/finalexam/question3/GradeCalculatorTest.java
org/howard/edu/lsp/finalexam/question3/doc/AIUsage.md
]