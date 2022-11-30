package game.ui;

import javafx.scene.canvas.Canvas;

import java.util.Arrays;

import game.model.*;

public class EnemyAvatar extends Avatar{

    double angle = 0;
    int adN = 0;
    String actualTargCoord = "";
    boolean firstPass = true;
    boolean moving = false;

    public EnemyAvatar(Canvas canvas, Moveable character, int posX, int posY) {
        super(canvas, character, posX, posY);
    }

    public void setMovement(){
        
    }

    public void move(){
        Enemy.getInstance().updatePath();
        String[] targ = Enemy.getInstance().getTargetCoordinates().split(",");
        System.out.println(Arrays.toString(targ));
        double amp = direction.getAmplitude();
        double actAngle = direction.getAngle()>=0?direction.getAngle():direction.getAngle()+360;
        String[] posi = Enemy.getInstance().getPosition().getId().split(",");
        int difY = Integer.parseInt(targ[0])-Integer.parseInt(posi[0]);
        int difX = Integer.parseInt(targ[1])-Integer.parseInt(posi[1]);
        int addAngle = 0;


        if(difX==1&&difY==0) angle = 0;
        else if(difX==1&&difY==1) angle = 45;
        else if(difX==0&&difY==1) angle = 90;
        else if(difX==-1&&difY==1) angle = 135;
        else if(difX==-1&&difY==0) angle = 180;
        else if(difX==-1&&difY==-1) angle = 225;
        else if(difX==0&&difY==-1) angle = 270;
        else if(difX==1&&difY==-1) angle = 315;


        System.out.println(angle + "**" + actAngle);
        System.out.println("!" + Arrays.toString(targ));
        System.out.println("¡" + Arrays.toString(posi));




        if(angle == (int)actAngle){
            System.out.println(direction.x + "/" + direction.y);
            //System.out.println(direction.y + "/" + direction.x);
            pos.x += direction.x;
            pos.y += direction.y;

            objectAssigned.move(pos.x, pos.y);
        } 
        else{
            actAngle += 1;
            direction.x = amp*Math.cos(Math.toRadians(actAngle));
            direction.y = amp*Math.sin(Math.toRadians(actAngle));

            
        }
       // else if(angle>360)angle = 0;
        //else{

        //}
        
    }
    
}
