package game.ui;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

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
    }

    public void draw(){
        gc.save();
        gc.translate(pos.x, pos.y);
        gc.rotate(90+direction.getAngle());
        gc.drawImage(tank, -25,-25, 50,50);
        gc.restore();
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

    public void moveForward(double mult){
        pos.x += direction.x*mult;
        pos.y += direction.y*mult;

    }

    public void moveBackward(double mult){
        pos.x -= direction.x*mult;
        pos.y -= direction.y*mult;

    }

    public void setShot(){
        double amp = direction.getAmplitude();
        double angle = direction.getAngle();
        posShot.x = pos.x;
        posShot.y = pos.y;
        directShot.x = amp*Math.cos(Math.toRadians(angle) + (Math.PI/2));
        directShot.y = amp*Math.sin(Math.toRadians(angle) + (Math.PI/2));
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
            String uri = "file:"+FrontlineDuel.class.getResource("boom.png").getPath();
            tank = new Image(uri);
            System.out.println(tank.getUrl());
        }
    }

}
