/**
 * Bingo class that loads the cards 
 * @author: Thanh An Vu
 * @version: 1.0
 * Date created: 2/4/2025
 */
import java.io.*;
import java.util.*;

public class Bingo {
    private static final String WIN ="BINGO";
    private static final Random random = new Random();
    private static ArrayList<BingoCard> cards = new ArrayList<>();
    //private final String[][] matrix = new String[5][5];

    
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        System.out.println("Assignment 2: BINGO!");
        loadCards("BingoCards.txt");
        //displayCards(); 
        randomPlay(scnr);
        // while (true) {
        //     int choice;
        //     Scanner scnr = new Scanner(System.in);
            
        //     System.out.println("Choose game mode:(1) Random Play (2) Manual Play");
            
        //     switch (choice=scnr.nextInt()) {
        //         case 1: 
        //             randomPlay(scnr);
        //             break;
        //         case 0:
        //             manualPlay(scnr);   
        //             break;
        //         default:
        //             System.out.println("Choose again, value must be a number from 0-1");
        //             continue;
        //     }
        // }
        

    }
    //Random Play
    private static void randomPlay(Scanner scnr) {
        BingoCard selectedCard = cards.get(random.nextInt(cards.size()));
        playGame(scnr, selectedCard, true);

    }
    //Manual Play
    private static void manualPlay(Scanner scnr) {

    }
    
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
    
    private static void playGame(Scanner scnr, BingoCard card, boolean isRand) {
        ArrayList<Integer> storeAllInputs = new ArrayList<>();
        ArrayList<ArrayList<Integer>> checkAllInputs = getBingoResutls(card);

        card.display();
        System.out.println("Enter user's input: ");
        String usrInput = scnr.nextLine();
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
                    System.out.println("haha");
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
                    System.out.println("haha");
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
            usrInput = scnr.nextLine();
            
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

    public static ArrayList<ArrayList<Integer>> getBingoResutls(BingoCard selectedCard) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        for(int i=0; i<5; i++) {
            ArrayList<Integer> getColumns = new ArrayList<>();
            for(int j=0; j<5;j++) {
                getColumns.add(Integer.parseInt(selectedCard.getValue(i,j)));
            }
            result.add(getColumns);

        }

        for(int i=0; i<5; i++) {
            ArrayList<Integer> getRows = new ArrayList<>();
            for(int j=0; j<5;j++) {
                getRows.add(Integer.parseInt(selectedCard.getValue(j,i)));
            }
            result.add(getRows);

        }
        //First Diagonal
        ArrayList<Integer> getFirstDiagonal = new ArrayList<>();
        ArrayList<Integer> getSecondDiagonal = new ArrayList<>();

        for(int i=0; i < 5;i++){
            getFirstDiagonal.add(Integer.parseInt(selectedCard.getValue(i,i)));
        }
    
        result.add(getFirstDiagonal);
        //Second Diagonal
        for(int j=4; j >= 0;j--) {
            int i = 0;
            getSecondDiagonal.add(Integer.parseInt(selectedCard.getValue(j,i)));
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
