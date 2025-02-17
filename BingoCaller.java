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

    public BingoCaller() {
        this.calledCombinations = new ArrayList<>();
    }

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
}
