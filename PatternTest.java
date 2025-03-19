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
            card.addValue(2, j, "XX"); // Fill the first row with "XX"
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
            card.addValue(4, j, "XX"); // Fill the first row with "XX"
        }
        Pattern pattern = new Pattern("ROW");
        assertTrue(pattern.matches(card), "Pattern should match the filled row (P3)");
    }

    /**
     * Test the matches method for a BingoCard with a filled column.
     * This test checks if the pattern correctly identifies a complete column in the
     */
    @Test
    public void testCheckColumns() {
        BingoCard card = new BingoCard("1");
        card.display();
        for (int i = 0; i < 5; i++) {
            card.addValue(i, 0, "XX"); // Fill the first column with "XX"
        }
        Pattern pattern = new Pattern("COLUMN");
        assertTrue(pattern.matches(card), "Pattern should match the filled column");
    }

    /**
     * Test the matches method for a BingoCard with a filled diagonal.
     * This test checks if the pattern correctly identifies a complete diagonal in
     * the
     */
    @Test
    public void testCheckDiagonals() {
        BingoCard card = new BingoCard("1");
        card.display();
        for (int i = 0; i < 5; i++) {
            card.addValue(i, i, "XX"); // Fill the diagonal from top-left to bottom-right
        }
        Pattern pattern = new Pattern("DIAGONAL");
        assertTrue(pattern.matches(card), "Pattern should match the filled diagonal");
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
