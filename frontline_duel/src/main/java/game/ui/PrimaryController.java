package game.ui;

import javafx.application.Platform;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.swing.JOptionPane;

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
    boolean Gpressed = false;

    boolean UPpressed = false;
    boolean DOWNpressed = false;
    boolean RIGTHpressed = false;
    boolean LEFTpressed = false;
    boolean SPACEpressed = false;
    boolean Mpressed = false;

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

    private ArrayList<Obstacle> map1 = new ArrayList<>();



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gc = canvas.getGraphicsContext2D();
        canvas.setFocusTraversable(true);

        canvas.setOnMouseClicked(this::onMouseClicked);
        canvas.setOnKeyPressed(this::onKeyPressed);
        canvas.setOnKeyReleased(this::onKeyReleased);

        avatar = new Avatar(canvas);
        avatar2 = new Avatar(canvas);
        initializedMap1();
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
        if(keyEvent.getCode() == KeyCode.M){
            Mpressed = false;
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
            Apressed = false;
            WmomentumCounter = MOVEMENT_CONSTANT;
        }
        if(keyEvent.getCode() == KeyCode.A){
            Apressed = true;
            Dpressed = false;
            DmomentumCounter = -TURN_CONSTANT;
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
            if(!shotPlayer1){
                avatar.setShotRight();
            }

        }
        if(keyEvent.getCode() == KeyCode.G){
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
            Gpressed = true;
            if(!shotPlayer1){
                avatar.setShotLeft();
            }
        }
        if(keyEvent.getCode() == KeyCode.SPACE){
            if(shotPlayer1) {
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
            if(!shotPlayer2){
                avatar2.setShotRight();
            }

        }
        if(keyEvent.getCode() == KeyCode.M){
            if(shotPlayer1) {
                synchronized(Thread.currentThread()){
                    try{
                        Thread.sleep(50);
                    }
                    catch(Exception e){
                        JOptionPane.showMessageDialog(null, "Wut");
                    }

                }
            }
            Mpressed = true;
            if(!shotPlayer2){
                avatar2.setShotLeft();
            }
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
        AtomicBoolean exit = new AtomicBoolean(false);
        new Thread(
                ()->{
                    while(isRunning && !exit.get()){
                        //Dibujo
                        Platform.runLater(()->{
                            if(avatar2.getLives()<=0){
                                exit.set(true);
                                Stage stage = (Stage) canvas.getScene().getWindow();
                                stage.close();
                                FrontlineDuel.showWindow("win.fxml");
                            }
                            gc.setFill(Color.CYAN);
                            gc.fillRect(0,0, canvas.getWidth(), canvas.getHeight());
                            avatar.draw();
                            avatar2.draw();
                            drawMap(map1);
                            if(avatar.getLives()>0){
                                if(Wpressed||WmomentumCounter>0){
                                    if(!avatar.checkObstacleX(map1)&&!avatar.checkAvatar(avatar2)) {

                                        avatar.moveForward(WmomentumCounter / MOVEMENT_CONSTANT);
                                        WmomentumCounter = Wpressed ? MOVEMENT_CONSTANT : WmomentumCounter - 0.1;

                                    }else {
                                        double variable = 1.5;
                                        avatar.moveBackward(variable);
                                        WmomentumCounter = 0;
                                    }

                                }
                                if(Apressed||AmomentumCounter<0){
                                    avatar.changeAngle(AmomentumCounter);
                                    AmomentumCounter = Apressed? -TURN_CONSTANT : AmomentumCounter + 0.1;
                                }
                                if(Spressed||SmomentumCounter>0){

                                    if(!avatar.checkObstacleX(map1)&&!avatar.checkAvatar(avatar2)) {
                                        avatar.moveBackward(SmomentumCounter/MOVEMENT_CONSTANT);
                                        SmomentumCounter = Spressed? MOVEMENT_CONSTANT: SmomentumCounter-0.1;
                                    }else {
                                        double variable = 1.5;
                                        avatar.moveBackward(variable);
                                        SmomentumCounter = 0;
                                    }

                                }
                                if(Dpressed||DmomentumCounter>0){
                                    avatar.changeAngle(DmomentumCounter);
                                    DmomentumCounter = Dpressed?TURN_CONSTANT:DmomentumCounter-0.1;
                                    
                                }
                                if(Fpressed||shotPlayer1){
                                    if((avatar.getPosShot().x<=canvas.getWidth()&&avatar.getPosShot().y<=canvas.getHeight())&&(avatar.getPosShot().x>=0&&avatar.getPosShot().y>=0)){
                                        drawShot(avatar, avatar2,1);
                                    }
                                    Fpressed=false;
                                }
                                if(Gpressed||shotPlayer1){
                                    if((avatar.getPosShot().x<=canvas.getWidth()&&avatar.getPosShot().y<=canvas.getHeight())&&(avatar.getPosShot().x>=0&&avatar.getPosShot().y>=0)){
                                        drawShot(avatar, avatar2,1);
                                    }
                                    Gpressed=false;
                                }
                            }
                            if(avatar2.getLives()>0){
                                if(UPpressed||UPmomentumCounter>0){
                                    if(!avatar2.checkObstacleX(map1)&&!avatar2.checkAvatar(avatar)) {

                                        avatar2.moveForward(UPmomentumCounter / MOVEMENT_CONSTANT);
                                        UPmomentumCounter = UPpressed ? MOVEMENT_CONSTANT : UPmomentumCounter - 0.1;

                                    }else {
                                        double variable = 1.5;
                                            avatar2.moveBackward(variable);
                                            UPmomentumCounter = 0;
                                    }
                                }
                                if(LEFTpressed||LEFTmomentumCounter<0){
                                    avatar2.changeAngle(LEFTmomentumCounter);
                                    LEFTmomentumCounter = LEFTpressed? -TURN_CONSTANT : LEFTmomentumCounter + 0.1;
                                }
                                if(DOWNpressed||DOWNmomentumCounter>0){
                                    if(!avatar2.checkObstacleX(map1)&&!avatar2.checkAvatar(avatar)) {

                                        avatar2.moveBackward(DOWNmomentumCounter/MOVEMENT_CONSTANT);
                                    DOWNmomentumCounter = DOWNpressed? MOVEMENT_CONSTANT : DOWNmomentumCounter-0.1;

                                    }else {
                                        double variable = 1.5;
                                        avatar2.moveForward(variable);
                                        DOWNmomentumCounter=0;
                                    }

                                }
                                if(RIGTHpressed||RIGHTmomentumCounter>0){
                                    avatar2.changeAngle(RIGHTmomentumCounter);
                                    RIGHTmomentumCounter = RIGTHpressed? TURN_CONSTANT : RIGHTmomentumCounter-0.1;
                                }
                                if(SPACEpressed||shotPlayer2){
                                    if((avatar2.getPosShot().x<=canvas.getWidth()&&avatar2.getPosShot().y<=canvas.getHeight())&&(avatar2.getPosShot().x>=0&&avatar2.getPosShot().y>=0)){
                                        drawShot(avatar2, avatar,2);
                                    }
                                    SPACEpressed=false;
                                }
                                if(Mpressed||shotPlayer2){
                                    if((avatar2.getPosShot().x<=canvas.getWidth()&&avatar2.getPosShot().y<=canvas.getHeight())&&(avatar2.getPosShot().x>=0&&avatar2.getPosShot().y>=0)){
                                        drawShot(avatar2, avatar,2);
                                    }
                                    Mpressed=false;
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

        if(player==1)shotPlayer1=true;
        if(player==2)shotPlayer2=true;

        boolean shot = true;
        avatarWhoShot.shot();
        avatarWhoShot.moveForwardShot();

        if((avatarWhoShot.getPosShot().x>canvas.getWidth()||avatarWhoShot.getPosShot().y>canvas.getHeight())||(avatarWhoShot.getPosShot().x<0||avatarWhoShot.getPosShot().y<0)){
            if(player==1)shotPlayer1=false;
            if(player==2)shotPlayer2=false;
        }

        if(avatarWhoShot.getPosShot().x<=avatarShoted.getPos().x+25*Math.cos(Math.toRadians(avatarShoted.getPos().getAngle()))&&
                avatarWhoShot.getPosShot().x>=avatarShoted.getPos().x-25*Math.cos(Math.toRadians(avatarShoted.getPos().getAngle()))&&
                avatarWhoShot.getPosShot().y-25*Math.sin(Math.toRadians(avatarShoted.getPos().getAngle()))<=avatarShoted.getPos().y&&
                avatarWhoShot.getPosShot().y+25*Math.sin(Math.toRadians(avatarShoted.getPos().getAngle()))>=avatarShoted.getPos().y){
            shot = false;
            if(player==1)shotPlayer1=false;
            if(player==2)shotPlayer2=false;
        }
        if(!shot&&avatarShoted.getLives()>0){
            avatarShoted.impact();
            gc.drawImage(image,avatarShoted.getPos().x - 25 ,avatarShoted.getPos().y - 25, 50,50);
            long before = System.currentTimeMillis();
            gc.drawImage(image,avatarShoted.getPos().x - 25 ,avatarShoted.getPos().y - 25, 50,50);
        }

        if(avatarWhoShot.shotObstacle(map1)){
            if(player==1)shotPlayer1=false;
            if(player==2)shotPlayer2=false;
            gc.drawImage(image,avatarWhoShot.getPosShot().x ,avatarWhoShot.getPosShot().y, 50,50);

        }



    }

    public void initializedMap1(){
        Obstacle ob = new Obstacle(275,175,50,canvas);
        Obstacle ob2 = new Obstacle(275,225,50,canvas);
        Obstacle ob3 = new Obstacle(275,275,50,canvas);
        Obstacle ob4 = new Obstacle(275,325,50,canvas);
        Obstacle ob5 = new Obstacle(575,175,50,canvas);
        Obstacle ob6 = new Obstacle(575,225,50,canvas);
        Obstacle ob7 = new Obstacle(575,275,50,canvas);
        Obstacle ob8 = new Obstacle(575,325,50,canvas);
        map1.add(ob);
        map1.add(ob2);
        map1.add(ob3);
        map1.add(ob4);
        map1.add(ob5);
        map1.add(ob6);
        map1.add(ob7);
        map1.add(ob8);
        ob.draw();
    }
    public void drawMap(ArrayList<Obstacle> array){
        for(int i = 0; i<array.size();i++){
            array.get(i).draw();
        }
    }

}