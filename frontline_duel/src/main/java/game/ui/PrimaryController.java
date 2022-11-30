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
import java.util.ArrayList;
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

    private String fondo = "file:"+FrontlineDuel.class.getResource("Fondo.jpeg").getPath();

    private Image fon = new Image(fondo);

    private ArrayList<Obstacle> map1 = new ArrayList<>();

    private ArrayList<Obstacle> map2 = new ArrayList<>();


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
        initializedMap2();
        selectMap();


    }

    public void selectMap(){

        int map = (int) (Math.random() * 2 + 1);
        switch (map){
            case 1:
                draw(map1);
                break;
            case 2:
                draw(map2);
                break;
            case 3:
                //draw(map3);
                break;
        }

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

    public void draw(ArrayList<Obstacle> map){
        new Thread(
                ()->{
                    while(isRunning){
                        //Dibujo
                        Platform.runLater(()->{
                            drawBackground();
                            avatar.draw();
                            avatar2.draw();
                            drawMap(map);
                            if(avatar.getLives()>0){
                                if(Wpressed||WmomentumCounter>0){
                                    if(!avatar.checkObstacleX(map)) {

                                        avatar.moveForward(WmomentumCounter / MOVEMENT_CONSTANT);
                                        UPmomentumCounter = Wpressed ? MOVEMENT_CONSTANT : WmomentumCounter - 0.1;

                                    }else {
                                        double variable = 1.5;
                                        avatar.moveBackward(variable);
                                        WmomentumCounter = 0;
                                    }

                                }
                                if(Apressed||AmomentumCounter<0){
                                    avatar.changeAngle(AmomentumCounter);
                                    AmomentumCounter = Apressed?-TURN_CONSTANT:AmomentumCounter+0.1;
                                }
                                if(Spressed||SmomentumCounter>0){

                                    if(!avatar.checkObstacleX(map)) {
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
                                    if(!avatar2.checkObstacleX(map)) {

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
                                    if(!avatar2.checkObstacleX(map)) {

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

    public void initializedMap1(){
        Obstacle ob = new Obstacle(175,125,50,canvas);
        Obstacle ob2 = new Obstacle(175,250,50,canvas);
        Obstacle ob3 = new Obstacle(175,375,50,canvas);
        Obstacle ob4 = new Obstacle(175,500,50,canvas);
        Obstacle ob5 = new Obstacle(475,125,50,canvas);
        Obstacle ob6 = new Obstacle(475,250,50,canvas);
        Obstacle ob7 = new Obstacle(475,375,50,canvas);
        Obstacle ob8 = new Obstacle(475,500,50,canvas);
        Obstacle ob9 = new Obstacle(475,625,50,canvas);
        Obstacle ob10 = new Obstacle(175,625,50,canvas);
        map1.add(ob);
        map1.add(ob2);
        map1.add(ob3);
        map1.add(ob4);
        map1.add(ob5);
        map1.add(ob6);
        map1.add(ob7);
        map1.add(ob8);
        map1.add(ob9);
        map1.add(ob10);
    }

    public void initializedMap2(){
        //ESI
        Obstacle ob5 = new Obstacle(0,250,50,canvas);
        Obstacle ob8 = new Obstacle(50,250,50,canvas);
        Obstacle ob10 = new Obstacle(100,250,50,canvas);

        Obstacle ob6 = new Obstacle(250,0,50,canvas);
        Obstacle ob7 = new Obstacle(250,50,50,canvas);
        Obstacle ob9 = new Obstacle(250,100,50,canvas);
        //ESD
        Obstacle ob = new Obstacle(550,250,50,canvas);
        Obstacle ob2 = new Obstacle(600,250,50,canvas);
        Obstacle ob3 = new Obstacle(650,250,50,canvas);

        Obstacle ob4 = new Obstacle(400,0,50,canvas);
        Obstacle ob11 = new Obstacle(400,50,50,canvas);
        Obstacle ob12 = new Obstacle(400,100,50,canvas);


        //EII
        Obstacle ob13 = new Obstacle(0,400,50,canvas);
        Obstacle ob14 = new Obstacle(50,400,50,canvas);
        Obstacle ob15 = new Obstacle(100,400,50,canvas);

        Obstacle ob16 = new Obstacle(250,650,50,canvas);
        Obstacle ob17 = new Obstacle(250,600,50,canvas);
        Obstacle ob18 = new Obstacle(250,550,50,canvas);
        //EID
        Obstacle ob19 = new Obstacle(550,400,50,canvas);
        Obstacle ob20 = new Obstacle(600,400,50,canvas);
        Obstacle ob21 = new Obstacle(650,400,50,canvas);

        Obstacle ob22 = new Obstacle(400,650,50,canvas);
        Obstacle ob23 = new Obstacle(400,600,50,canvas);
        Obstacle ob24 = new Obstacle(400,550,50,canvas);

        map2.add(ob);
        map2.add(ob2);
        map2.add(ob3);
        map2.add(ob4);
        map2.add(ob5);
        map2.add(ob6);
        map2.add(ob7);
        map2.add(ob8);
        map2.add(ob9);
        map2.add(ob10);
        map2.add(ob11);
        map2.add(ob12);
        map2.add(ob13);
        map2.add(ob14);
        map2.add(ob15);
        map2.add(ob16);
        map2.add(ob17);
        map2.add(ob18);
        map2.add(ob19);
        map2.add(ob20);
        map2.add(ob21);
        map2.add(ob22);
        map2.add(ob23);
        map2.add(ob24);

    }
    public void drawMap(ArrayList<Obstacle> array){
        for(int i = 0; i<array.size();i++){
            array.get(i).draw();
        }
    }
    public void drawBackground(){
        gc.save();
        gc.drawImage(fon, 0, 0, 700, 700);
        gc.restore();
    }

}