package game.model;

public interface Moveable {
    //public boolean move();
    public boolean move(double posX, double posY);
    public Object getPosition();
    public Object getMap();
    public void murder();
}
