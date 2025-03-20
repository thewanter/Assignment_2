import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PatternTest {

    BingoCard card = new BingoCard("1");

    /**
     * This method is called before each test to set up the BingoCard with initial
     * values. This ensures that each test starts with a fresh BingoCard
     * containing a 5x5 grid filled with numbers from 0 to 24.
     */
    @BeforeEach
    public void setUp() {
        int num = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                card.addValue(i, j, "" + num);
                num++;
            }
        }
    }

    /**
     * P1: 1st row fully marked
     * Test the matches method for a BingoCard with a filled row.
     * This test checks if the pattern correctly identifies a complete row in the
     * BingoCard.
     */
    @Test
    public void testP1() {
        BingoCard card = this.card;

        for (int j = 0; j < 5; j++) {
            card.addValue(0, j, "XX"); // Fill the first row with "XX"
        }

        Pattern pattern = new Pattern("ROW");
        assertTrue(pattern.matches(card), "Pattern should match the filled row (P1)");
    }

    /**
     * P2: 3rd row fully marked
     * Test the matches method for a BingoCard with a filled row.
     */
    @Test
    public void testP2() {
        for (int j = 0; j < 5; j++) {
            card.addValue(2, j, "XX");
        }
        Pattern pattern = new Pattern("ROW");
        assertTrue(pattern.matches(card), "Pattern should match the filled row (P2)");
    }

    /**
     * P3: Last row fully marked
     * Test the matches method for a BingoCard with a filled row.
     */
    @Test
    public void testP3() {
        card.display();
        for (int j = 0; j < 5; j++) {
            card.addValue(4, j, "XX");
        }
        Pattern pattern = new Pattern("ROW");
        assertTrue(pattern.matches(card), "Pattern should match the filled row (P3)");
    }

    /**
     * P4: 1st row fully marked except the last column
     * Test the matches method for a BingoCard with a filled row.
     */
    @Test
    public void testP4() {
        card.display();
        for (int j = 0; j < 4; j++) {
            card.addValue(0, j, "XX");
        }
        Pattern pattern = new Pattern("ROW");
        assertFalse(pattern.matches(card), "Pattern should match the filled row (P4)");
    }

    /**
     * P5: 1st column fully marked
     * Test the matches method for a BingoCard with a filled column.
     */
    @Test
    public void testP5() {
        card.display();
        for (int i = 0; i < 5; i++) {
            card.addValue(i, 0, "XX");
        }
        Pattern pattern = new Pattern("COLUMN");
        assertTrue(pattern.matches(card), "Pattern should match the filled column (P5)");
    }

    /**
     * P6: 4rd column fully marked
     * Test the matches method for a BingoCard with a filled column.
     */
    @Test
    public void testP6() {
        card.display();
        for (int i = 0; i < 5; i++) {
            card.addValue(i, 3, "XX");
        }
        Pattern pattern = new Pattern("COLUMN");
        assertTrue(pattern.matches(card), "Pattern should match the filled column (P6)");
    }

    /**
     * P7: last column fully marked
     * Test the matches method for a BingoCard with a filled column.
     */
    @Test
    public void testP7() {
        card.display();
        for (int i = 0; i < 5; i++) {
            card.addValue(i, 4, "XX");
        }
        Pattern pattern = new Pattern("COLUMN");
        assertTrue(pattern.matches(card), "Pattern should match the filled column (P7)");
    }

    /**
     * P8: 1st row fully marked with "COLUMN" pattern
     * Test the matches method for a BingoCard with a filled row using a column
     * pattern.
     */
    @Test
    public void testP8() {
        card.display();
        for (int i = 0; i < 5; i++) {
            card.addValue(0, i, "XX"); // Fill the first row with "XX"
        }
        Pattern pattern = new Pattern("COLUMN");
        assertFalse(pattern.matches(card), "Pattern should match the filled column (P8)");
    }

    /**
     * P9: 2nd column fully marked except 2nd row
     * Test the matches method for a BingoCard with a filled column except for one
     * row. This test checks if the pattern correctly identifies a column that is
     * not
     */
    public void testP9() {
        card.display();
        for (int i = 0; i < 5; i++) {
            if (i == 1)
                continue; // Skip the 2nd row
            card.addValue(i, 1, "XX");
        }
        Pattern pattern = new Pattern("COLUMN");
        assertFalse(pattern.matches(card), "Pattern should match the filled column (P9)");
    }

    /**
     * P10: Top-right to Bottom-left fully marked
     * Test the matches method for a BingoCard with a filled diagonal (top-right to
     * bottom-left).
     */
    @Test
    public void testP10() {
        card.display();
        for (int i = 0; i < 5; i++) {
            card.addValue(i, 4 - i, "XX"); // Fill the diagonal from top-left to bottom-right
        }
        Pattern pattern = new Pattern("DIAGONAL");
        assertTrue(pattern.matches(card), "Pattern should match the filled diagonal (P10)");
    }

    /**
     * P11: Top-left to Bottom-right fully marked
     * Test the matches method for a BingoCard with a filled diagonal.
     */
    @Test
    public void testP11() {
        card.display();
        for (int i = 0; i < 5; i++) {
            card.addValue(i, i, "XX"); // Fill the diagonal from top-left to bottom-right
        }
        Pattern pattern = new Pattern("DIAGONAL");
        assertTrue(pattern.matches(card), "Pattern should match the filled diagonal (P11)");
    }

    /**
     * P12: 2nd row fully marked with "DIAGONAL" pattern
     * Test the matches method for a BingoCard with a filled row using a diagonal
     * pattern.
     */
    @Test
    public void testP12() {
        card.display();
        for (int i = 0; i < 5; i++) {
            card.addValue(1, i, "XX");
        }
        Pattern pattern = new Pattern("DIAGONAL");
        assertFalse(pattern.matches(card), "Pattern should match the filled diagonal (P12)");
    }

    /**
     * P13: Top row and middle column fully marked with "CUSTOME" pattern
     * Test the matches method for a BingoCard with a filled row using a custom
     * pattern.
     */
    @Test
    public void testP13() {
        card.display();
        for (int i = 0; i < 5; i++) {
            card.addValue(0, i, "XX");
        }

        for (int j = 0; j < 5; j++) {
            card.addValue(j, 2, "XX");
        }
        Pattern pattern = new Pattern("CUSTOM");
        assertTrue(pattern.matches(card), "Pattern should match the filled custom (P13)");
    }

    /**
     * P14: Entire card fully marked
     * Test the matches method for a BingoCard with a filled row and column using a
     * custom pattern.
     */
    @Test
    public void testP14() {
        card.display();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                card.addValue(i, j, "XX");
            }
        }
        Pattern pattern = new Pattern("CUSTOM");
        assertTrue(pattern.matches(card), "Pattern should match the filled custom (P14)");
    }

    /**
     * P15: Top row and middle column fully marked except for the middle space
     * Test the matches method for a BingoCard with a filled row and column using a
     * custom pattern.
     */
    @Test
    public void testP15() {
        card.display();
        for (int i = 0; i < 5; i++) {
            if (i == 2)
                continue; // Skip the middle row
            for (int j = 0; j < 5; j++) {
                card.addValue(i, j, "XX");
            }
        }
        Pattern pattern = new Pattern("CUSTOM");
        assertFalse(pattern.matches(card), "Pattern should match the filled custom (P15)");
    }

    /**
     * P16: Top row, bottom row, left column, and right column fully marked
     * 
     */
    @Test
    public void testP16() {
        card.display();
        for (int i = 0; i < 5; i++) {
            card.addValue(0, i, "XX"); // Fill the top row
            card.addValue(4, i, "XX"); // Fill the bottom row
            card.addValue(i, 0, "XX"); // Fill the left column
            card.addValue(i, 4, "XX"); // Fill the right column
        }
        Pattern pattern = new Pattern("CUSTOM");
        assertTrue(pattern.matches(card), "Pattern should match the filled custom (P16)");
    }

}
