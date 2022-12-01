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
import javafx.stage.Stage;

import java.net.URL;
import java.security.Key;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;
import javax.swing.text.AbstractDocument.LeafElement;
import game.model.*;

public class PrimaryController implements Initializable {

    private Controller ctrl;

    @FXML
    private Canvas canvas;
    private GraphicsContext gc;

    private boolean isRunning = true;


    //Elementos gráficos
    private Avatar avatar,avatar2;
    private EnemyAvatar enemyAvatar;


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
    boolean shotEnemy = false;


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

        ctrl = new Controller(new Player("jiji"), new Player("jaja"));

        Enemy.getInstance().setMap(ctrl.getStage());
        Enemy.getInstance().setTarget1(ctrl.getPlayer1());
        Enemy.getInstance().setTarget2(ctrl.getPlayer2());

        initializedMap1();
        initializedMap2();
        selectMap();


    }

    public void selectMap(){
        int[][] temp = new int[14][14];

        int map = (int) (Math.random() * 2 + 1);
        switch (map){
            case 1:
                draw(map1);

                String[] obstPos = {"3,4", "5,4", "8,4", "10,4", "3,10", "5,10", "8,10", "10,10"};

                for(int i=0; i<14; i++){
                    for (int j=0; j<14; j++){
                        temp[i][j] = 1;
                        for (String h : obstPos){
                            if((i + "," + j).equals(h)){
                                temp[i][j] = 0;
                            }
                        }
                    }
                }
                ctrl.createScenario(temp, "2,2", "6,2", "1,4");

                ctrl.getPlayer1().setPosition(ctrl.getStage().searchVertex("2,2"));
                ctrl.getPlayer2().setPosition(ctrl.getStage().searchVertex("6,2"));
                Enemy.getInstance().setPosition(ctrl.getStage().searchVertex("1,4"));
                avatar = new Avatar(canvas, ctrl.getPlayer1(), 100, 100);
                avatar2 = new Avatar(canvas, ctrl.getPlayer2(), 100, 300);
                enemyAvatar = new EnemyAvatar(canvas, Enemy.getInstance(), 250, 50);
                break;
            case 2:

                String[] obstPos2 = {"5,0","5,1","5,2","0,5","1,5","2,5","5,11","5,12","5,13","0,8","1,8","2,8","8,0","8,1","8,2","13,5","12,5","11,5","8,11","8,12","8,13","13,8","12,8","11,8"};
                
                for(int i=0; i<14; i++){
                    for (int j=0; j<14; j++){
                        temp[i][j] = 1;
                        for (String h : obstPos2){
                            if((i + "," + j).equals(h)){
                                temp[i][j] = 0;
                            }
                        }
                    }
                }
                
                ctrl.createScenario(temp, "2,2", "6,2", "1,4");

                ctrl.getPlayer1().setPosition(ctrl.getStage().searchVertex("2,2"));
                ctrl.getPlayer2().setPosition(ctrl.getStage().searchVertex("6,2"));
                Enemy.getInstance().setPosition(ctrl.getStage().searchVertex("1,4"));
                avatar = new Avatar(canvas, ctrl.getPlayer1(), 100, 100);
                avatar2 = new Avatar(canvas, ctrl.getPlayer2(), 100, 300);
                enemyAvatar = new EnemyAvatar(canvas, Enemy.getInstance(), 200, 50);
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
                            enemyAvatar.draw();
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
                                        drawShot(avatar, avatar2,enemyAvatar,1);
                                    }
                                    Fpressed=false;
                                }
                                if(Gpressed||shotPlayer1){
                                    if((avatar.getPosShot().x<=canvas.getWidth()&&avatar.getPosShot().y<=canvas.getHeight())&&(avatar.getPosShot().x>=0&&avatar.getPosShot().y>=0)){
                                        drawShot(avatar, avatar2,enemyAvatar,1);
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
                                        drawShot(avatar2, avatar,enemyAvatar,2);
                                    }
                                    SPACEpressed=false;
                                }
                                if(Mpressed||shotPlayer2){
                                    if((avatar2.getPosShot().x<=canvas.getWidth()&&avatar2.getPosShot().y<=canvas.getHeight())&&(avatar2.getPosShot().x>=0&&avatar2.getPosShot().y>=0)){
                                        drawShot(avatar2, avatar,enemyAvatar,2);
                                    }
                                    Mpressed=false;
                                }
                            }

                            if(enemyAvatar.move()&&Enemy.getInstance().clearShot()||shotEnemy){
                                drawShot(enemyAvatar, avatar, avatar2, 3);
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

    private void drawShot(Avatar avatarWhoShot, Avatar avatarShoted1, Avatar avatarShoted2, int player){

        switch(player){
            case(1):
                shotPlayer1=true;
                break;
            case(2):
                shotPlayer2=true;
                break;
            case(3):
                shotEnemy=true;
                break;
        }

        boolean shot = true;
        avatarWhoShot.shot();
        avatarWhoShot.moveForwardShot();

        if((avatarWhoShot.getPosShot().x>canvas.getWidth()||avatarWhoShot.getPosShot().y>canvas.getHeight())||(avatarWhoShot.getPosShot().x<0||avatarWhoShot.getPosShot().y<0)){
            switch(player){
                case(1):
                    shotPlayer1=false;
                    break;
                case(2):
                    shotPlayer2=false;
                    break;
                case(3):
                    shotEnemy=false;
                    break;
            }
        }

        Avatar avatarShoted = null;

        if(avatarWhoShot.getPosShot().x<=avatarShoted1.getPos().x+25*Math.cos(Math.toRadians(avatarShoted1.getPos().getAngle()))&&
                avatarWhoShot.getPosShot().x>=avatarShoted1.getPos().x-25*Math.cos(Math.toRadians(avatarShoted1.getPos().getAngle()))&&
                avatarWhoShot.getPosShot().y-25*Math.sin(Math.toRadians(avatarShoted1.getPos().getAngle()))<=avatarShoted1.getPos().y&&
                avatarWhoShot.getPosShot().y+25*Math.sin(Math.toRadians(avatarShoted1.getPos().getAngle()))>=avatarShoted1.getPos().y){
            shot = false;
            switch(player){
                case(1):
                    shotPlayer1=false;
                    break;
                case(2):
                    shotPlayer2=false;
                    break;
                case(3):
                    shotEnemy=false;
                    break;
            }
            avatarShoted = avatarShoted1;
        }
        else if(avatarWhoShot.getPosShot().x<=avatarShoted2.getPos().x+25*Math.cos(Math.toRadians(avatarShoted2.getPos().getAngle()))&&
                    avatarWhoShot.getPosShot().x>=avatarShoted2.getPos().x-25*Math.cos(Math.toRadians(avatarShoted2.getPos().getAngle()))&&
                    avatarWhoShot.getPosShot().y-25*Math.sin(Math.toRadians(avatarShoted2.getPos().getAngle()))<=avatarShoted2.getPos().y&&
                    avatarWhoShot.getPosShot().y+25*Math.sin(Math.toRadians(avatarShoted2.getPos().getAngle()))>=avatarShoted2.getPos().y){
                shot = false;
                switch(player){
                    case(1):
                        shotPlayer1=false;
                        break;
                    case(2):
                        shotPlayer2=false;
                        break;
                    case(3):
                        shotEnemy=false;
                        break;
                }
                avatarShoted = avatarShoted2;
        }
        if(!shot&&avatarShoted!=null&&avatarShoted.getLives()>0){
            avatarShoted.impact();
            long times = System.currentTimeMillis();
            gc.drawImage(image,avatarShoted.getPos().x - 25 ,avatarShoted.getPos().y - 25, 50,50);
        }



    }

    public void initializedMap1(){
        Obstacle ob = new Obstacle(200,150,50,canvas);
        Obstacle ob2 = new Obstacle(200,250,50,canvas);
        Obstacle ob3 = new Obstacle(200,400,50,canvas);
        Obstacle ob4 = new Obstacle(200,500,50,canvas);
        Obstacle ob5 = new Obstacle(500,150,50,canvas);
        Obstacle ob6 = new Obstacle(500,250,50,canvas);
        Obstacle ob7 = new Obstacle(500,400,50,canvas);
        Obstacle ob8 = new Obstacle(500,500,50,canvas);
        Obstacle ob9 = new Obstacle(500,650,50,canvas);
        Obstacle ob10 = new Obstacle(200,650,50,canvas);
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