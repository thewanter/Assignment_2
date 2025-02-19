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
    private static ArrayList<Integer> alreadySelectedCard = new ArrayList<>();

    /**
     * The main method serves as the entry point for the Bingo game application.
     * It initializes the scanner for user input, loads the Bingo cards from a file,
     * and presents the user with options to play in random or manual mode.
     * It handles user input, ensures valid choices, and directs the gameplay accordingly
     * @param args command line arguments (not used in this application)
     */
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
                //For testing purpose
                if (choice == 0) {
                    System.out.println("Exiting the program...");
                    break;
                }
            }
            catch (Exception e) {
                System.out.println("Invalid input, Please enter a number(1 or 2):");
                // scnr.next(); //Clear valid input
            }
        }
    }
    /**
     * Random play function that receive a random card from the array list 
     * @param scnr to get input from users
     */
    private static void randomPlay(Scanner scnr) {
        int cardID = random.nextInt(cards.size()); //0-7
        //System.out.println("Select Card: " + (cardID + 1));
        //Update the cardID value to match with the order of the list
        BingoCard selectedCard = cards.get(cardID);
        alreadySelectedCard.add(cardID); //Integer storing val (0-7)
        BingoCaller callObject = new BingoCaller();
        boolean endRandomMode = false;

        int newCardID = 0;
        while(!endRandomMode) {
                do {
                    Random randCardID = new Random();
                    newCardID = randCardID.nextInt(cards.size()); //0-7
                }
                while(alreadySelectedCard.contains(newCardID));
                //System.out.println("Your current card is: " + (newCardID+1));
                //Get a new card
                selectedCard = cards.get(newCardID);
                //selectedCard.display();
                //Store this card id into alreadySelectedCard
                alreadySelectedCard.add(newCardID);
                System.out.println("Select Card: " + (newCardID + 1));
                //shuffle the list to make sure there is no duplicated combination
                callObject.listChanging();
                endRandomMode = randomGameplay(selectedCard, true, callObject);
        }
    }

    /**
     * This method allows the user to manually play the Bingo game. It displays the available cards and prompts the user to pick a card by entering its ID.
     * It ensures that the user input is an integer within the valid range (1-8). If the input is invalid, it prompts the user to try again.
     * @param scnr the Scanner object to receive user input
     */
    private static void manualPlay(Scanner scnr) {
        displayCards();
        int cardID = -1;
        boolean validInput = false;

        while (!validInput) {
            System.out.println("Pick the card that you want, enter the id of the card (1-8):");
            try {
                cardID = scnr.nextInt();
                if (cardID >= 1 && cardID <= 8) {
                    validInput = true;
                } else {
                    System.out.println("Invalid input. Please enter a number between 1 and 8.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer.");
                scnr.next(); // Clear the invalid input
            }
        }
        BingoCard selectedCard = cards.get(cardID - 1);
        manualGameplay(selectedCard, cardID);
    }
     /**
     * Checks if the given subset list is entirely contained within the set list.
     * @param subset The list of integers representing the subset.
     * @param set The list of integers representing the full set.
     * @return true if all elements of the subset are present in the set, otherwise false.
     * Returns false if the subset size is greater than the set size.
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
     * This method allows the user to play the Bingo game manually.
     * It stores all user inputs in an ArrayList and uses a multidimensional ArrayList 
     * to check if the inputs are correct answers.
     * It adjusts the positions of the matrix based on the user's inputs.
     * User doesn't need to call the number to win the game, they only need to put the right location to win
     * THIS FUNCTION IS MORE FOR TESTING
     * @param card the BingoCard object to be used in the game
     * @param cardID the ID of the card to be used in the game
     */
    public static void manualGameplay(BingoCard card, int cardID) {
        Scanner scnrMan = new Scanner(System.in);
        ArrayList<Integer> storeAllInputs = new ArrayList<>();
        ArrayList<ArrayList<Integer>> checkAllInputs = getBingoResutls(card);

        card.display();
        System.out.println("Enter a string winthin (B,I,N,G,O):");
                System.out.println("Your input should be something like: BB,IB,BO... ");
        System.out.println(">>Program stricly follow formatting rules: row then column for your input! For example: BI\n" 
                        + "with B will be the row and I will be the column<<");
        String usrInput = scnrMan.nextLine();
        while(!usrInput.equals("BINGO")) {
            char firstLetter=usrInput.charAt(0);
            System.out.println(firstLetter);
            char secondLetter=usrInput.charAt(1);
            int row =0 ,col = 0;
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
                    System.out.println("Please choose again for the first letter among (B,I,N,G,O)!");
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
                    System.out.println("Please choose again for the first letter among (B,I,N,G,O)!");
                    break;
            }
            if(card.getValue(row, col).equals("XX")) {
                System.out.println("You already chose this location");
            }
            else {
                storeAllInputs.add(Integer.parseInt(card.getValue(row, col)));
                card.addValue(row, col, "XX");
            }
            card.display();
            System.out.println("Enter user's input: ");
            usrInput = scnrMan.nextLine();
            
        }
        boolean flag = false;
        for (int i = 0; i<checkAllInputs.size();i ++) {
            
            if(isSubset(checkAllInputs.get(i), storeAllInputs)) {
                flag=true;
                System.out.println(flag);
            }
        }
        if(flag==false) {
            System.out.println("You lose!");
        }
        else {
            System.out.println("You won!");
        }
        
    }

    /**
     * This method allows the user to play the Bingo game. It stores all user inputs in an ArrayList and
     * uses a multidimensional ArrayList to check if the inputs are correct answers.
     * It adjusts the positions of the matrix based on the user's inputs.
     * @param card the BingoCard object to be used in the game
     * @param isRand a boolean indicating whether the gameplay is random
     * @param callObject a BingoCaller object to generate calls during the game
     * @return true if the user wins, false if the user moves to the next card
     */
    private static boolean randomGameplay(BingoCard card, boolean isRand, BingoCaller callObject) {
        Scanner scnr = new Scanner(System.in);
        ArrayList<Integer> storeAllInputs = new ArrayList<>();
        //Assign checkAllInputs with the results 
        ArrayList<ArrayList<Integer>> checkAllInputs = getBingoResutls(card);
        card.display();
        String usrInput="";
        String tempAntiCheatString = callObject.generateCall();
        int antiCheatValue = Integer.parseInt(tempAntiCheatString.substring(1));
        boolean flagForInput = false;
        //System.out.println("TEST" + antiCheatValue);
        while(!flagForInput) {
            try {
                System.out.println("---> Caller says: " + tempAntiCheatString);
                System.out.println("1. Make a \"BINGO\" call\n" + 
                        "2. Type \"CALL\" to move to the next call\n"+
                        "3. Move to the next card by typing \"MOVE\"");
                System.out.println("Enter a string winthin (B,I,N,G,O):");
                System.out.println("Your input should be something like: BB,IB,BO... ");
                System.out.println(">>Program stricly follow formatting rules: row then column for your input! For example: BI\n" 
                        + "with B will be the row and I will be the column<<");
                usrInput = scnr.nextLine().trim();
                
                if(!usrInput.matches("[a-zA-Z]+")) {
                    System.out.println("DEMO" + usrInput);
                    throw new IllegalArgumentException("Invalid input please try again!");
                }
                flagForInput = true;
            }
            catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        String tempUsrInput = usrInput;
        //Break when user calls "BINGO"
        while(!tempUsrInput.toUpperCase().equals("BINGO")) {
            //Move to another card when user believes they can't win by using their provided card
            if (tempUsrInput.toUpperCase().equals("MOVE")) {
                usrInput="";
                System.out.println("||---------------------------||");
                System.out.println("||Move to another random card||");
                System.out.println("||---------------------------||");
                return false;
            }; 

            if (tempUsrInput.toUpperCase().equals("CALL")) {
                tempAntiCheatString = callObject.generateCall();
                System.out.println("---> Caller says again (CALL): " + tempAntiCheatString);
                antiCheatValue = Integer.parseInt(tempAntiCheatString.substring(1));
                //System.out.println("HELLO" + antiCheatValue);
                card.display();
                System.out.println("Enter user's input (BINGO,CALL,MOVE, or specific location): ");
                tempUsrInput = scnr.nextLine().trim();
                continue;
            }; 
            
            char firstLetter=tempUsrInput.toUpperCase().charAt(0);
            //System.out.println(firstLetter);
            char secondLetter=tempUsrInput.toUpperCase().charAt(1);
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
                    System.out.println("Please choose again for the first letter among (B,I,N,G,O)!");
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
                    System.out.println("Please choose again for the second letter among (B,I,N,G,O)!");
                    break;
            }
        
            if(card.getValue(row, col).equals("XX")) {
                System.out.println("You already chose this location");
            }
            else {
                int convertToIntVal = Integer.parseInt(card.getValue(row, col));
                if(convertToIntVal != antiCheatValue) {
                    card.display();
                    System.out.println("Value doesn't match current call: " + tempAntiCheatString + "! You can \"CALL\" again or \"MOVE\": ");
                    tempUsrInput = scnr.nextLine();
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
            tempUsrInput = scnr.nextLine();
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
            return true;
        }
        else {
            System.out.println("You won!");
            return true;
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
