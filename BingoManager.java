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

    /**
     * Counts the number of bingos in a given BingoCard based on the patterns
     * managed by this BingoManager.
     * 
     * @param card The BingoCard to check for bingos.
     * @return The number of bingos found in the card.
     */
    public int countBingos(BingoCard card) {
        int count = 0;
        for (Pattern pattern : patterns) {
            if (pattern.matches(card) && (pattern.getType().equals("ROW") || pattern.getType().equals("COLUMN"))) {
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
            }
        }
        return count;
    }
}
