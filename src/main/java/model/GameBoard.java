package model;

public class GameBoard {
    public static final int ROWS = 6;
    public static final int COLUMNS = 7;
    private char[][] grid;

    public GameBoard() {
        grid = new char[ROWS][COLUMNS];
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS; col++) {
                grid[row][col] = '.';
            }
        }
    }


    public boolean placePiece(int column, char symbol) {
        for (int row = ROWS - 1; row >= 0; row--) {
            if (grid[row][column] == '.') {
                grid[row][column] = symbol;
                return true;
            }
        }
        return false;
    }

    public boolean checkWin(char symbol) {
        // Check horizontal, vertical, and diagonal wins
        // Horizontal
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col <= COLUMNS - 4; col++) {
                if (grid[row][col] == symbol &&
                        grid[row][col + 1] == symbol &&
                        grid[row][col + 2] == symbol &&
                        grid[row][col + 3] == symbol) {
                    return true;
                }
            }
        }

        // Vertical
        for (int col = 0; col < COLUMNS; col++) {
            for (int row = 0; row <= ROWS - 4; row++) {
                if (grid[row][col] == symbol &&
                        grid[row + 1][col] == symbol &&
                        grid[row + 2][col] == symbol &&
                        grid[row + 3][col] == symbol) {
                    return true;
                }
            }
        }

        // Diagonal (bottom-left to top-right)
        for (int row = 0; row <= ROWS - 4; row++) {
            for (int col = 0; col <= COLUMNS - 4; col++) {
                if (grid[row][col] == symbol &&
                        grid[row + 1][col + 1] == symbol &&
                        grid[row + 2][col + 2] == symbol &&
                        grid[row + 3][col + 3] == symbol) {
                    return true;
                }
            }
        }

        // Diagonal (top-left to bottom-right)
        for (int row = 3; row < ROWS; row++) {
            for (int col = 0; col <= COLUMNS - 4; col++) {
                if (grid[row][col] == symbol &&
                        grid[row - 1][col + 1] == symbol &&
                        grid[row - 2][col + 2] == symbol &&
                        grid[row - 3][col + 3] == symbol) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean isFull() {
        for (int col = 0; col < COLUMNS; col++) {
            if (grid[0][col] == '.') {
                return false;
            }
        }
        return true;
    }

    public void display() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS; col++) {
                System.out.print(grid[row][col] + " ");
            }
            System.out.println();
        }
        for (int col = 0; col < COLUMNS; col++) {
            System.out.print(col + " ");
        }
        System.out.println();
    }
}
