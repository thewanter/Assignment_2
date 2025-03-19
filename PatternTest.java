import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class PatternTest {
    @Test
    void testMatches() {

    }

    /**
     * P1: 1st row fully marked
     * Test the matches method for a BingoCard with a filled row.
     * This test checks if the pattern correctly identifies a complete row in the
     * BingoCard.
     */
    @Test
    public void testP1() {
        BingoCard card = new BingoCard("1");
        card.display();
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
        BingoCard card = new BingoCard("1");
        card.display();
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
        BingoCard card = new BingoCard("1");
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
        BingoCard card = new BingoCard("1");
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
        BingoCard card = new BingoCard("1");
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
        BingoCard card = new BingoCard("1");
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
        BingoCard card = new BingoCard("1");
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
        BingoCard card = new BingoCard("1");
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
        BingoCard card = new BingoCard("1");
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
     * Test the matches method for a BingoCard with a filled diagonal (top-left).
     */
    @Test
    public void testP10() {
        BingoCard card = new BingoCard("1");
        card.display();
        for (int i = 0; i < 5; i++) {
            card.addValue(i, 4 - i, "XX"); // Fill the diagonal from top-left to bottom-right
        }
        Pattern pattern = new Pattern("DIAGONAL");
        assertTrue(pattern.matches(card), "Pattern should match the filled diagonal (P10)");
    }

    /**
     * P11: Top-left to Bottom-right fully marked
     * Test the matches method for a BingoCard with a filled diagonal (bottom-left).
     */
    public void testP11() {
        BingoCard card = new BingoCard("1");
        card.display();
        for (int i = 0; i < 5; i++) {
            card.addValue(i, i, "XX"); // Fill the diagonal from top-left to bottom-right
        }
        Pattern pattern = new Pattern("DIAGONAL");
        assertTrue(pattern.matches(card), "Pattern should match the filled diagonal (P11)");
    }

    /**
     * Test the matches method for a BingoCard with a custom pattern.
     * This test checks if the pattern correctly identifies a custom pattern in the
     */
    @Test
    public void testCheckCustom() {
        BingoCard card = new BingoCard("TestCard");
        int[][] patterns = { { 0, 0 }, { 0, 1 }, { 0, 2 }, { 1, 2 }, { 2, 2 } };
        for (int[] pattern : patterns) {
            card.addValue(pattern[0], pattern[1], "XX");
        }
        Pattern customPattern = new Pattern(Arrays.asList(patterns));
        assertTrue(customPattern.matches(card));
    }

}
