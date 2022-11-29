package game.ui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.net.URL;
import java.security.Key;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;
import javax.swing.text.AbstractDocument.LeafElement;

public class PrimaryController implements Initializable {

    @FXML
    private Canvas canvas;
    private GraphicsContext gc;
    private boolean isRunning = true;


    //Elementos gráficos
    private Avatar avatar,avatar2;


    //Estados de las teclas
    boolean Wpressed = false;
    boolean Apressed = false;
    boolean Spressed = false;
    boolean Dpressed = false;

    boolean Fpressed = false;

    boolean UPpressed = false;
    boolean DOWNpressed = false;
    boolean RIGTHpressed = false;
    boolean LEFTpressed = false;

    boolean SPACEpressed = false;

    boolean shotPlayer1 = false;

    boolean shotPlayer2 = false;


    double WmomentumCounter = 0;
    double AmomentumCounter = 0;
    double SmomentumCounter = 0;
    double DmomentumCounter = 0;

    double UPmomentumCounter = 0;
    double LEFTmomentumCounter = 0;
    double DOWNmomentumCounter = 0;
    double RIGHTmomentumCounter = 0;

    final double MOVEMENT_CONSTANT = 5;
    final double TURN_CONSTANT = 3;

    String uri = "file:"+FrontlineDuel.class.getResource("boom.png").getPath();
    Image image = new Image(uri);



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gc = canvas.getGraphicsContext2D();
        canvas.setFocusTraversable(true);

        canvas.setOnMouseClicked(this::onMouseClicked);
        canvas.setOnKeyPressed(this::onKeyPressed);
        canvas.setOnKeyReleased(this::onKeyReleased);

