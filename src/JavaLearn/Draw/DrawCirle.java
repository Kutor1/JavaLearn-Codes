package JavaLearn.Draw;

import javax.swing.*;
import java.awt.*;
//JFrame框架
@SuppressWarnings({"all"})
public class DrawCirle extends JFrame {
    //初始化画板
    private Mypanel mp=null;
    public static void main(String[] args) {
        new DrawCirle();
    }
    public  DrawCirle(){
        mp=new Mypanel();//将画板赋值给mp
        this.add(mp);//在使用方法时，在框架内加入画板
        this.setSize(400,300);//设置框架大小
        this.setDefaultCloseOperation((JFrame.EXIT_ON_CLOSE));//设置关闭窗口即退出程序
        this.setVisible(true);//框架可视化
    }
}
//制作画板
class Mypanel extends JPanel{
    //重写paint方法，paint方法在第一次显示时自动调用
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawOval(0,0,100,100);
    }
}
