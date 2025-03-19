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
    void testCheckRows() {
        BingoCard card = new BingoCard("TestCard");
        for (int j = 0; j < 5; j++) {
            card.addValue(0, j, "XX"); // Fill the first row with "XX"
        }
        Pattern pattern = new Pattern("ROW");
        assertTrue(pattern.matches(card), "Pattern should match the filled row");

    }
}
