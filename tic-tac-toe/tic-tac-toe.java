import java.util.InputMismatchException;
import java.util.Scanner;

public class TicTacToe {

    // Board size constant
    private static final int SIZE = 3;

    // Game board
    private static char[][] board = {
            {' ', ' ', ' '},
            {' ', ' ', ' '},
            {' ', ' ', ' '}
    };

    public static void main(String[] args) {

        System.out.println("Welcome to 3x3 Tic Tac Toe!");

        boolean gameOver = false;
        char currentPlayer = 'X';

        Scanner scanner = new Scanner(System.in);

        printBoard();

        while (!gameOver) {

            System.out.println("Player " + currentPlayer + ", enter row and column (0-2):");

            int row;
            int column;

            // Handle invalid input like characters
            try {
                row = scanner.nextInt();
                column = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter numbers only.");
                scanner.nextLine(); // Clear buffer
                continue;
            }

            // Validate board position
            if (!isValidPosition(row, column)) {
                System.out.println("Invalid position! Please enter values between 0 and 2.");
                continue;
            }

            // Check if the cell is already filled
            if (board[row][column] != ' ') {
                System.out.println("This cell is already occupied. Choose another.");
                continue;
            }

            // Place player symbol
            board[row][column] = currentPlayer;

            printBoard();

            // Check win condition
            if (hasPlayerWon(currentPlayer)) {
                System.out.println("Congratulations! Player " + currentPlayer + " wins!");
                gameOver = true;
            }

            // Check draw condition
            else if (isDraw()) {
                System.out.println("The game is a draw!");
                gameOver = true;
            }

            // Switch player
            else {
                currentPlayer = switchPlayer(currentPlayer);
            }
        }

        scanner.close();
    }

    /**
     * Checks if the given board position is valid.
     */
    private static boolean isValidPosition(int row, int column) {
        return row >= 0 && row < SIZE && column >= 0 && column < SIZE;
    }

    /**
     * Switches the current player.
     */
    private static char switchPlayer(char player) {
        return (player == 'X') ? 'O' : 'X';
    }

    /**
     * Checks if the board is completely filled.
     */
    private static boolean isDraw() {

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {

                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Checks if the given player has won.
     */
    private static boolean hasPlayerWon(char player) {

        // Check rows
        for (int row = 0; row < SIZE; row++) {
            if (board[row][0] == player &&
                board[row][1] == player &&
                board[row][2] == player) {
                return true;
            }
        }

        // Check columns
        for (int column = 0; column < SIZE; column++) {
            if (board[0][column] == player &&
                board[1][column] == player &&
                board[2][column] == player) {
                return true;
            }
        }

        // Check main diagonal
        if (board[0][0] == player &&
            board[1][1] == player &&
            board[2][2] == player) {
            return true;
        }

        // Check secondary diagonal
        if (board[0][2] == player &&
            board[1][1] == player &&
            board[2][0] == player) {
            return true;
        }

        return false;
    }

    /**
     * Prints the game board.
     */
    private static void printBoard() {

        System.out.println("|---|---|---|");

        for (int i = 0; i < SIZE; i++) {

            System.out.print("| ");

            for (int j = 0; j < SIZE; j++) {
                System.out.print(board[i][j] + " | ");
            }

            System.out.println();
            System.out.println("|---|---|---|");
        }
    }
}
