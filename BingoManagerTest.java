import static org.junit.Assert.*;

import org.junit.jupiter.api.Test;

public class BingoManagerTest {
    /**
     * Test the countBingos method for a BingoCard with first filled row.
     * This test checks if the BingoManager correctly counts the number of bingos
     */
    @Test
    public void testBM1() {
        BingoCard card = new BingoCard("TestCard");
        for (int i = 0; i < 5; i++) {
            card.addValue(0, i, "XX"); // Fill the first row with "XX"
        }

        BingoManager manager = new BingoManager();
        manager.addPattern(new Pattern("ROW"));
        assertEquals(1, manager.countBingos(card));
    }

}
