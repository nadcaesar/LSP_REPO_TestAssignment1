package org.howard.edu.lsp.finalexam.question3;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit 5 test class for GradeCalculator.
 *
 * @author Nicholas Caesar
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