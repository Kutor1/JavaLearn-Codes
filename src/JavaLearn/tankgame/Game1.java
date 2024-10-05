package JavaLearn.tankgame;

import javax.swing.*;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Scanner;

public class Game1 extends JFrame {
    MyPanel mp = null;
    static Scanner scanner=new Scanner(System.in);
    public static void main(String[] args) {

        Game1 game1 = new Game1();
    }

    public Game1() {
        System.out.println("请选择进行游戏的方式：1.开始新游戏 2.继续上局游戏");
        String key=scanner.next();
        mp = new MyPanel(key);
        //将mp放入Thread代理，将mp线程启动
        Thread thread = new Thread(mp);
        thread.start();
        this.add(mp);//把面板加入JFrame中
        this.setSize(1300, 750);
        this.addKeyListener(mp);//让JFrame监听key的动作使得画板移动
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        //在JFrame中，增加相应关闭窗口响应的处理
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Recorder.keepRecord();
                super.windowClosing(e);
            }
        });

    }
}