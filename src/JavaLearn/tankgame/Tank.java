package JavaLearn.tankgame;

public class Tank {
    private int x;
    private int y;
    private int direction;
    private int speed;
    boolean isLive=true;
    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    //设置坦克移动方法
    public void moveUp(){

        y -= speed;

    }
    public void moveRight(){

        x += speed;

    }
    public void moveDown(){

        y += speed;

    }
    public void moveLeft(){

        x -= speed;

    }


    public Tank(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }


}
