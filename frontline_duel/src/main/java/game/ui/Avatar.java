package game.ui;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.ArrayList;
import game.model.*;

public class Avatar {



    private Canvas canvas;
    protected GraphicsContext gc;

    private Image tank;

    protected Vector pos;

    protected Vector direction;

    protected Vector posShot;


    protected Vector directShot;

    private int lives;

    protected double SHOT_SPEED = 2;

    protected Moveable objectAssigned;

    private Circle shape;

    private PlayerS player;

    public Avatar(Canvas canvas, Moveable character, int posX, int posY, PlayerS player, String uri){
        this.canvas = canvas;
        gc = canvas.getGraphicsContext2D();
        tank = new Image(uri);
        pos = new Vector(posX,posY);
        posShot = new Vector(posX,posY );
        direction = new Vector(1,1);
        directShot = new Vector(1,1);
        lives = 5;
        this.player = player;
        shape = new Circle(-25,-25, 25);
        objectAssigned = character;
    }

    public void draw(){
        gc.save();
        gc.translate(pos.x, pos.y);
        gc.rotate(90+direction.getAngle());
        gc.drawImage(tank, -25,-25, 50,50);
        gc.restore();
        gc.setFill(Color.BLACK);
    }
    public void setPosition(double x, double y) {
        pos.x = (int) x - 25;
        pos.y = (int) y - 25;
    }

    public void changeAngle(double a){
        double amp = direction.getAmplitude();
        double angle = direction.getAngle();
        angle += a;
        direction.x = amp*Math.cos(Math.toRadians(angle));
        direction.y = amp*Math.sin(Math.toRadians(angle));
    }

    public boolean checkObstacleX(ArrayList<Obstacle> ob){
        System.out.println(shape.getCenterX() +" --------- " + shape.getCenterY());
       for (int i = 0; i<ob.size();i++){
           if (shape.intersects(ob.get(i).getX()+10,ob.get(i).getY()+10,30,30)){
               return true;
           }
       }
       return false;

    }

    public boolean shotObstacle(ArrayList<Obstacle> ob){

        for (int i = 0; i<ob.size();i++){
            System.out.println(ob.get(i).getX() +" --------- " + ob.get(i).getY());
            if(ob.get(i).shape.intersects(posShot.x,posShot.y,10,10)){
                return true;
            }

        }
        return false;

    }

    public boolean checkAvatar(Avatar avatar){

        return shape.intersects(avatar.pos.x,avatar.pos.y, Math.cos(Math.toRadians(avatar.pos.getAngle()))*50, Math.sin(Math.toRadians(avatar.pos.getAngle()))*50);

    }

    public void moveForward(double mult){

        if(pos.x + direction.x*mult-25>0 && pos.x + direction.x*mult+25<700 ){
            pos.x += direction.x*mult;

        }
        if(pos.y + direction.y*mult-25>0 && pos.y + direction.y*mult+25<700){
            pos.y += direction.y*mult;

        }
        objectAssigned.move(pos.x, pos.y);
        shape.setCenterX(pos.x);
        shape.setCenterY(pos.y);

    }

    public void moveBackward(double mult) {
        if(pos.x - direction.x*mult-25>0 && pos.x - direction.x*mult+25<950){
            pos.x -= direction.x*mult;
        }
        if(pos.y - direction.y*mult-25>0 && pos.y - direction.y*mult+25<950 ){
            pos.y -= direction.y*mult;
        }
        objectAssigned.move(pos.x, pos.y);
        shape.setCenterX(pos.x);
        shape.setCenterY(pos.y);

    }

    public void setShotRight(){
        double amp = direction.getAmplitude();
        double angle = direction.getAngle();
        posShot.x = pos.x;
        posShot.y = pos.y;
        directShot.x = amp*Math.cos(Math.toRadians(angle) + (Math.PI/2));
        directShot.y = amp*Math.sin(Math.toRadians(angle) + (Math.PI/2));
    }

    public void setShotLeft(){
        double amp = direction.getAmplitude();
        double angle = direction.getAngle();
        posShot.x = pos.x;
        posShot.y = pos.y;
        directShot.x = amp*Math.cos(Math.toRadians(angle) - (Math.PI/2));
        directShot.y = amp*Math.sin(Math.toRadians(angle) - (Math.PI/2));
    }

    public void shot(){
        gc.setFill(Color.RED);
        gc.fillRect(posShot.x, posShot.y, 10,10);
        gc.restore();
    }

    public void moveForwardShot(){
        posShot.x += SHOT_SPEED*directShot.x;
        posShot.y += SHOT_SPEED*directShot.y;
    }

    public Vector getPos() {
        return pos;
    }

    public Vector getDirection() {
        return direction;
    }

    public Vector getPosShot(){
        return posShot;
    }

    public Vector getDirectShot(){
        return directShot;
    }

    public int getLives(){return lives;}

    public void impact(){
        lives--;
        if(lives<=0){
            tank = null;
            String death = "file:"+FrontlineDuel.class.getResource("oil.png").getPath();
            tank = new Image(death);
            System.out.println(tank.getUrl());
            objectAssigned.murder();
        }
    }

    public PlayerS getPlayer() {
        return player;
    }

    public void setPlayer(PlayerS player) {
        this.player = player;
    }

    public Circle getCircle(){
        return shape;
    }

    public void setCircle(int x, int y){
        shape.setCenterX(x);
        shape.setCenterY(y);
    }
}
