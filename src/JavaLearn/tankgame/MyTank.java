package JavaLearn.tankgame;

import java.util.Vector;

public class MyTank extends Tank{
    Shot shot=null;
    Vector<Shot> shots=new Vector<>();
    public MyTank(int x, int y) {
        super(x, y);
    }

    //对己方坦克进行射击设定
    public void shotEnemy(){
        //设置子弹数量限制
        if (shots.size()==5){
            return;
        }

        //创建Shot对象，根据当前己方坦克的坐标来创建Shot对象
        switch (getDirection()){
            case 0:
                shot=new Shot(getX()+20,getY(),getDirection());
                break;
            case 1:
                shot=new Shot(getX()+60,getY()+20,getDirection());
                break;
            case 2:
                shot=new Shot(getX()+20,getY()+60,getDirection());
                break;
            case 3:
                shot=new Shot(getX(),getY()+20,getDirection());
                break;
        }
        new Thread(shot).start();
        //把新创建的shot放入到shots集合中
        shots.add(shot);
    }
}
