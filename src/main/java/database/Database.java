package database;

import player.Player;

import java.io.*;
import java.util.*;

public class Database {

    private List<Player> scoreBoard;

    public Database() {
        this.scoreBoard = new ArrayList<>();
    }


    public void updateScoreBoard(Player player) {
        if (!playerExist(player)){
            player.addScore();
            scoreBoard.add(player);
        }else {
            addScore(player);
        }
    }

    public void addScore(Player player) {
        for (Player p : scoreBoard) {
            if (p.getName().equals(player.getName())){
                p.setScore(p.getScore()+1);
            }
        }
    }

    public boolean playerExist(Player player) {
        for (Player p : scoreBoard) {
            if (p.getName().equals(player.getName())){
                return true;
            }
        }
        return false;
    }


    public void showScores() {
        scoreBoard.sort(Comparator.comparing(Player::getScore).reversed());
        System.out.println("----ScoreBoard----");
        for (int i = 0; i < scoreBoard.size(); i++) {
            System.out.println(scoreBoard.get(i).getName() + " " + scoreBoard.get(i).getScore());
        }
        System.out.println("------------------");
    }

    public void writeToFile() {
        File score = new File("score.txt");

        try {
            FileWriter writer = new FileWriter(score);
            BufferedWriter ki = new BufferedWriter(writer);

            for (Player player : scoreBoard) {
                String sor = player.getName() + " " + player.getScore();
                ki.append(sor);
                ki.newLine();
            }

            ki.flush();
            ki.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void loadFromFile() {
        File scoreFile = new File("score.txt");

        try {
        FileReader reader = new FileReader(scoreFile);
        BufferedReader be = new BufferedReader(reader);
        Scanner scanner = new Scanner(be);

        this.scoreBoard = new ArrayList<>();

        while (scanner.hasNextLine()){
            String sor = scanner.nextLine();
            String reszek[] = sor.split(" ");
            String nev = reszek[0];
            int playerScore = Integer.parseInt(reszek[1]);

            Player player = new Player(nev,playerScore);

            scoreBoard.add(player);
        }

            scanner.close();
            be.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
