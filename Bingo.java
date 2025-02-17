/**
 * Bingo class that loads the cards 
 * @author: Thanh An Vu
 * @version: 1.0
 * Date created: 2/4/2025
 */
import java.io.*;
import java.util.*;

public class Bingo {
    private static final Random random = new Random();
    private static ArrayList<BingoCard> cards = new ArrayList<>();
    
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        System.out.println("Assignment 2: BINGO!");
        loadCards("BingoCards.txt");
        //displayCards(); 
        
        while (true) {
            System.out.println("Welcome to UW-STOUT BINGO!" + "\n" +
                            "Enter (1) for Random Play" + "\n" +
                            "Enter (2) Manual Play\n");
            try {
                int choice = scnr.nextInt();
                if(choice == 1) {
                    System.out.println("Enjoy the random gameplay!");
                    randomPlay(scnr);
                }
                if(choice == 2) {
                    System.out.println("Enjoy the manual mode!");
                    manualPlay(scnr);
                }
            }
            catch (Exception e) {
                System.out.println("Invalid input, Please enter a number(1 or 2):");
                scnr.next(); //Clear valid input
            }
        }
        

    }
    /**
     * Random play function that receive a random card from the array list 
     * @param scnr to get input from users
     */
    private static void randomPlay(Scanner scnr) {
        int cardID = random.nextInt(cards.size());
        System.out.println("Select Card: " + cardID);
        BingoCard selectedCard = cards.get(cardID);
        BingoCaller callObject = new BingoCaller();
        playGame(scnr, selectedCard, true, callObject);

    }
    //Manual Play
    private static void manualPlay(Scanner scnr) {
        displayCards();
        System.out.println("Pick the card that you want, enter the id of the card (1-9):");
        int cardID = scnr.nextInt();
        BingoCard selectedCard = cards.get(cardID);
        System.out.println("That's it");
    }
    
    /**
     * Function to check if the array list storing the users input is inside the a\
     * the array list storing a set of correct answers. 
     * @param subset
     * @param set
     * @return false if the subset size is greater than set size
     * @return 
     */
    public static boolean isSubset(ArrayList<Integer> subset, ArrayList<Integer> set) {
        if (subset.size() > set.size()) {
            return false;
        }
        for (int i = 0; i < subset.size(); i++) {
            boolean Nflag = false;
            for(int j = 0; j < set.size(); j++) {
                if(subset.get(i) == set.get(j)) {
                    Nflag=true;
                }
            }
            if(Nflag==false) {
                return false;
            }
        }
        return true;
    }
    /**
     * Loads Bingo cards from a specified file.
     * Reads the file line by line, identifies card headers, and fills card matrix.
     * Skips empty lines and ensures each card has exactly 5 rows.
     * @param filename The name of the file containing Bingo card data.
     * @return void
     */
    private static void loadCards(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
        String line;
        BingoCard card = null;
        int rowCount = 0;

        while ((line = reader.readLine()) != null) {
            line = line.trim();
            if (line.isEmpty()) continue; // Skip empty lines

            if (line.startsWith("Card")) {
                if (card != null) cards.add(card);
                card = new BingoCard(line.trim());
                rowCount = 0;  // Reset row count for new card
            } else if (card != null && rowCount < 5) {
                card.addRow(line, rowCount);
                rowCount++;  // Move to next row index
            }
        }
        if (card != null) cards.add(card);
        }
        catch (IOException e) {
            System.out.println("File not found, try again: " + e.getMessage());
        }
    }
    
    /**
     * Function allow user to play the game
     * Store all user's inputs in an ArrayList and 
     * Use a multidimentional Array List to check the values inside if they're correct answers 
     * Adjust the positions of the matrix based on user's inputs
     * @param scnr,card,isRand: Scanner to get user's input, card from BingoCard
     * @return void
     */
    private static void playGame(Scanner scnr, BingoCard card, boolean isRand, BingoCaller callObject) {
        ArrayList<Integer> storeAllInputs = new ArrayList<>();
        //Assign checkAllInputs with the results 
        ArrayList<ArrayList<Integer>> checkAllInputs = getBingoResutls(card);
        card.display();
        String usrInput;
        String tempAntiCheatString = callObject.generateCall();
        int antiCheatValue = Integer.parseInt(tempAntiCheatString.substring(1, 3));
        System.out.println("TEST" + antiCheatValue);
        while(true) {
                try {
                    System.out.println("---> Caller says: " + tempAntiCheatString);
                    System.out.println("1. Make a \"BINGO\" call\n" + 
                            "2. Type \"NONE\" to move to the next call\n"+
                            "3. Move to the next card by typing \\\"NOTWINABLE\\\"");
                    System.out.println("Enter a string winthin (B,I,N,G,O):");
                    System.out.println("Your input should be something like: BB,IB,BO...");
                    usrInput = scnr.nextLine().trim();

                    if(!usrInput.matches("[a-zA-Z]+")) {
                        throw new IllegalArgumentException("Invalid input please try again!");
                    };
                    break;
                }
                catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
        }
        
        //Break when user calls "BINGO"
        while(!usrInput.equals("BINGO") && !usrInput.equals("NOTWINABLE")) {
            if (usrInput.equals("NONE")) {
                tempAntiCheatString = callObject.generateCall();
                System.out.println("---> Caller says again: " + tempAntiCheatString);
                antiCheatValue = Integer.parseInt(tempAntiCheatString.substring(1));
                System.out.println("HELLO" + antiCheatValue);
                card.display();
                System.out.println("Enter user's input: ");
                usrInput = scnr.nextLine();
                continue;
            }; 
            //Move to another card when user believes they can't win by using their provided card
            //Also delete the provided card in the array list so they can't pick it again
            if (usrInput.equals("NOTWINABLE")) {
                System.out.println("----Move to another random card----");
                continue;
            }; 
            char firstLetter=usrInput.toUpperCase().charAt(0);
            //System.out.println(firstLetter);
            char secondLetter=usrInput.toUpperCase().charAt(1);
            int row =-1,col = -1;
            switch(firstLetter){
                case 'B':
                    row = 0;
                    break;
                case 'I':
                    row = 1;
                    break;
                case 'N':
                    row = 2;
                    break;
                case 'G':
                    row = 3;
                    break;
                case 'O':
                    row = 4;
                    break;
                default:
                    System.out.println("Please choose again among (B,I,N,G,O)!");
                    break;
            }

            switch(secondLetter){
                case 'B':
                    col = 0;
                    break;
                case 'I':
                    col = 1;
                    break;
                case 'N':
                    col = 2;
                    break;
                case 'G':
                    col = 3;
                    break;
                case 'O':
                    col = 4;
                    break;
                default:
                    System.out.println("Please choose again among (B,I,N,G,O)!");
                    break;
            }
            //Check if default value was not changed
            if(row < 0 || col < 0) {
                card.display();
                System.out.println("Enter user's input: ");
                usrInput = scnr.nextLine();
                continue;
            }
            if(card.getValue(row, col).equals("XX")) {
                System.out.println("You already chose this location");
            }
            else {
                int convertToIntVal = Integer.parseInt(card.getValue(row, col));
                if(convertToIntVal != antiCheatValue) {
                    card.display();
                    System.out.println("Value doesn't match current call: " + tempAntiCheatString + "! Now please enter again: ");
                    usrInput = scnr.nextLine();
                    continue;
                }
                storeAllInputs.add(convertToIntVal);
                card.addValue(row, col, "XX");
                
            }
            //Call here again
            tempAntiCheatString = callObject.generateCall();
            System.out.println("---> Caller says again: " + tempAntiCheatString);
            antiCheatValue = Integer.parseInt(tempAntiCheatString.substring(1));
            card.display();
            System.out.println("Enter user's input: ");
            usrInput = scnr.nextLine();
            
        }
        /*A flag to check if all the inputs stored in the storeAllInputs lists 
        are the subset of the checkAllInputs*/
        boolean flag = false;
        for (int i = 0; i<checkAllInputs.size();i ++) {
            if(isSubset(checkAllInputs.get(i), storeAllInputs)) {
                flag=true;
                //System.out.println(flag);
            }
        }
        if(flag==false) {
            System.out.println("You lose!");
            //Move to the next card
            //randomPlay(scnr);
        }
        else {
            System.out.println("You won!");
        }
    }

    /**
     * Function that use a multidimentional ArrayLists to check to final results
     * There are 12 cases to win a Bingo game so we will need to store all the cases in separated lists
     * @param selectedCard
     * @return ArrayList<ArrayList<Integer>> 
     */
    public static ArrayList<ArrayList<Integer>> getBingoResutls(BingoCard selectedCard) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        //Store all values of the columns into an getColumns List
        for(int cI=0; cI<5; cI++) {
            ArrayList<Integer> getColumns = new ArrayList<>();
            for(int cJ=0; cJ<5; cJ++) {
                getColumns.add(Integer.parseInt(selectedCard.getValue(cI,cJ)));
            }
            result.add(getColumns);
        }

        //Store all values of the rows into an getRows List
        for(int rI=0; rI<5; rI++) {
            ArrayList<Integer> getRows = new ArrayList<>();
            for(int rJ=0; rJ<5; rJ++) {
                getRows.add(Integer.parseInt(selectedCard.getValue(rJ,rI)));
            }
            result.add(getRows);

        }
        //Store all values of the first diagonal into getFirstDiagonal list
        ArrayList<Integer> getFirstDiagonal = new ArrayList<>();
        for(int fDiaI=0; fDiaI < 5;fDiaI++){
            getFirstDiagonal.add(Integer.parseInt(selectedCard.getValue(fDiaI,fDiaI)));
        }
        result.add(getFirstDiagonal);

        //Store all values of the second diagonal into getSecondDiagonal list
        ArrayList<Integer> getSecondDiagonal = new ArrayList<>();
        for(int sDiaJ=4; sDiaJ >= 0;sDiaJ--) {
            int i = 0;
            getSecondDiagonal.add(Integer.parseInt(selectedCard.getValue(sDiaJ,i)));
            i++;
        }
        result.add(getSecondDiagonal);

        return result;
    } 

    /**
     * Displays all loaded Bingo cards.
     * Iterates through the list of Bingo cards and calls their display method.
     */ 
   private static void displayCards() {
        for (BingoCard card : cards) {
            card.display();
        } 
   }
}
