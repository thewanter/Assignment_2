import java.util.List;

public class Pattern {
    private String type; // Type of the pattern (e.g., "ROW", "COLUMN", "DIAGONAL", "CUSTOM")
    private List<String> pattern;

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
}
