import java.util.List;

public class Pattern {
    private String type; // Type of the pattern (e.g., "ROW", "COLUMN", "DIAGONAL", "CUSTOM")
    private List<int[]> pattern;

    /**
     * Constructs a Pattern object with the specified pattern type.
     * The type is automatically converted to uppercase for consistency.
     * 
     * @param type The type of pattern (e.g., "ROW", "COLUMN", "DIAGONAL",
     *             "CUSTOM").
     */
    public Pattern(String type) {
        this.type = type.toUpperCase();
    }

    public String getType() {
        return type;
    }

    /**
     * Constructs a Pattern object with a custom pattern.
     * The type is set to "CUSTOM" and the provided pattern is used.
     * 
     * @param pattern A list of int arrays representing the custom pattern.
     */
    public Pattern(List<int[]> pattern) {
        this.type = "CUSTOM";
        this.pattern = pattern;
    }

    /**
     * Checks if the provided BingoCard matches the specified pattern.
     * The method determines the type of pattern and calls the appropriate
     * checking method.
     * 
     * @param card The BingoCard to check against the pattern.
     * @return correct functionality of the pattern
     * @throws IllegalArgumentException if the pattern type is invalid.
     */
    public boolean matches(BingoCard card) {
        switch (type) {
            case "ROW":
                return checkRows(card);
            case "COLUMN":
                return checkColumns(card);
            case "DIAGONAL":
                return checkDiagonals(card);
            case "CUSTOM":
                return checkCustom(card);
            default:
                throw new IllegalArgumentException("Invalid pattern type: " + type);
        }
    }

    /**
     * Checks if any row in the provided BingoCard matches the pattern.
     * 
     * @param card The BingoCard to check against the pattern.
     * @return true if any row matches the pattern, false otherwise.
     */
    private boolean checkRows(BingoCard card) {
        // Implement logic to check if any row in the BingoCard matches the pattern
        for (int i = 0; i < 5; i++) {
            boolean rowComplete = true;
            for (int j = 0; j < 5; j++) {
                if (!card.getValue(i, j).equals("XX")) {
                    rowComplete = false;
                    break;
                }
            }
            if (rowComplete) {
                return true; // Found a complete row
            }
        }

        return false;
    }

    /**
     * Checks if any column in the provided BingoCard matches the pattern.
     * 
     * @param card The BingoCard to check against the pattern.
     * @return true if any column matches the pattern, false otherwise.
     */
    private boolean checkColumns(BingoCard card) {
        // Implement logic to check if any column in the BingoCard matches the pattern
        for (int j = 0; j < 5; j++) {
            boolean columnComplete = true;
            for (int i = 0; i < 5; i++) {
                if (!card.getValue(i, j).equals("XX")) {
                    columnComplete = false;
                    break;
                }
            }
            if (columnComplete) {
                return true; // Found a complete column
            }
        }
        return false;
    }

    /**
     * Checks if any diagonal in the provided BingoCard matches the pattern.
     * 
     * @param card The BingoCard to check against the pattern.
     * @return leftDiagonalComplete or rightDiagonalComplete
     *         if any diagonal matches the pattern, false otherwise.
     */
    private boolean checkDiagonals(BingoCard card) {
        // Implement logic to check if any diagonal in the BingoCard matches the pattern
        boolean leftDiagonalComplete = true;
        boolean rightDiagonalComplete = true;

        for (int i = 0; i < 5; i++) {
            if (!card.getValue(i, i).equals("XX")) {
                leftDiagonalComplete = false;
            }
            if (!card.getValue(i, 4 - i).equals("XX")) {
                rightDiagonalComplete = false;
            }
        }

        return leftDiagonalComplete || rightDiagonalComplete;
    }

    /**
     * Checks if the provided BingoCard has a T custom pattern.
     * 
     * @param card The BingoCard to check against the custom pattern.
     * @return true if the BingoCard matches the custom pattern, false otherwise.
     */
    private boolean checkCustom(BingoCard card) {
        boolean topT = true;
        boolean bottomT = true;
        for (int i = 0; i < 5; i++) {
            if (!card.getValue(0, i).equals("XX")) {
                topT = false;
            }
        }

        for (int j = 0; j < 5; j++) {
            if (!card.getValue(4, j).equals("XX")) {
                bottomT = false; // Bottom row is not fully marked
            }
        }

        if (!topT && !bottomT) {
            return false; // Neither top nor bottom row is fully marked
        }
        // Check if the middle column is fully marked
        boolean columnMarked = isAnyColumnFullyMarked(card);
        if (!columnMarked) {
            return false;
        }

        // Allow the bottom right corner to be unmarked
        return true; // All cells in the custom pattern match
    }

    /**
     * Checks if any column in the provided BingoCard is fully marked with "XX".
     * 
     * @param card The BingoCard to check against the pattern.
     * @return true if any column is fully marked, false otherwise.
     */
    private boolean isAnyColumnFullyMarked(BingoCard card) {
        // Iterate over each column (0 to 4)
        for (int col = 0; col < 5; col++) {
            boolean columnMarked = true;

            // Check if all rows in this column are "XX"
            for (int row = 0; row < 5; row++) {
                if (!card.getValue(row, col).equals("XX")) {
                    columnMarked = false; // If any cell is not marked, the column is not fully marked
                    break;
                }
            }

            // If we found a fully marked column, return true
            if (columnMarked) {
                return true;
            }
        }
        return false; // No fully marked columns found
    }
}
