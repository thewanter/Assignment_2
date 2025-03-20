import static org.junit.Assert.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BingoManagerTest {

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
     * Test the countBingos method for a BingoCard with first filled row.
     * This test checks if the BingoManager correctly counts the number of bingos
     */
    @Test
    public void testBM1() {
        for (int i = 0; i < 5; i++) {
            card.addValue(0, i, "XX"); // Fill the first row with "XX"
        }

        BingoManager manager = new BingoManager();
        manager.addPattern(new Pattern("ROW"));
        assertEquals(1, manager.countBingos(card));
    }

    /**
     * BM2: 1st, 3rd, and last row fully marked
     */
    @Test
    public void testBM2() {
        for (int i = 0; i < 5; i++) {
            card.addValue(0, i, "XX"); // Fill the first row with "XX"
            card.addValue(2, i, "XX"); // Fill the third row with "XX"
            card.addValue(4, i, "XX"); // Fill the last row with "XX"
        }

        BingoManager manager = new BingoManager();
        manager.addPattern(new Pattern("ROW"));

        assertEquals(3, manager.countBingos(card));
    }

    @Test
    public void testBM3() {
        for (int i = 0; i < 5; i++) {
            card.addValue(0, i, "XX"); // Fill the first row with "XX"
            card.addValue(2, i, "XX"); // Fill the third row with "XX"
            card.addValue(4, i, "XX"); // Fill the last row with "XX"
        }

        BingoManager manager = new BingoManager();
        for (int i = 0; i < 5; i++) {
            card.addValue(i, 4, "XX"); // Fill the last column with "XX"
        }

        manager.addPattern(new Pattern("ROW"));
        assertEquals(3, manager.countBingos(card)); // Check for 3 bingos (rows)
    }
}
