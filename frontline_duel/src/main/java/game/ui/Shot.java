package game.ui;


public class Shot extends Thread{

    private Vector shotPosition;

    private Vector shotDirection;

    public Shot(double x, double y, double alpha, double beta){
        shotPosition = new Vector(x,y);
        shotDirection = new Vector(alpha,beta);
    }


    @Override
    public void run() {
        shotPosition.x += shotDirection.x;
        shotPosition.y += shotDirection.y;
    }

    public Vector getShotPosition() {
        return shotPosition;
    }
}
