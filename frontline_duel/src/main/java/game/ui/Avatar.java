package game.ui;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class Avatar {

    private Canvas canvas;
    private GraphicsContext gc;

    private Image tank;

    private Vector pos;

    private Vector direction;

    private Vector posShot;


    private Vector directShot;

    private int lives;

    private double SHOT_SPEED = 2;

    private Circle shape;


    public Avatar(Canvas canvas){
        this.canvas = canvas;
        gc = canvas.getGraphicsContext2D();
        String uri = "file:"+FrontlineDuel.class.getResource("tank.png").getPath();
        tank = new Image(uri);
        pos = new Vector(100,100);
        posShot = new Vector(100,100);
        direction = new Vector(2,2);
        directShot = new Vector(2,2);
        lives = 5;

        shape = new Circle(-25,-25, 25);
    }

    public void draw(){
        gc.save();
        gc.translate(pos.x, pos.y);
        gc.rotate(90+direction.getAngle());
        gc.drawImage(tank, -25,-25, 50,50);
        gc.setFill(Color.RED);
        gc.fillOval(-16,-9,29,29);
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

       for (int i = 0; i<ob.size();i++){
           System.out.println(ob.get(i).getX() +" --------- " + ob.get(i).getY());
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
        }
    }

}
