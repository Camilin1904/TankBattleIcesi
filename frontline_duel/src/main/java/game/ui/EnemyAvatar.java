package game.ui;

import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

import java.util.Arrays;

import game.model.*;

public class EnemyAvatar extends Avatar{

    double angle = 0;
    int adN = 0;
    String actualTargCoord = "";
    boolean firstPass = true;
    private boolean isShooting = false;

    public EnemyAvatar(Canvas canvas, Moveable character, int posX, int posY, PlayerS player, String uri) {
        super(canvas, character, posX, posY, player, uri);
        actualTargCoord = Enemy.getInstance().getPosition().getId();
    }

    public void setMovement(){
        
    }

    public boolean move(){
        Enemy.getInstance().updatePath();
        String[] targ = Enemy.getInstance().getTargetCoordinates().split(",");
        double amp = direction.getAmplitude();
        double actAngle = direction.getAngle()>=0?direction.getAngle():direction.getAngle()+360;
        String[] posi = Enemy.getInstance().getPosition().getId().split(",");
        int difY = Integer.parseInt(targ[0])-Integer.parseInt(posi[0]);
        int difX = Integer.parseInt(targ[1])-Integer.parseInt(posi[1]);
        int addAngle = 0;
        System.out.println(Enemy.getInstance().getPath());
        System.out.println(Enemy.getInstance().clearShot());
            if(difX==1&&difY==0) angle = 0;
            else if(difX==1&&difY==1) angle = 45;
            else if(difX==0&&difY==1) angle = 90;
            else if(difX==-1&&difY==1) angle = 135;
            else if(difX==-1&&difY==0) angle = 180;
            else if(difX==-1&&difY==-1) angle = 225;
            else if(difX==0&&difY==-1) angle = 270;
            else if(difX==1&&difY==-1) angle = 315;

        if(angle == (int)actAngle){
            firstPass = false;
            if(!Enemy.getInstance().clearShot()){
                if(pos.x+direction.x>3&&pos.y+direction.y>3){
                    pos.x += direction.x;
                    pos.y += direction.y;
                }
                objectAssigned.move(pos.x, pos.y);
                if(Enemy.getInstance().getPosition().getId().equals(actualTargCoord)) actualTargCoord = Enemy.getInstance().getTargetCoordinates();
            }
            if(!isShooting) setShot();
            return true;

        } 
        else{
            System.out.println(angle + "()" + actAngle);
            if(angle>actAngle)actAngle += (adN = 1);
            else if (angle<actAngle)actAngle += (adN = -1);
            else actAngle += adN;
            
            direction.x = amp*Math.cos(Math.toRadians(actAngle));
            direction.y = amp*Math.sin(Math.toRadians(actAngle));
            if(!isShooting) setShot();
            return false;
        }

        
       // else if(angle>360)angle = 0;
        //else{

        //}
        
    }
    
    public void setShot(){
        double amp = direction.getAmplitude();
        double angle = direction.getAngle();
        posShot.x = pos.x;
        posShot.y = pos.y;
        directShot.x = amp*Math.cos(Math.toRadians(angle));
        directShot.y = amp*Math.sin(Math.toRadians(angle));
    }

    public void shot(){
        gc.setFill(Color.BLUE);
        gc.fillRect(posShot.x, posShot.y, 10,10);
        gc.restore();
        isShooting = true;
    }

    public void moveForwardShot(){
        posShot.x += SHOT_SPEED*directShot.x;
        posShot.y += SHOT_SPEED*directShot.y;
    }

    public void setShooting(boolean isShooting) {
        this.isShooting = isShooting;
    }

    public boolean getIsShooting(){
        return isShooting;
    }
}  
