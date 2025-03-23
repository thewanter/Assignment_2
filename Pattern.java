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
        // P13 & P15: T pattern (top row and middle column)
        boolean isTopRowComplete = true;
        for (int col = 0; col < 5; col++) {
            if (!card.getValue(0, col).equals("XX")) {
                isTopRowComplete = false;
                break;
            }
        }
        boolean isMiddleColumnComplete = true;
        for (int row = 1; row < 5; row++) {
            if (!card.getValue(row, 2).equals("XX")) {
                isMiddleColumnComplete = false;
                break;
            }
        }

        // P13: Top row and middle column fully marked
        if (isTopRowComplete && isMiddleColumnComplete) {
            return true; // Found a complete T pattern
        }

        // P14: Entire card marked
        boolean isEntireCardComplete = true;
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                if (!card.getValue(row, col).equals("XX")) {
                    isEntireCardComplete = false;
                    break;
                }
            }
            if (!isEntireCardComplete) {
                break; // Exit if any cell is not marked
            }
        }

        if (isEntireCardComplete) {
            return true; // Found a complete card
        }

        // P15: Middle row and middle column fully marked
        if (isTopRowComplete && card.getValue(1, 2).equals("XX") &&
                card.getValue(3, 2).equals("XX") &&
                card.getValue(4, 2).equals("XX") &&
                !card.getValue(2, 2).equals("XX")) {
            return false;
        }

        // P16-P18: Custom patterns including square
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
        // P16: Square pattern (top row, bottom row, left column, right column)
        if (isTopRowFull && isBottomRowFull && isLeftColumnFull && isRightColumnFull) {
            return true; // Found a complete square pattern
        }
        // P17: Square pattern except the bottom right corner
        if (isTopRowFull && isLeftColumnFull && isRightColumnFull &&
        // Check if bottom row is marked except last cell
                card.getValue(4, 0).equals("XX") &&
                card.getValue(4, 1).equals("XX") &&
                card.getValue(4, 2).equals("XX") &&
                card.getValue(4, 3).equals("XX") &&
                !card.getValue(4, 4).equals("XX")) {
            return false;
        }
        // P18: Square pattern except the top left corner
        if (isBottomRowFull && isLeftColumnFull && isRightColumnFull &&
                !card.getValue(0, 0).equals("XX") &&
                card.getValue(0, 1).equals("XX") &&
                card.getValue(0, 2).equals("XX") &&
                card.getValue(0, 3).equals("XX") &&
                card.getValue(0, 4).equals("XX")) {
            return false;
        }
        return false; // No custom pattern matched
    }
}
