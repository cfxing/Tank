package com.xnj.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static com.xnj.tank.Dir.*;

/**
 * @author chen xuanyi
 * @create 2020-11-05 15:26
 */
public class TankFrame extends Frame {

    Tank tank = new Tank(200,200,Dir.DOWN);


    public TankFrame() throws HeadlessException {
        //设置窗口大小
        this.setSize(800, 600);
        //设置窗口是否可以随便改变大小
        this.setResizable(false);
        //设置标题
        this.setTitle("tank war");
        //设置窗口可见
        this.setVisible(true);

        this.addKeyListener(new MyKeyListener());

        //添加windows  的监听器
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                //0 表示正常退出
                System.exit(0);
            }
        });
    }

    @Override
    public void paint(Graphics g) {
//        System.out.println("print");
//        g.fillRect(x,y,50,50);
//        x += 10;
//        y += 10;
        tank.paint(g);



    }

    class MyKeyListener extends KeyAdapter {

        // 表示键是否被按下
        boolean bL = false;
        boolean bU = false;
        boolean bR = false;
        boolean bD = false;


        //当一个键被按下
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key){
                case KeyEvent.VK_LEFT:
//                    x -= 10;
                    bL = true;
                    break;
                case KeyEvent.VK_UP:
//                    y -= 10;
                    bU = true;
                    break;
                case KeyEvent.VK_RIGHT:
//                    x += 10;
                    bR = true;
                    break;
                case KeyEvent.VK_DOWN:
//                    y += 10;
                    bD = true;
                    break;
                default:
                    break;
            }
//            x += 30;
//            repaint();

            setMainTankDir();
        }

        //当一个键被松开
        @Override
        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key){
                case KeyEvent.VK_LEFT:
                    bL = false;
                    break;
                case KeyEvent.VK_UP:
                    bU = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = false;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = false;
                    break;
                default:
                    break;
            }
            setMainTankDir();
        }

        private void setMainTankDir() {
            if (bL) {
                tank.setDir(LEFT);
            }
            if (bU) {
                tank.setDir(UP);
            }
            if (bR) {
                tank.setDir(RIGHT);
            }
            if (bD) {
                tank.setDir(DOWN);
            }
        }

    }
}
