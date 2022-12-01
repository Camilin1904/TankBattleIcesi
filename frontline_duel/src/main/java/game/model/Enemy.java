package game.model;

import java.util.*;

public class Enemy implements Moveable{
    private Player target1;
    private Player target2;

    private Vertex<String, Moveable> position;

    private ListGraph<String, Moveable> map;
    
    private Vertex<String, Moveable> goal = null;

    private Stack<Vertex<String, Moveable>> path = null;

    private String targetCoordinates;

    private static Enemy instance = new Enemy();

    public Enemy(){
        path = new Stack<>();
    }
    
    public boolean move(double posX, double posY){
        //updatePath();
        try{
            String[] desPos = targetCoordinates.split(",");
            int i = (int)posY/50, j = (int)posX/50;
            position.setValue(null);
            position = map.searchVertex(i + "," + j);
            position.setValue(instance);
            /*System.out.println("-" + Arrays.toString(desPos));
            System.out.println(i + "," + j);*/
            if(i==Integer.parseInt(desPos[0])&& j ==Integer.parseInt(desPos[1])){
                targetCoordinates = path.pop().getId();
                return true;
            }
            /*if(j !=Integer.parseInt(pos[0])&&i !=Integer.parseInt(pos[1])){
                position.setValue(null);
                position = map.searchVertex(i + "," + j);
                position.setValue(this);
                
            }*/
        }
        catch (EmptyStackException e){
            System.out.println("i");
        }

        return false;
    }

    public int updatePath(){
        try{
            Vertex<String, Moveable> pPos1 = (Vertex<String, Moveable>) map.containerOf(target1);
            Vertex<String, Moveable> pPos2 = (Vertex<String, Moveable>) map.containerOf(target2);
            System.out.println("2-"+pPos2);
            System.out.println("1-"+pPos1);
            Stack<Vertex<String,Moveable>> path1 = null;
            Stack<Vertex<String,Moveable>> path2 = null;
            if(pPos1!=null) path1 = (Stack<Vertex<String, Moveable>>) map.dijktraPath(position.getId(), pPos1.getId());
            if(pPos2!=null) path2 = (Stack<Vertex<String, Moveable>>) map.dijktraPath(position.getId(), pPos2.getId());
            System.out.println("N");
            if(pPos2==null&&pPos1!=null||pPos2!=null&&pPos1!=null&&path1.size()<path2.size()&&goal!=pPos1){
                path.clear();
                System.out.println("+" + pPos1);
                goal = pPos1;
                path = path1;
                System.out.println(path);
                targetCoordinates = path.pop().getId();
            }
            else if (pPos2!=null&&pPos1==null||pPos2!=null&&pPos1!=null&&path1.size()>=path2.size()&&goal!=pPos2){
                path.clear();
                goal = pPos2;
                path = path2;
                targetCoordinates = path.pop().getId();
            }
            return path.size();
        }
        catch (NullPointerException e){
            return 0;
        }
    }

    public Player getTarget1() {
        return target1;
    }

    public void setTarget1(Player target1) {
        this.target1 = target1;
    }

    public Player getTarget2() {
        return target2;
    }

    public void setTarget2(Player target2) {
        this.target2 = target2;
    }

    public void setMap(ListGraph<String, Moveable> map) {
        this.map = map;
    }
    public void setPosition(Vertex<String, Moveable> position) {
        this.position = position;
    }
    public static Enemy getInstance() {
        return instance;
    }
    public Stack<Vertex<String, Moveable>> getPath() {
        return path;
    }
    public Vertex<String, Moveable> getPosition() {
        return position;
    }

    public Graph<String, Moveable> getMap() {
        return map;
    }
    public String getTargetCoordinates() {
        return targetCoordinates;
    }

    public boolean clearShot(){
        boolean clear = true;
        Vertex<String,Moveable> actCheck = position;
        int[] last = new int[2];
        for(Vertex<String,Moveable> i : path){
            String[] p1 = actCheck.getId().split(",");
            String[] p2 = i.getId().split(",");
            int difY = Integer.parseInt(p2[0])-Integer.parseInt(p1[0]);
            int difX = Integer.parseInt(p2[1])-Integer.parseInt(p1[1]);
            if(actCheck!=position){
                clear = last[0]==difY&&last[1]==difX;
                if(!clear) break;
            }
            last[0] = difY;
            last[1] = difX;
            actCheck = i;
        }
        return clear;
    }

    @Override
    public void murder() {
        position.setValue(null);
        map = null;
    }
}
