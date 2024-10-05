package JavaLearn.tankgame;

public class Shot implements Runnable{
    int x;
    int y;
    int direction=0;
    int speed=5;
    boolean isLive=true;//子弹是否存活

    public Shot(int x, int y, int direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    @Override
    public void run() {
        while(true){
            //线程休眠
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            //根据方向改变x,y的坐标
            switch (direction){

                case 0://向上
                    y-=speed;
                    break;
                case 1://向右
                    x+=speed;
                    break;
                case 2://向下
                    y+=speed;
                    break;
                case 3://向左
                    x-=speed;
                    break;

            }

            System.out.println("测试坐标"+x+","+y);

            //子弹超出边界则销毁
            //子弹击中敌方坦克也应该进行销毁
            if(!(x>=0&&x<=1000&&y>=0&&y<=750&&isLive==true)){
                isLive=false;
                break;
            }

        }
    }
}
