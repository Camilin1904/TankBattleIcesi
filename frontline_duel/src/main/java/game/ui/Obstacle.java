package game.ui;

import javafx.geometry.Bounds;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Obstacle {

    protected int size;

    protected int x;

    protected int  y;

    private GraphicsContext gc;

    private Image image;

    protected Rectangle shape;

    private Canvas canvas;

    public Obstacle(int x,int y,int size,Canvas canvas){
        this.x=x;
        this.y=y;
        this.size=size;
        this.canvas = canvas;
        gc = canvas.getGraphicsContext2D();

        String uri = "file:"+FrontlineDuel.class.getResource("roca.png").getPath();
        image = new Image(uri);

        shape = new Rectangle(x,y, size,size);


    }

    public void draw(){
        gc.save();
        gc.drawImage(image,x,y,size,size);
        gc.restore();
    }





    public Bounds getBoundsInParent() {
        return shape.getBoundsInParent();
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public double getWidth(){
        return shape.getWidth();
    }
    public double getHeight(){
        return  shape.getHeight();
    }



}

