package JavaLearn.tankgame;



import java.io.*;
import java.util.Vector;

//用于记录信息和文件交互
public class Recorder {

    //定义变量，记录我方击毁的敌方坦克数量
    private static int allEnemyTankNum=0;
    //定义IO对象
    private static BufferedWriter bw=null;
    private static BufferedReader br=null;
    private static String recordFilePath="D:\\myRecord.txt";
    //增加集合变量，指向MyPanel中的敌方坦克Vector
    private static Vector<EnemyTank> enemyTanks=null;
    private static Vector<Node> nodes=new Vector<>();

    public static void setEnemyTanks(Vector<EnemyTank> enemyTanks) {
        Recorder.enemyTanks = enemyTanks;
    }

    //增加方法，用于读取Node中的信息，用于恢复中止的游戏信息
    public static Vector<Node> getNodesAndEnemyTanksRes(){

        try {
            br=new BufferedReader(new FileReader(recordFilePath));
            allEnemyTankNum=Integer.parseInt(br.readLine());
            //循环读取文件，生成nodes集合
            String line="";
            while ((line=br.readLine())!=null){

                String[] xydirection=line.split(" ");
                Node node = new Node(Integer.parseInt(xydirection[0]), Integer.parseInt(xydirection[1]), Integer.parseInt(xydirection[2]));
                nodes.add(node);//放入到nodes集合
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (br!=null){
                try {
                    br.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        return nodes;
    }

    //增加方法，使得能使成绩存盘
    public static void keepRecord() {

        try {
            bw =new BufferedWriter(new FileWriter(recordFilePath));
            bw.write(allEnemyTankNum+"\r\n");
            //遍历敌方坦克的Vector，然后根据情况保存坐标
            for (int i=0;i<enemyTanks.size();i++){
                //取出敌方坦克
                EnemyTank enemyTank=enemyTanks.get(i);
                if (enemyTank.isLive){
                    //保存该敌方坦克的信息
                    String record=enemyTank.getX()+" "+enemyTank.getY()+" "+enemyTank.getDirection();
                    //写入到文件
                    bw.write(record+"");
                    bw.newLine();
                }
            }
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                bw.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


    }

    public static int getAllEnemyTankNum() {
        return allEnemyTankNum;
    }

    public static void setAllEnemyTankNum(int allEnemyTankNum) {
        Recorder.allEnemyTankNum = allEnemyTankNum;
    }

    //当己方坦克击毁一个敌方坦克，就应当对allEnemyTankNum增加1
    public static void addAllEnemyTankNum(){
        Recorder.allEnemyTankNum++;
    }

}
