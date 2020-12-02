package com.xnj.tank;

import com.xnj.tank.element.Tank;
import com.xnj.tank.net.Client;
import com.xnj.tank.net.TankStartMovingMsg;
import com.xnj.tank.net.TankStopMsg;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.*;

import static com.xnj.tank.element.Dir.*;

/**
 * @author chen xuanyi
 * @create 2020-11-05 15:26
 */
public class TankFrame extends Frame {

    GameModel gm = GameModel.getInstance();

    //将游戏界面抽话出来
    public static final int GAME_WIDTH = PropertyMgr.getInt("gameWidth"), GAME_HEIGHT = PropertyMgr.getInt("gameHeight");


    private TankFrame() throws HeadlessException {
        //设置窗口大小
        this.setSize(GAME_WIDTH, GAME_HEIGHT);
        //设置窗口是否可以随便改变大小
        this.setResizable(false);
        //设置标题
        this.setTitle("tank war");
        //设置窗口可见
//        this.setVisible(true);

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


    private static class TankFrameLoader {
        private static final TankFrame INSTANCE = new TankFrame();
    }

    public static TankFrame getInstance() {
        return TankFrameLoader.INSTANCE;
    }

    //使用双缓冲解决闪烁问题
    Image offScreenImage = null;

    @Override
    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        //重新画背景
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);

        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    @Override
    public void paint(Graphics g) {
        gm.paint(g);
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
            switch (key) {
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
                case KeyEvent.VK_SPACE:
                    gm.getMainTank().fire();
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
            switch (key) {
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
            if (!bL && !bU && !bR && !bD) {
                gm.getMainTank().setMoving(false);
                Client.getInstance().send(new TankStopMsg(gm.getMainTank()));
            } else {
                if (bL) {
                    gm.getMainTank().setDir(LEFT);
                }
                if (bU) {
                    gm.getMainTank().setDir(UP);
                }
                if (bR) {
                    gm.getMainTank().setDir(RIGHT);
                }
                if (bD) {
                    gm.getMainTank().setDir(DOWN);
                }
                if (!gm.getMainTank().isMoving()) {

                    Client.getInstance().send(new TankStartMovingMsg(gm.getMainTank()));
                }
                gm.getMainTank().setMoving(true);

            }
        }

    }

}
