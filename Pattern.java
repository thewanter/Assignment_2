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
}
