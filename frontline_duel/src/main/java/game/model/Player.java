package game.model;

import java.util.ArrayList;

public class Player implements Moveable{
    private String name;
    private ArrayList<String> names;
    private double score;

    private Player right;
    private Player left;
    private Vertex<String, Moveable> position;

    private Score posScore;

    private ListGraph<String, Moveable> map;



    public Player (String name) {
        this.name=name;
        names = new ArrayList<String>();
        posScore = new Score();
    }

    public Player getRight() {
        return right;
    }

    public void setRight(Player right) {
        this.right = right;
    }

    public Player getLeft() {
        return left;
    }

    public void setLeft(Player left) {
        this.left = left;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public boolean move(double posX, double posY){
        String[] pos = position.getId().split(",");
        int i = (int)(posY/50), j = (int)(posX/50);

        System.out.println("#" + i + "/" + j);
        /*System.out.println(i!=Integer.parseInt(pos[0])||j !=Integer.parseInt(pos[1]));
        System.out.println(pos[0] + "," + pos[1]);*/
        if(i!=Integer.parseInt(pos[0])||j !=Integer.parseInt(pos[1])){
            /*System.out.println(posX + "," + posY);
            System .out.println(i + "," + j);*/
            position.setValue(null);
            position = map.searchVertex(i + "," + j);
            position.setValue(this);
            Enemy.getInstance().updatePath();
            return true;
        }
        return false;
    }

    public boolean move() {
        return false;
    }

    public Vertex<String, Moveable> getPosition() {
        return position;
    }
    public void setPosition(Vertex<String, Moveable> position) {
        this.position = position;
    }

    public boolean empty() {
        return names.isEmpty();
    }

    public ArrayList<String> getNames(){
        return new ArrayList<>(names);
    }
    public void addArray(String value){
        names.add(value);
    }
    public Score getPosScore() {
        return posScore;
    }

    public void clean(){
        position = null;
        posScore = null;
    }

    public VertexGraph<String, Moveable> getMap() {
        return map;
    }
    public void setMap(ListGraph<String, Moveable> map) {
        this.map = map;
    }

    public void murder(){
        position.setValue(null);
    }
}
