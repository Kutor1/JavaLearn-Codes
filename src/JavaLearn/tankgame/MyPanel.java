package JavaLearn.tankgame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

//为了让子弹能不断重绘，必须让Panel实现线程Runnable接口
public class MyPanel extends JPanel implements KeyListener,Runnable {
    //定义己方坦克
    MyTank hero = null;
    //定义敌方坦克
    Vector<EnemyTank> enemyTanks=new Vector<>();
    //定义一个Vector存放Node
    Vector<Node> nodes=new Vector<>();
    int enemyTankSize=3;
    //定义一个Vector，用于存放爆炸效果
    //当子弹击中坦克时，加入一个Bomb对象到Vector(bombs)中
    Vector<Bomb> bombs=new Vector<>();

    Image image1=null;
    Image image2=null;
    Image image3=null;



    //无参构造方法
    public MyPanel(String key) {
        //将MyPanel对象的enemyTanks设置给Recorder
        Recorder.setEnemyTanks(enemyTanks);

        //初始化己方坦克
        hero = new MyTank(100, 100);
        hero.setSpeed(20);

        //根据传入的key，判断需要进行游戏的方式
        switch (key) {
            case "1":
                //初始化敌方坦克
                for (int i = 0; i < enemyTankSize; i++) {
                    EnemyTank enemyTank = new EnemyTank((100 * (i + 1)), 0);
                    enemyTank.setEnemyTanks(enemyTanks);
                    //设置敌方坦克方向
                    enemyTank.setDirection(1);
                    //设置敌方坦克的移动速度
                    enemyTank.setSpeed(2);
                    //给敌方坦克加入一颗子弹
                    //Shot shot=new Shot(enemyTank.getX()+25,enemyTank.getY()+40,enemyTank.getDirection());
                    //加入到enemyTank的vector成员
                    //enemyTank.shots.add(shot);
                    //启动敌方坦克(线程)
                    new Thread(enemyTank).start();
                    //启动子弹(线程)
                    //new Thread(shot).start();
                    //将初始化完成的敌方坦克放入到敌方坦克Vector中
                    enemyTanks.add(enemyTank);
                }
                break;
            case "2"://继续中止的游戏，恢复游戏信息
                //读取文件数据恢复中止的游戏
                nodes=Recorder.getNodesAndEnemyTanksRes();

                //初始化敌方坦克
                for (int i = 0; i < nodes.size(); i++) {
                    Node node=nodes.get(i);
                    EnemyTank enemyTank = new EnemyTank(node.getX(), node.getY());
                    enemyTank.setEnemyTanks(enemyTanks);
                    //设置敌方坦克方向
                    enemyTank.setDirection(node.getDirection());
                    //设置敌方坦克的移动速度
                    enemyTank.setSpeed(2);
                    //给敌方坦克加入一颗子弹
                    //Shot shot=new Shot(enemyTank.getX()+25,enemyTank.getY()+40,enemyTank.getDirection());
                    //加入到enemyTank的vector成员
                    //enemyTank.shots.add(shot);
                    //启动敌方坦克(线程)
                    new Thread(enemyTank).start();
                    //启动子弹(线程)
                    //new Thread(shot).start();
                    //将初始化完成的敌方坦克放入到敌方坦克Vector中
                    enemyTanks.add(enemyTank);
                }
                break;
        }
        //初始化爆炸图片对象
        //备注：三张图片暂时一样，后期需要根据Bomb.life来处理不同生命值所对应的图片
        image1=Toolkit.getDefaultToolkit().getImage(MyPanel.class.getResource("/Bomb.gif"));
        image2=null;
        image3=null;
    }


    //编写封装的战绩显示功能
    public void showInfo(Graphics g){

        //画出玩家的总成绩
        g.setColor(Color.BLACK);
        Font font= new Font("宋体",Font.BOLD,20);
        g.setFont(font);

        g.drawString("已累积击毁的敌方坦克：",1020,30);
        g.drawString(Recorder.getAllEnemyTankNum()+"",1080,90);
        drawEnemyTank(1020,60,g,0);
    }

    //绘制方法(实现在Panel中绘制)
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.WHITE);
        g.fillRect(0,0,1000,750);//填充矩形

        //使用显示战绩方法
        showInfo(g);

        //画出坦克（封装的方法）
        if (hero.isLive) {
            drawTank(hero.getX(), hero.getY(), g, hero.getDirection());
        }
        //画出子弹(单颗子弹)
        //判断子弹是否出界(Premise)
