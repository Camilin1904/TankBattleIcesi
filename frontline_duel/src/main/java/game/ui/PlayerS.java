package game.ui;

public class PlayerS {

    private String name;

    private int wonGames;

    private int position;

    public PlayerS(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWonGames() {
        return wonGames;
    }

    public void setWonGames(int wonGames) {
        this.wonGames = wonGames;
    }

    public int getGamesWon() {
        return this.wonGames;
    }
    public void setGamesWon(int wonGames){
        this.wonGames =wonGames;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
