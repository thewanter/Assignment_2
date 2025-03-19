import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class PatternTest {
    @Test
    void testMatches() {

    }

    /**
     * Test the matches method for a BingoCard with a filled row.
     * This test checks if the pattern correctly identifies a complete row in the
     * BingoCard.
     */
    @Test
    public void testCheckRows() {
        BingoCard card = new BingoCard("1");
        card.display();
        for (int j = 0; j < 5; j++) {
            card.addValue(0, j, "XX"); // Fill the first row with "XX"
        }
        Pattern pattern = new Pattern("ROW");
        assertTrue(pattern.matches(card), "Pattern should match the filled row");
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

}
