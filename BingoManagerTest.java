import static org.junit.Assert.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BingoManagerTest {
    private BingoManager manager;
    private BingoCard card;

    /**
     * This method is called before each test to set up the BingoCard with initial
     * values. This ensures that each test starts with a fresh BingoCard
     * containing a 5x5 grid filled with numbers from 0 to 24.
     */
    @BeforeEach
    public void setUp() {
        manager = new BingoManager();
        card = new BingoCard("1"); // Create a new BingoCard with ID "1"
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
        manager.addPattern(new Pattern("ROW"));
        for (int i = 0; i < 5; i++) {
            card.addValue(0, i, "XX"); // Fill the first row with "XX"
        }
        assertEquals(1, manager.countBingos(card));
    }

    /**
     * BM2: 1st, 3rd, and last row fully marked
     */
    @Test
    public void testBM2() {
        manager.addPattern(new Pattern("ROW"));
        for (int i = 0; i < 5; i++) {
            card.addValue(0, i, "XX"); // Fill the first row with "XX"
            card.addValue(2, i, "XX"); // Fill the third row with "XX"
            card.addValue(4, i, "XX"); // Fill the last row with "XX"
        }
        assertEquals(3, manager.countBingos(card));
    }

    @Test
    public void testBM3() {
        manager.addPattern(new Pattern("ROW"));

        for (int i = 0; i < 5; i++) {
            card.addValue(0, i, "XX"); // Fill the first row with "XX"
            card.addValue(2, i, "XX"); // Fill the third row with "XX"
            card.addValue(4, i, "XX"); // Fill the last row with "XX"
        }

        for (int i = 0; i < 5; i++) {
            card.addValue(i, 4, "XX"); // Fill the last column with "XX"
        }

        assertEquals(3, manager.countBingos(card)); // Check for 3 bingos (rows)
    }

    /**
     * BM4: 1st, 3rd, and last row fully marked and last column fully marked
     */
    @Test
    public void testBM4() {
        manager.addPattern(new Pattern("ROW"));
        manager.addPattern(new Pattern("COLUMN"));

        for (int i = 0; i < 5; i++) {
            card.addValue(0, i, "XX"); // Fill the first row with "XX"
            card.addValue(2, i, "XX"); // Fill the third row with "XX"
            card.addValue(4, i, "XX"); // Fill the last row with "XX"
        }

        for (int i = 0; i < 5; i++) {
            card.addValue(i, 4, "XX"); // Fill the last column with "XX"
        }

        assertEquals(4, manager.countBingos(card)); // Check for 3 bingos (rows) and 1 bingo (column)
    }

    /**
     * BM5: full card marked
     * Test the countBingos method for a BingoCard with all rows and columns fully
     * marked.
     */
    @Test
    public void testBM5() {
        manager.addPattern(new Pattern("ROW"));
        manager.addPattern(new Pattern("COLUMN"));
        manager.addPattern(new Pattern("DIAGONAL"));

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                card.addValue(i, j, "XX"); // Fill the entire card with "XX"
            }
        }

        assertEquals(12, manager.countBingos(card)); // Check for 5 bingos (rows)
    }

    /**
     * BM6: full card marked with custom pattern
     */
    @Test
    public void testBM6() {
        manager.addPattern(new Pattern("ROW"));
        manager.addPattern(new Pattern("COLUMN"));
        manager.addPattern(new Pattern("DIAGONAL"));
        manager.addPattern(new Pattern("CUSTOM")); // Add custom pattern

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                card.addValue(i, j, "XX"); // Fill the entire card with "XX"
            }
        }

        assertEquals(14, manager.countBingos(card));
    }

    /**
     * BM7: full card marked with except for top right corner
     * Test the countBingos method for a BingoCard with all rows and columns fully
     */
    @Test
    public void testBM7() {
        manager.addPattern(new Pattern("ROW"));
        manager.addPattern(new Pattern("COLUMN"));
        manager.addPattern(new Pattern("DIAGONAL"));
        manager.addPattern(new Pattern("CUSTOM")); // Add custom pattern

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (i == 0 && j == 4) {
                    continue; // Skip the top right corner
                }
                card.addValue(i, j, "XX"); // Fill the entire card with "XX"
            }
        }

        assertEquals(10, manager.countBingos(card));
    }

    /**
     * BM8: full card marked except for 2nd row, 2nd column
     * Test the countBingos method for a BingoCard with all rows and columns fully
     */
    @Test
    public void testBM8() {
        manager.addPattern(new Pattern("ROW"));
        manager.addPattern(new Pattern("COLUMN"));
        manager.addPattern(new Pattern("DIAGONAL"));
        manager.addPattern(new Pattern("CUSTOM"));

        // Mark full card
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                card.addValue(i, j, "XX");
            }
        }

        // Unmark 2nd row, 2nd column
        card.addValue(1, 1, "I7");

        assertEquals(11, manager.countBingos(card));
    }

    /**
     * BM9: Full card marked except for 3rd row 2nd column
     * Test the countBingos method for a BingoCard with all rows and columns fully
     */
    @Test
    public void testBM9() {
        manager.addPattern(new Pattern("ROW"));
        manager.addPattern(new Pattern("COLUMN"));
        manager.addPattern(new Pattern("DIAGONAL"));
        manager.addPattern(new Pattern("CUSTOM"));

        // Mark full card
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                card.addValue(i, j, "XX");
            }
        }

        // Unmark 3rd row, 2nd column
        card.addValue(2, 1, "I7");

        assertEquals(12, manager.countBingos(card));
    }
}
