package model;

import database.Database;
import player.Player;

import java.util.Enumeration;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Game {
    private GameBoard board;
    private Player[] players;
    private int currentPlayerIndex;
    private Database database;

    public Game() {
        currentPlayerIndex = 0;
        database = new Database();
        players = new Player[2];
    }

    public void start() {
        System.out.println("Welcome to Connect 4!");
        database.loadFromFile();
        selectName();

        while(true) {

            int menu = showMenu();

            if (menu == 1){
                int gm = selectGameMode();
                if (gm == 1){
                    getGameModeOne();
                } else if (gm == 2) {
                    getGameModeTwo();
                }

            } else if (menu == 2) {
                database.showScores();
                System.out.println();
            } else if (menu == 3) {
                database.writeToFile();
                System.out.println("Saving changes...");
                break;
            } else {
                System.out.println("Invalid input. Try again!");
            }

        }
    }

    private int showMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        System.out.println("----Menu----");
        System.out.println("| 1 - New Game");
        System.out.println("| 2 - Show Score");
        System.out.println("| 3 - Exit");
        System.out.print("Type (1-3): ");
        System.out.println();
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            // Invalid input
        }
        return 0;
    }

    private void selectName(){
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        System.out.print("Player1, select a name: ");
        String POne = scanner.nextLine();
        System.out.print("Player2, select a name: ");
        String PTwo = scanner.nextLine();
        players = new Player[]{new Player(POne,0), new Player(PTwo,0)};
    }

    private int getPlayerMove() {
        Scanner scanner = new Scanner(System.in);
        int column;
        while (true) {
            System.out.print("Enter a column (0-6): ");
            try {
                column = Integer.parseInt(scanner.nextLine());
                if (column >= 0 && column < GameBoard.COLUMNS) {
                    return column;
                }
            } catch (NumberFormatException e) {
                // Invalid input
            }
            System.out.println("Invalid input. Please enter a number between 0 and 6.");
        }
    }

    private int selectGameMode(){
        int number;
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println();
            System.out.println("Please, select a game mode!");
            System.out.println("| 1 - Human vs Human");
            System.out.println("| 2 - Human vs AI");
            System.out.print("Type (1-2): ");
            try {
                number = Integer.parseInt(scanner.nextLine());
                if (number == 1){
                    return 1;
                } else if (number == 2) {
                    return 2;
                } else {
                    System.out.println("Invalid number. Try again!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Try again!");
            }
        }
    }

    private void getGameModeOne(){
        board = new GameBoard();
        System.out.println();
        System.out.println("Game mode 1 selected!");
        System.out.println("Good Luck!");
        board.display();
        while (true) {
            Player currentPlayer = players[currentPlayerIndex];
            System.out.println("Player " + currentPlayer.getName() + "'s turn.");
            int column = getPlayerMove();

            if (board.placePiece(column, currentPlayer.getSymbol())) {
                board.display();
                if (board.checkWin(currentPlayer.getSymbol())) {
                    System.out.println("Player " + currentPlayer.getName() + " wins!");
                    database.updateScoreBoard(players[currentPlayerIndex]);
                    break;
                }
                if (board.isFull()) {
                    System.out.println("It's a draw!");
                    break;
                }
                currentPlayerIndex = (currentPlayerIndex + 1) % players.length;
            } else {
                System.out.println("Column is full or invalid. Try again.");
            }
        }
        currentPlayerIndex = (currentPlayerIndex + 1) % players.length;
    }

    private void getGameModeTwo() {
        board = new GameBoard();
        System.out.println();
        System.out.println("Game mode 2 selected!");
        System.out.println("Good Luck!");
        board.display();
        while (true) {
            Player currentPlayer = players[currentPlayerIndex];
            int column;

            if(currentPlayerIndex == 1){
                System.out.println("Player " + currentPlayer.getName() + "(AI)'s turn.");
                Random rand = new Random();
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                column = rand.nextInt(7);
            } else{
                System.out.println("Player " + currentPlayer.getName() + "'s turn.");
                column = getPlayerMove();
            }

            if (board.placePiece(column, currentPlayer.getSymbol())) {
                board.display();
                if (board.checkWin(currentPlayer.getSymbol())) {
                    if (currentPlayerIndex == 1){
                        System.out.println("Player " + currentPlayer.getName() + "(AI) wins!");
                        database.updateScoreBoard(players[currentPlayerIndex]);
                        break;
                    } else{
                        System.out.println("Player " + currentPlayer.getName() + " wins!");
                        database.updateScoreBoard(players[currentPlayerIndex]);
                        break;
                    }
                }
                if (board.isFull()) {
                    System.out.println("It's a draw!");
                    break;
                }
                currentPlayerIndex = (currentPlayerIndex + 1) % players.length;
            } else {
                System.out.println("Column is full or invalid. Try again.");
            }
        }
        currentPlayerIndex = (currentPlayerIndex + 1) % players.length;
    }
}
