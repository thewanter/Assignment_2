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
    
    public static void main(String[] args) {
        System.out.println("Assignment 2: BINGO!");
        loadCards("BingoCards.txt");
        displayCards(); 
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
