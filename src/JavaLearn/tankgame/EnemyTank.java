package JavaLearn.tankgame;

import java.util.Vector;

public class EnemyTank extends Tank implements Runnable{
    boolean isLive=true;
    Vector<Shot> shots=new Vector<>();
    //添加EnemyTank变量，使得每个敌方坦克都能遍历所有敌方坦克
    Vector<EnemyTank> enemyTanks=new Vector<>();
    public EnemyTank(int x, int y) {
        super(x, y);
    }


    //提供方法，能设置EnemyTank类内的enemyTanks属性
    public void setEnemyTanks(Vector<EnemyTank> enemyTanks) {
        this.enemyTanks = enemyTanks;
    }


    //方法，能判断当前敌方坦克是否与其他敌方坦克发生重叠
    public boolean isTouchEnemyTank(){

        //首先判断当前坦克的方向
        switch (this.getDirection()){
            case 0:
                //取出所有敌方坦克
                for (int i=0;i<enemyTanks.size();i++){
                    //从vector中取出一个敌方坦克
                    EnemyTank enemyTank=enemyTanks.get(i);
                    //首先，不和自己比较
                    if(enemyTank!=this){
                        //如果敌方坦克是向上或者向下


                        if (enemyTank.getDirection()==0||enemyTank.getDirection()==2){

                            //以当前坦克的左上角判断
                            if (this.getX()>=enemyTank.getX()
                                    &&this.getX()<=enemyTank.getX()+50
                                    &&this.getY()>=enemyTank.getY()
                                    &&this.getY()<= enemyTank.getY()+40){
                                return true;
                            }

                            //以当前坦克的右上角判断
                            if (this.getX()+50>=enemyTank.getX()
                                    &&this.getX()+50<=enemyTank.getX()+50
                                    &&this.getY()>=enemyTank.getY()
                                    &&this.getY()<= enemyTank.getY()+40){
                                return true;
                            }
                        }



                        //如果敌方坦克是向左或者向右
                        if (enemyTank.getDirection()==1||enemyTank.getDirection()==3){

                            //以当前坦克的左上角判断
                            if (this.getX()>=enemyTank.getX()
                                    &&this.getX()<=enemyTank.getX()+40
                                    &&this.getY()>=enemyTank.getY()
                                    &&this.getY()<= enemyTank.getY()+50){
                                return true;
                            }

                            //以当前坦克的右上角判断
                            if (this.getX()+50>=enemyTank.getX()
                                    &&this.getX()+50<=enemyTank.getX()+40
                                    &&this.getY()>=enemyTank.getY()
                                    &&this.getY()<= enemyTank.getY()+50){
                                return true;
                            }
                        }
                    }
                }
                break;
            case 1:
                //取出所有敌方坦克
                for (int i=0;i<enemyTanks.size();i++){
                    //从vector中取出一个敌方坦克
                    EnemyTank enemyTank=enemyTanks.get(i);
                    //首先，不和自己比较
                    if(enemyTank!=this){
                        //如果敌方坦克是向上或者向下


                        if (enemyTank.getDirection()==0||enemyTank.getDirection()==2){

                            //以当前坦克的右上角判断
                            if (this.getX()+40>=enemyTank.getX()
                                    &&this.getX()+40<=enemyTank.getX()+50
                                    &&this.getY()>=enemyTank.getY()
                                    &&this.getY()<= enemyTank.getY()+40){
                                return true;
                            }

                            //以当前坦克的右下角判断
                            if (this.getX()+40>=enemyTank.getX()
                                    &&this.getX()+40<=enemyTank.getX()+50
                                    &&this.getY()+50>=enemyTank.getY()
                                    &&this.getY()+50<= enemyTank.getY()+40){
                                return true;
                            }
                        }



                        //如果敌方坦克是向左或者向右
                        if (enemyTank.getDirection()==1||enemyTank.getDirection()==3){

                            //以当前坦克的右上角判断
                            if (this.getX()+40>=enemyTank.getX()
                                    &&this.getX()+40<=enemyTank.getX()+40
                                    &&this.getY()>=enemyTank.getY()
                                    &&this.getY()<= enemyTank.getY()+50){
                                return true;
                            }

                            //以当前坦克的右下角判断
                            if (this.getX()+40>=enemyTank.getX()
                                    &&this.getX()+40<=enemyTank.getX()+40
                                    &&this.getY()+50>=enemyTank.getY()
                                    &&this.getY()+50<= enemyTank.getY()+50){
                                return true;
                            }
                        }
                    }
                }
                break;
            case 2:
                //取出所有敌方坦克
                for (int i=0;i<enemyTanks.size();i++){
                    //从vector中取出一个敌方坦克
                    EnemyTank enemyTank=enemyTanks.get(i);
                    //首先，不和自己比较
                    if(enemyTank!=this){
                        //如果敌方坦克是向上或者向下


                        if (enemyTank.getDirection()==0||enemyTank.getDirection()==2){

                            //以当前坦克的左下角判断
                            if (this.getX()>=enemyTank.getX()
                                    &&this.getX()<=enemyTank.getX()+50
                                    &&this.getY()+40>=enemyTank.getY()
                                    &&this.getY()+40<= enemyTank.getY()+40){
                                return true;
                            }

                            //以当前坦克的右下角判断
                            if (this.getX()+50>=enemyTank.getX()
                                    &&this.getX()+50<=enemyTank.getX()+50
                                    &&this.getY()+40>=enemyTank.getY()
                                    &&this.getY()+40<= enemyTank.getY()+40){
                                return true;
                            }
                        }



                        //如果敌方坦克是向左或者向右
                        if (enemyTank.getDirection()==1||enemyTank.getDirection()==3){

                            //以当前坦克的左下角判断
                            if (this.getX()>=enemyTank.getX()
                                    &&this.getX()<=enemyTank.getX()+40
                                    &&this.getY()+40>=enemyTank.getY()
                                    &&this.getY()+40<= enemyTank.getY()+50){
                                return true;
                            }

                            //以当前坦克的右下角判断
                            if (this.getX()+50>=enemyTank.getX()
                                    &&this.getX()+50<=enemyTank.getX()+40
                                    &&this.getY()+40>=enemyTank.getY()
                                    &&this.getY()+40<= enemyTank.getY()+50){
                                return true;
                            }
                        }
                    }
                }
                break;
            case 3:
                //取出所有敌方坦克
                for (int i=0;i<enemyTanks.size();i++){
                    //从vector中取出一个敌方坦克
                    EnemyTank enemyTank=enemyTanks.get(i);
                    //首先，不和自己比较
                    if(enemyTank!=this){
                        //如果敌方坦克是向上或者向下


                        if (enemyTank.getDirection()==0||enemyTank.getDirection()==2){

                            //以当前坦克的左上角判断
                            if (this.getX()>=enemyTank.getX()
                                    &&this.getX()<=enemyTank.getX()+50
                                    &&this.getY()>=enemyTank.getY()
                                    &&this.getY()<= enemyTank.getY()+40){
                                return true;
                            }

                            //以当前坦克的左下角判断
                            if (this.getX()>=enemyTank.getX()
                                    &&this.getX()<=enemyTank.getX()+50
                                    &&this.getY()+50>=enemyTank.getY()
                                    &&this.getY()+50<= enemyTank.getY()+40){
                                return true;
                            }
                        }



                        //如果敌方坦克是向左或者向右
                        if (enemyTank.getDirection()==1||enemyTank.getDirection()==3){

                            //以当前坦克的左上角判断
                            if (this.getX()>=enemyTank.getX()
                                    &&this.getX()<=enemyTank.getX()+40
                                    &&this.getY()>=enemyTank.getY()
                                    &&this.getY()<= enemyTank.getY()+50){
                                return true;
                            }

                            //以当前坦克的左下角判断
                            if (this.getX()>=enemyTank.getX()
                                    &&this.getX()<=enemyTank.getX()+40
                                    &&this.getY()+50>=enemyTank.getY()
                                    &&this.getY()+50<= enemyTank.getY()+50){
                                return true;
                            }
                        }
                    }
                }
                break;
        }
        return false;
    }

