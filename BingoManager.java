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
            }
        }
        return count;
    }
}
