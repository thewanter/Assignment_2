/**
 * @author: Thanh An Vu
 * @version: 1.0
 * Date created: 2/4/2025
 */


import java.util.Scanner;
import java.io.*;
import java.util.*;
import java.util.ArrayList;

public class Bingo {
    private static final String WIN ="BINGO";
    private static final Random random = new Random();
    private static ArrayList<BingoCard> cards = new ArrayList<>();
    private static Set<String> calledNumbers = new HashSet<>();
    private int target;
    private int lines;
    public static void main(String[] args) {
        System.out.println("Assignment 2: BINGO!");
        loadCards("BingoCards.txt"); 
        while (true) {
            int choice;
            Scanner scnr = new Scanner(System.in);
            System.out.println("Choose game mode:(1) Random Play (2) Manual Play");
            
            switch (choice) {
                case 1: 
                    randomPlay(scnr);
                    break;
                case 0:
                    manualPlay(scnr);   
                    break;
                default:
                    System.out.println("Choose again, value must be a number from 0-1");
                    continue;
            }

           
        }
        

    }
    //Random Play
    private static void randomPlay(Scanner scnr) {


    }

    private static void manualPlay(Scanner scnr) {


    }
    //Load cards function
    private static void loadCards(String filename) {
        try (BufferedReader readFile = new BufferedReader(new FileReader(filename))) {
            String line;
            BingoCard card = null;
            while((line = readFile.readLine()) != null) {
                if(line.startsWith("Card")) {
                    if (card != null) cards.add(card);
                    //Instance card object from the BingoCard class
                    card = new BingoCard();

                } else if (card != null) {
                    
                }
            }
            if (card != null) cards.add(card);
        }
        catch (IOException e) {
            System.out.println("File not found, try again: " + e.getMessage());
        }
        }

    }
}