    @Override
    public void run() {
        while (true){

            //设置敌方坦克子弹发射
            //敌方坦克状态存活且子弹存活量为0
            if (isLive&&shots.size()==0){
                Shot shot=null;
                switch (getDirection()) {
                    case 0:
                        shot = new Shot(getX()+25, getY(), getDirection());
                        break;
                    case 1:
                        shot=new Shot(getX()+40,getY()+25,getDirection());
                        break;
                    case 2:
                        shot=new Shot(getX()+25,getY()+40,getDirection());
                        break;
                    case 3:
                        shot=new Shot(getX(),getY()+25,getDirection());
                        break;
                }
                new Thread(shot).start();
                shots.add(shot);
            }

            //根据坦克的方向来继续移动
            switch (getDirection()){
                case 0://上
                    for (int i=0;i<30;i++){
                        if (getY()>0&&!isTouchEnemyTank()) {
                            moveUp();
                        }
                        /* 移动后休眠 */
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }

                    break;
                case 1://右
                    for (int i=0;i<30;i++){
                        if (getX()+40<1000&&!isTouchEnemyTank()) {
                            moveRight();
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }

                    break;
                case 2://下
                    for (int i=0;i<30;i++) {
                        if (getY()+40<750&&!isTouchEnemyTank()) {
                            moveDown();
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }

                    break;
                case 3://左
                    for (int i=0;i<30;i++) {
                            if (getX()>0&&!isTouchEnemyTank()) {
                                moveLeft();
                            }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }

                    break;
            }



            //随机改变坦克方向
            setDirection((int) (Math.random()*4));//利用random()*4取得0到4不包括4之间的整数

            //被击中时
            if(!isLive){
                break;
            }
        }
    }
}
