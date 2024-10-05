package JavaLearn.tankgame;

public class Bomb {
    int x;
    int y;
    int life=10;//生命周期
    boolean isLive=true;//生命状态

    public Bomb(int x, int y) {
        this.x = x;
        this.y = y;
    }

    //减少生命值方法
    public  void lifeDown(){

        //配合出现的爆炸效果
        if(life>0){
            life--;
        }else {
            isLive=false;
        }
    }
}