//        if(hero.shot!=null&& hero.shot.isLive){
//            g.draw3DRect(hero.shot.x,hero.shot.y,2,2,false);
//        }


        //发射多发子弹时，遍历多个子弹
        for (int i=0;i<hero.shots.size();i++){
            //取出子弹
            Shot shot=hero.shots.get(i);
            //绘制子弹
            if (shot!=null&&shot.isLive) {
                g.draw3DRect(shot.x, shot.y, 2, 2, false);
            }else {
                hero.shots.remove(shot);
            }
        }

        //如果bombs集合中有爆炸对象，则画出爆炸效果
        for (int i=0;i<bombs.size();i++){

            //取出爆炸
            Bomb bomb=bombs.get(i);
            //根据当前这个bomb对象的life值去画出对应的图片；
            if (bomb.life>6){
                g.drawImage(image1, bomb.x, bomb.y, 60,60,this);
            } else if (bomb.life>3) {
                g.drawImage(image1, bomb.x, bomb.y, 60,60,this);
            }else {
                g.drawImage(image1, bomb.x, bomb.y, 60,60,this);
            }

            //让爆炸的生命值life减少
            bomb.lifeDown();

            //如果bomb.life为0，则删除该bomb
            if(bomb.life==0){
                bombs.remove(bomb);
            }
        }

        //画出敌方坦克，使用遍历方法，使用size()方法，获取frame内剩余敌方坦克
        for(int i=0;i<enemyTanks.size();i++){
            //取出坦克
            EnemyTank enemyTank = enemyTanks.get(i);

            //使用封装的drawTank方法绘制敌方坦克
            if (enemyTank.isLive) {//利用isLive判断敌方坦克是否存活，若存活则进行绘制

                drawEnemyTank(enemyTank.getX(), enemyTank.getY(), g, enemyTank.getDirection());
                //画出敌方坦克的子弹
                for (int j = 0; j < enemyTank.shots.size(); j++) {
                    //取出子弹
                    Shot shot = enemyTank.shots.get(j);
                    //绘制子弹，(Premise)判断子弹的isLive
                    if (shot.isLive) {
                        g.draw3DRect(shot.x, shot.y, 2, 2, false);
                    } else {
                        //从容器vector中移除该子弹
                        enemyTank.shots.remove(shot);
                    }
                }


            }
        }
    }

    //封装的绘制己方坦克方法
    public void drawTank(int x,int y,Graphics g,int direction){

        //设置坦克颜色
        g.setColor(Color.CYAN);

        //根据坦克方向，绘制坦克
        switch (direction){
            case 0://表示向上
                //绘制坦克
                g.fill3DRect(x,y,10,60,false);
                g.fill3DRect(x+30, y,10,60,false );
                g.fill3DRect(x+10,y+10,20,40,false);
                g.fillOval(x+10,y+20,20,20);
                g.drawLine(x+20,y+30,x+20,y);
                break;
            case 1://表示向右
                g.fill3DRect(x,y,60,10,false);
                g.fill3DRect(x, y+30,60,10,false );
                g.fill3DRect(x+10,y+10,40,20,false);
                g.fillOval(x+20,y+10,20,20);
                g.drawLine(x+30,y+20,x+60,y+20);
                break;
            case 2://表示向下
                g.fill3DRect(x,y,10,60,false);
                g.fill3DRect(x+30, y,10,60,false );
                g.fill3DRect(x+10,y+10,20,40,false);
                g.fillOval(x+10,y+20,20,20);
                g.drawLine(x+20,y+30,x+20,y+60);
                break;
            case 3://表示向左
                g.fill3DRect(x,y,60,10,false);
                g.fill3DRect(x, y+30,60,10,false );
                g.fill3DRect(x+10,y+10,40,20,false);
                g.fillOval(x+20,y+10,20,20);
                g.drawLine(x+30,y+20,x,y+20);
                break;
            default:
                System.out.println("Tank no exist");
        }
    }


    //封装的绘制敌方坦克方法
    public void drawEnemyTank(int x,int y,Graphics g,int direction){

        //设置敌方坦克颜色
        g.setColor(Color.RED);

        //根据坦克方向，绘制坦克
        switch (direction){
            case 0://表示向上
                //绘制坦克
                g.fill3DRect(x,y,15,40,false);
                g.fill3DRect(x+35, y,15,40,false );
                g.fill3DRect(x+15,y+5,20,30,false);
                g.fillOval(x+15,y+10,20,20);
                g.drawLine(x+25,y+20,x+25,y);
                break;
            case 1://表示向右
                g.fill3DRect(x,y,40,15,false);
                g.fill3DRect(x, y+35,40,15,false );
                g.fill3DRect(x+5,y+15,30,20,false);
                g.fillOval(x+10,y+15,20,20);
                g.drawLine(x+20,y+25,x+50,y+25);
                break;
            case 2://表示向下
                g.fill3DRect(x,y,15,40,false);
                g.fill3DRect(x+35, y,15,40,false );
                g.fill3DRect(x+15,y+5,20,30,false);
                g.fillOval(x+15,y+10,20,20);
                g.drawLine(x+25,y+20,x+25,y+50);
                break;
            case 3://表示向左
                g.fill3DRect(x,y,40,15,false);
                g.fill3DRect(x, y+35,40,15,false );
                g.fill3DRect(x+5,y+15,30,20,false);
                g.fillOval(x+10,y+15,20,20);
                g.drawLine(x+20,y+25,x-10,y+25);
                break;
            default:
                System.out.println("Tank no exist");
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    //处理按键触发
    @Override
    public void keyPressed(KeyEvent e) {
        //根据按键改变坦克的方向
        if(e.getKeyCode()==KeyEvent.VK_W) {
            hero.setDirection(0);
            if (hero.getY()>0) {
                hero.moveUp();
            }

        } else if (e.getKeyCode()==KeyEvent.VK_D) {
            hero.setDirection(1);
            if (hero.getX()+60<1000) {
                hero.moveRight();
            }

        } else if (e.getKeyCode()==KeyEvent.VK_S) {
            hero.setDirection(2);
            if (hero.getY()+60<750) {
                hero.moveDown();
            }
        } else if (e.getKeyCode()==KeyEvent.VK_A) {
            hero.setDirection(3);
            if (hero.getX()>0) {
                hero.moveLeft();
            }
        }

        //如果用户按下J键则发射子弹(单发子弹)
//        if(e.getKeyCode()==KeyEvent.VK_J){
//            if (hero.shot==null||!hero.shot.isLive) {
//                hero.shotEnemy();
//            }
//        }

        if(e.getKeyCode()==KeyEvent.VK_J){

                hero.shotEnemy();

        }

        //让面板重绘
        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void run() {//每隔100毫秒自动重绘Panel，刷新绘图区域

        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            //每隔100毫秒刷新Panel的同时，刷新子弹是否击中敌方坦克
            hitEnemyTank();

            //判断敌方坦克是否击中己方坦克
            hitMyTank();

            this.repaint();

        }
    }

    //
    public void hitMyTank(){
        //遍历敌方坦克
        for (int i=0;i<enemyTanks.size();i++){
            //取出敌方坦克
            EnemyTank enemyTank=enemyTanks.get(i);

            for (int j=0;j<enemyTank.shots.size();j++) {

                //取出每个敌方坦克的子弹集合，传入hitTank方法
                Shot shot = enemyTank.shots.get(j);
                hitTank(shot, hero);
            }
        }
    }

    public void hitEnemyTank(){
        //遍历己方子弹集合
        for (int i=0;i<hero.shots.size();i++){

            Shot shot=hero.shots.get(i);

            if(shot!=null&&shot.isLive){//当己方还有子弹时

                //遍历敌方坦克传入hitTank方法判断子弹是否击中
                for(int j=0;j<enemyTanks.size();j++){
                    EnemyTank enemyTank=enemyTanks.get(j);
                    hitTank(shot,enemyTank);
                }

            }
        }
    }



    //对命中坦克动作处理，hitTank方法(子弹判断，爆炸效果)
    public void hitTank(Shot s, Tank enemyTank){

            //判断区域 命中坦克
            switch (enemyTank.getDirection()) {
                case 0://当敌方坦克向上或者向下时，判断子弹是否击中敌方坦克区域
                case 2:
                    if (s.x > enemyTank.getX() && s.x < enemyTank.getX() + 50
                            && s.y > enemyTank.getY() && s.y < enemyTank.getY() + 40) {
                        s.isLive = false;//子弹状态改变
                        enemyTank.isLive = false;//改变敌方坦克状态
                        //在paint方法中，依据isLive属性判断是否需要绘制该敌方坦克


                        //将isLive为0的敌方坦克在enemyTanks集合中移除
                        enemyTanks.remove(enemyTank);

                        //当己方坦克击毁敌方坦克时，应该记录成绩
                        if(enemyTank instanceof EnemyTank){
                            Recorder.addAllEnemyTankNum();
                        }

                        //创建Bomb对象，加入到bombs中
                        Bomb bomb = new Bomb(enemyTank.getX(), enemyTank.getY());
                        bombs.add(bomb);
                    }
                    break;
                case 1://当敌方坦克向左向右时
                case 3:
                    if (s.x > enemyTank.getX() && s.x < enemyTank.getX() + 40
                            && s.y > enemyTank.getY() && s.y < enemyTank.getY() + 50) {
                        s.isLive = false;
                        enemyTank.isLive = false;//在paint方法中，依据isLive属性判断是否需要绘制该敌方坦克


                        //将isLive为0的敌方坦克在enemyTanks集合中移除
                        enemyTanks.remove(enemyTank);

                        //当己方坦克击毁敌方坦克时，应该记录成绩
                        if(enemyTank instanceof EnemyTank){
                            Recorder.addAllEnemyTankNum();
                        }

                        //创建Bomb对象，加入到bombs中
                        Bomb bomb = new Bomb(enemyTank.getX(), enemyTank.getY());
                        bombs.add(bomb);
                    }
            }
        }
}