        avatar = new Avatar(canvas);
        avatar2 = new Avatar(canvas);
        draw();
    }

    private void onKeyReleased(KeyEvent keyEvent) {
        if(keyEvent.getCode() == KeyCode.W){
            Wpressed = false;
        }
        if(keyEvent.getCode() == KeyCode.A){
            Apressed = false;
        }
        if(keyEvent.getCode() == KeyCode.S){
            Spressed = false;
        }
        if(keyEvent.getCode() == KeyCode.D){
            Dpressed = false;
        }
        if(keyEvent.getCode() == KeyCode.F){
            Fpressed = false;
        }
        if(keyEvent.getCode() == KeyCode.SPACE){
            SPACEpressed = false;
        }
        if(keyEvent.getCode() == KeyCode.UP){
            UPpressed = false;
        }
        if(keyEvent.getCode() == KeyCode.DOWN){
            DOWNpressed = false;
        }
        if(keyEvent.getCode() == KeyCode.RIGHT){
            RIGTHpressed = false;
        }
        if(keyEvent.getCode() == KeyCode.LEFT){
            LEFTpressed = false;
        }
    }

    private void onKeyPressed(KeyEvent keyEvent) {
        System.out.println(keyEvent.getCode());
        if(keyEvent.getCode() == KeyCode.W){
            Wpressed = true;
            Fpressed = false;
            WmomentumCounter = MOVEMENT_CONSTANT;
        }
        if(keyEvent.getCode() == KeyCode.A){
            Apressed = true;
            AmomentumCounter = -TURN_CONSTANT;
            Dpressed = false;
        }
        if(keyEvent.getCode() == KeyCode.S){
            Spressed = true;
            Wpressed = false;
            SmomentumCounter = MOVEMENT_CONSTANT;
        }
        if(keyEvent.getCode() == KeyCode.D){
            Dpressed = true;
            Apressed = false;
            DmomentumCounter = TURN_CONSTANT;
        }
        if(keyEvent.getCode() == KeyCode.F){
            if(shotPlayer2) {
                    synchronized(Thread.currentThread()){
                        try{
                            Thread.sleep(50);
                        }
                        catch(Exception e){
                            JOptionPane.showMessageDialog(null, "Wut");
                        }
                        
                    }
            }
            Fpressed = true;

        }
        if(keyEvent.getCode() == KeyCode.SPACE){
            if(!shotPlayer1) {
                synchronized(Thread.currentThread()){
                    try{
                        Thread.sleep(50);
                    }
                    catch(Exception e){
                        JOptionPane.showMessageDialog(null, "Wut");
                    }
                    
                }
            }
            SPACEpressed = true;
        }
        if(keyEvent.getCode() == KeyCode.UP){
            UPpressed = true;
            DOWNpressed = false;
            UPmomentumCounter = MOVEMENT_CONSTANT;
        }
        if(keyEvent.getCode() == KeyCode.DOWN){
            DOWNpressed = true;
            UPpressed = false;
            DOWNmomentumCounter = MOVEMENT_CONSTANT;
        }
        if(keyEvent.getCode() == KeyCode.RIGHT){
            RIGTHpressed = true;
            LEFTpressed = false;
            LEFTmomentumCounter = TURN_CONSTANT;
        }
        if(keyEvent.getCode() == KeyCode.LEFT){
            LEFTpressed = true;
            RIGTHpressed = false;
            RIGHTmomentumCounter = -TURN_CONSTANT;
        }
    }

    private void onMouseClicked(MouseEvent mouseEvent) {
        avatar.setPosition(mouseEvent.getX(), mouseEvent.getY());
    }

    public void draw(){
        new Thread(
                ()->{
                    while(isRunning){
                        //Dibujo
                        Platform.runLater(()->{
                            gc.setFill(Color.BLACK);
                            gc.fillRect(0,0, canvas.getWidth(), canvas.getHeight());
                            avatar.draw();
                            avatar2.draw();
                            if(avatar.getLives()>0){

                                if(Wpressed||WmomentumCounter>0){                                    
                                    avatar.moveForward(WmomentumCounter/MOVEMENT_CONSTANT);
                                    WmomentumCounter = Wpressed? MOVEMENT_CONSTANT: WmomentumCounter-0.1;
                                }
                                if(Apressed||AmomentumCounter<0){
                                    avatar.changeAngle(AmomentumCounter);
                                    AmomentumCounter = Apressed?-TURN_CONSTANT:AmomentumCounter+0.1;
                                }
                                if(Spressed||SmomentumCounter>0){
                                    avatar.moveBackward(SmomentumCounter/MOVEMENT_CONSTANT);
                                    SmomentumCounter = Spressed? MOVEMENT_CONSTANT: SmomentumCounter-0.1;
                                }
                                if(Dpressed||DmomentumCounter>0){
                                    avatar.changeAngle(DmomentumCounter);
                                    DmomentumCounter = Dpressed?TURN_CONSTANT:DmomentumCounter-0.1;
                                    
                                }
                                if(Fpressed&&!shotPlayer1){
                                    avatar.setShot();
                                    drawShot(avatar, avatar2,1);
                                    shotPlayer1 = true;
                                    Fpressed=false;
                                    avatar.setShot();
                                }
                            }
                            if(avatar2.getLives()>0){
                                if(UPpressed||UPmomentumCounter>0){
                                    avatar2.moveForward(UPmomentumCounter/MOVEMENT_CONSTANT);
                                    UPmomentumCounter = UPpressed? MOVEMENT_CONSTANT : UPmomentumCounter-0.1;
                                }
                                if(LEFTpressed||LEFTmomentumCounter<0){
                                    avatar2.changeAngle(LEFTmomentumCounter);
                                    LEFTmomentumCounter = LEFTpressed? -TURN_CONSTANT : LEFTmomentumCounter + 0.1;
                                }
                                if(DOWNpressed||DOWNmomentumCounter>0){
                                    avatar2.moveBackward(DOWNmomentumCounter/MOVEMENT_CONSTANT);
                                    DOWNmomentumCounter = DOWNpressed? MOVEMENT_CONSTANT : DOWNmomentumCounter-0.1;
                                }
                                if(RIGTHpressed||RIGHTmomentumCounter>0){
                                    avatar2.changeAngle(RIGHTmomentumCounter);
                                    RIGHTmomentumCounter = RIGTHpressed? TURN_CONSTANT : RIGHTmomentumCounter-0.1;
                                }
                                if(SPACEpressed&&!shotPlayer2){
                                    avatar2.setShot();
                                    drawShot(avatar2, avatar,2);
                                    shotPlayer2=true;
                                    SPACEpressed=false;
                                    avatar2.setShot();
                                }
                            }


                        });
                        //Sleep
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
        ).start();


    }

    private void drawShot(Avatar avatarWhoShot, Avatar avatarShoted, int player){

        boolean draw=true;

        if(player==1&& shotPlayer1)draw=false;
        if(player==2&& shotPlayer2)draw=false;

        Runnable runnable = () -> {
            boolean shot = true;
            while(shot&&(avatarWhoShot.getPosShot().x<=canvas.getWidth()&&avatarWhoShot.getPosShot().y<=canvas.getHeight())&&(avatarWhoShot.getPosShot().x>=0&&avatarWhoShot.getPosShot().y>=0)){
                avatarWhoShot.shot();
                avatarWhoShot.moveForwardShot();

                if(avatarWhoShot.getPosShot().x<=avatarShoted.getPos().x+25*Math.cos(Math.toRadians(avatarShoted.getPos().getAngle()))&&
                        avatarWhoShot.getPosShot().x>=avatarShoted.getPos().x-25*Math.cos(Math.toRadians(avatarShoted.getPos().getAngle()))&&
                        avatarWhoShot.getPosShot().y-25*Math.sin(Math.toRadians(avatarShoted.getPos().getAngle()))<=avatarShoted.getPos().y&&
                        avatarWhoShot.getPosShot().y+25*Math.sin(Math.toRadians(avatarShoted.getPos().getAngle()))>=avatarShoted.getPos().y){
                    shot = false;
                }
                try {
                    synchronized (Thread.currentThread()){
                        Thread.currentThread().wait(20);
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            if(!shot&&avatarShoted.getLives()>0){
                avatarShoted.impact();
                long times = System.currentTimeMillis();
                while(System.currentTimeMillis()<times+50){
                    gc.drawImage(image,avatarShoted.getPos().x - 25 ,avatarShoted.getPos().y - 25, 50,50);
                    try {
                        synchronized (Thread.currentThread()){
                            Thread.currentThread().wait(10);
                        }
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            if(player==1)shotPlayer1=false;
            if(player==2)shotPlayer2=false;
            Thread.currentThread().interrupt();
        };

        if(draw){
            Thread thread = new Thread(runnable);

            thread.start();
            if(player==1)shotPlayer1=false;
            if(player==2)shotPlayer2=false;
        }



    }

}