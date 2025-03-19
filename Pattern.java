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
     * @param card The BingoCard to check against the pattern.
     * @return correct functionality of the pattern
     * @throws IllegalArgumentException if the pattern type is invalid.
     */
    public boolean matches(BingoCard card) {
        switch (type) {
            case "ROW":
               // return checkRows(card);
            case "COLUMN":
                // return checkColumns(card);
            case "DIAGONAL":
                // return checkDiagonals(card);
            case "CUSTOM":
                // return checkCustom(card);
            default:
                throw new IllegalArgumentException("Invalid pattern type: " + type);

        }

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

        return false; // Placeholder
    }
}
