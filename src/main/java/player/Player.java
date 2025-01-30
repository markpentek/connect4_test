package player;

public class Player {
    private String name;
    private int score;


    public Player(String name, int score) {
        this.name = name;
        this.score = score;
    }


    public void setScore(int score) {
        this.score = score;
    }

    public char getSymbol() {
        return this.name.toUpperCase().charAt(0);
    }


    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void addScore() {
        this.score = this.score + 1;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", score=" + score +
                '}';
    }
}
