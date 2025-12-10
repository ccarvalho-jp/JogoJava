package Telas;

public class PlayerScore implements Comparable<PlayerScore> {
    private final String name;
    private final int score;

    public PlayerScore(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    @Override
    public int compareTo(PlayerScore other) {
        return other.score - this.score; // decrescente
    }
}
