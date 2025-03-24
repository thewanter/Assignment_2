import java.util.ArrayList;
import java.util.List;

/**
 * @author: Thanh An Vu
 * @description: This class manages the Bingo patterns and counts the number of
 *               bingos in a BingoCard based on the defined patterns.
 */
public class BingoManager {
    private List<Pattern> patterns;

    /**
     * Constructs a BingoManager object.
     */
    public BingoManager() {
        this.patterns = new ArrayList<>();
    }

    /**
     * Adds a pattern to the BingoManager.
     * This method allows you to add a new pattern that can be used to check
     * 
     * @param pattern
     */
    public void addPattern(Pattern pattern) {
        patterns.add(pattern);
    }

    /**
     * Counts the number of bingos in the provided BingoCard based on the
     * patterns added to the BingoManager.
     * 
     * @param card The BingoCard to check for bingos.
     *             The method checks each pattern and counts how many times it
     *             matches
     * @return The total number of bingos found in the BingoCard.
     *         It checks for rows, columns, diagonals, and any custom patterns
     *         defined.
     */
    public int countBingos(BingoCard card) {
        int count = 0;
        for (Pattern pattern : patterns) {
            String type = pattern.getType();
            if (type.equals("ROW")) {
                for (int i = 0; i < 5; i++) {
                    boolean rowComplete = true;
                    for (int j = 0; j < 5; j++) {
                        if (!card.getValue(i, j).equals("XX")) {
                            rowComplete = false;
                            break;
                        }
                    }
                    if (rowComplete) {
                        count++;
                    }
                }
            } else if (type.equals("COLUMN")) {
                for (int k = 0; k < 5; k++) {
                    boolean columnComplete = true;
                    for (int j = 0; j < 5; j++) {
                        if (!card.getValue(j, k).equals("XX")) {
                            columnComplete = false;
                            break;
                        }
                    }
                    if (columnComplete) {
                        count++;
                    }
                }
            } else if (type.equals("DIAGONAL")) {
                boolean lefDiagonalComplete = true;
                boolean rightDiagonalComplete = true;
                for (int i = 0; i < 5; i++) {
                    if (!card.getValue(i, i).equals("XX")) {
                        lefDiagonalComplete = false;
                    }
                    if (!card.getValue(i, 4 - i).equals("XX")) {
                        rightDiagonalComplete = false;
                    }
                }
                if (lefDiagonalComplete) {
                    count++;
                }
                if (rightDiagonalComplete) {
                    count++;
                }

            } else if (type.equals("CUSTOM")) {
                // Implement custom pattern logic here
                boolean topRowComplete = true;
                for (int k = 0; k < 5; k++) {
                    if (!card.getValue(0, k).equals("XX")) {
                        topRowComplete = false;
                        break;
                    }
                }

                boolean middleColumnComplete = true;
                for (int l = 0; l < 5; l++) {
                    if (!card.getValue(l, 2).equals("XX")) {
                        middleColumnComplete = false;
                        break;
                    }
                }

                if (topRowComplete && middleColumnComplete) {
                    count++;
                }
                // check for square patter
                if (checkSquarePattern(card)) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * Checks if the provided BingoCard has a square pattern.
     * A square pattern is defined as having the top and bottom rows with left
     * column and right column fully marked with "XX".
     * 
     * @param card
     * @return
     */
    public boolean checkSquarePattern(BingoCard card) {
        boolean isTopRowFull = true;
        boolean isBottomRowFull = true;
        boolean isLeftColumnFull = true;
        boolean isRightColumnFull = true;
        // Check top and bottom rows
        for (int col = 0; col < 5; col++) {
            if (!card.getValue(0, col).equals("XX")) {
                isTopRowFull = false;
            }
            if (!card.getValue(4, col).equals("XX")) {
                isBottomRowFull = false;
            }
        }
        // Check left and right columns
        for (int row = 0; row < 5; row++) {
            if (!card.getValue(row, 0).equals("XX")) {
                isLeftColumnFull = false;
            }
            if (!card.getValue(row, 4).equals("XX")) {
                isRightColumnFull = false;
            }
        }
        if (isTopRowFull && isBottomRowFull && isLeftColumnFull && isRightColumnFull) {
            return true; // Found a complete square pattern
        }
        return false;
    }
}
