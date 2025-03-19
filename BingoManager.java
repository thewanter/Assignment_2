import java.util.ArrayList;
import java.util.List;

public class BingoManager {
    private List<Pattern> patterns;

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
}
