package game.model;

public class Singleton {

    private static Singleton instance;
    private PlayerS player1;
    private PlayerS player2;



    public Singleton() {
        player1 = new PlayerS("");
        player2 = new PlayerS("");
    }

    public void createPlayers(String name1, String name2){
        this.player1.setName(name1);
        this.player2.setName(name2);
    }

    public PlayerS getPlayer1() {
        return player1;
    }

    public void setPlayer1(PlayerS player1) {
        this.player1 = player1;
    }

    public PlayerS getPlayer2() {
        return player2;
    }

    public void setPlayer2(PlayerS player2) {
        this.player2 = player2;
    }

    public static Singleton getInstance(){
        if(instance==null){
            instance= new Singleton();
        }

        return instance;
    }
}
