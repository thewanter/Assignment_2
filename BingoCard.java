/**
 * @author: Thanh An Vu
 * @version: 1.3
 * Date created: 2/4/2025
 */
public class BingoCard {
    private final String id;
    private final String[][] matrix = new String[5][5];
    private static final String BINGO = "BINGO";

    public BingoCard(String id) {
        this.id = id;
    }

    /**
     * Get the id of selected card
     * @return id of selected card
    */
    public String getID() {
        return id;
    }
    
    /**
     * Adds a row of numbers to the Bingo card at the specified index.
     * Trims spaces, validates the row format, and ensures it contains exactly 5 numbers.
     * Skips empty rows and prevents exceeding the 5x5 matrix size.
     * @param row A comma-separated string of 5 numbers representing a row in the Bingo card.
     * @param rowIndex The index at which the row should be inserted (0-4).
     * @return void
     */
    public void addRow(String row, int rowIndex) {
        row = row.trim();
        if (row.isEmpty()) {
            System.out.println("Skipping empty row...");
            return;
        }
    
        String[] numbers = row.split(",");
        if (numbers.length != 5) {
            System.out.println("Error in row: '" + row + "' (Expected 5 values, got " + numbers.length + ")");
            throw new IllegalArgumentException("Invalid row format: " + row);
        }
    
        // Ensure rowIndex does not exceed grid size
        if (rowIndex < 5) {
            for (int i = 0; i < 5; i++) {
                matrix[rowIndex][i] = numbers[i].trim();
            }
        } else {
            System.out.println("Too many rows for this Bingo card: " + id);
        }
    }
    

    /**
     * Add value at the current position
     * @param row the current row of the matrix
     * @param col the current column of the matrix
     * @param value value that you want to add
     * @return matrix[row][col] value at the current position
     */
    public void addValue(int row, int col, String value) {
        matrix[row][col] = value;
    }

    /**
     * Get value at the current position
     * @param row the current row of the matrix
     * @param col the current column of the matrix
     * @return matrix[row][col] value at the current position
     */
    public String getValue(int row, int col) {
        return matrix[row][col];
    }


    /**
     * Display the matrix
     * @return void
     */
    public void display() {
        System.out.println("\n" + id);
        System.out.println("-------------------------------------------");
        System.out.println("\tB\tI\tN\tG\tO");
        System.out.println("-------------------------------------------");
        for (int i = 0; i < 5; i++) {
            //Display the each letter in the "BINGO" word seperately and vertically
            System.out.print(BINGO.charAt(i) + "\t");
            for (int j = 0; j < 5; j++) {
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println();
        }
    }
}
