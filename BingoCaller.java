/**
 * @author Thanh An Vu
 * Date created: 2/16/2025
 */

import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;
import java.util.List;

public class BingoCaller {
    private static final String[] LETTERS = {"B","I","N","G","O"};
    private List<String> calledCombinations;

    /**
     * Constructor for the BingoCaller class.
     * Initializes the calledCombinations list as an empty ArrayList.
     */
    public BingoCaller() {
        this.calledCombinations = new ArrayList<>();
    }

    /**
     * Generates a unique Bingo call consisting of a letter (B, I, N, G, or O) and a number
     * within the appropriate range. The function ensures that no duplicate call is made 
     * until all 75 possible combinations have been used, at which point the list resets.
     * The letter-number mapping is as follows:
     * - 'B': 1-15
     * - 'I': 16-30
     * - 'N': 31-45
     * - 'G': 46-60
     * - 'O': 61-75
     * @return A unique Bingo call in the format "LetterNumber" (e.g., "B12", "G50").
     */
    public String generateCall() {
        Random rand = new Random();
        while (true) {
            int letterIndex = rand.nextInt(LETTERS.length);
            String letter = LETTERS[letterIndex];
            char getLetter = letter.charAt(0);
            int number = -1;
            switch(getLetter) {
                case 'B':
                    number = rand.nextInt(15) + 1;
                    break;
                case 'I':
                    number = rand.nextInt(15) + 16;
                    break;
                case 'N':
                    number = rand.nextInt(15) + 31;
                    break;
                case 'G':
                    number = rand.nextInt(15) + 46;
                    break;
                case 'O':
                    number = rand.nextInt(15) + 61;
                    break;
                default:
                    break;
            }
            String call = letter + number;
            //No duplication for the combination
            if(!calledCombinations.contains(call)) {
                calledCombinations.add(call);
                return call;
            }
            //If all combinations are called, shuffle the list and reset
            //Each letter has 15 unique numbers, and there are 5 letters
            if(calledCombinations.size() == 75) {
                Collections.shuffle(calledCombinations);
                System.out.println("Reset the list");
                calledCombinations.clear();
            }
        }
    }

    /**
     * This method shuffles the elements in the list calledCombinations to change their order randomly.
     * It uses the Collections.shuffle() method to perform the shuffling items in the collections.
     */
    public void listChanging() {
        Collections.shuffle(calledCombinations);    
    }

    
}
