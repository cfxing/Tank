package com.xnj.tank;

import com.xnj.abstractfactory.BaseExplode;
import com.xnj.abstractfactory.DefaultFactory;
import com.xnj.abstractfactory.GameFactory;
import com.xnj.manage.PropertyMgr;
import com.xnj.strategy.DefaultFireStrategy;
import com.xnj.strategy.FireStrategy;
import com.xnj.strategy.FourDirFireStrategy;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import static com.xnj.tank.Dir.*;

/**
 * @author chen xuanyi
 * @create 2020-11-05 15:26
 */
public class TankFrame extends Frame {

    Tank tank = new Tank(200,400,Dir.UP, Group.GOOD, this);
    List<Bullet> bullets = new ArrayList<Bullet>();
//    Bullet b = new Bullet(300, 300, DOWN);

    List<Tank> tanks = new ArrayList<Tank>();

    //画爆炸
//    Explode e = new Explode(100, 100, this);

    //爆炸集合
//    List<Explode> explodes = new ArrayList<>();
    List<BaseExplode> explodes = new ArrayList<>();

    //初始化工厂
    GameFactory gf = new DefaultFactory();
    //将游戏界面抽话出来
    static final int GAME_WIDTH = PropertyMgr.getInt("gameWidth"), GAME_HEIGHT = PropertyMgr.getInt("gameHeight");


    public TankFrame() throws HeadlessException {
        //设置窗口大小
        this.setSize(GAME_WIDTH, GAME_HEIGHT);
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

    //使用双缓冲解决闪烁问题
    Image offScreenImage = null;
    @Override
    public void update(Graphics g){
        if (offScreenImage == null){
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
//        System.out.println("print");
//        g.fillRect(x,y,50,50);
//        x += 10;
//        y += 10;

        //修改颜色
        //保存之前颜色
        Color c = g.getColor();
        //修改颜色
        g.setColor(Color.WHITE);
        g.drawString("子弹的数量" + bullets.size(), 10, 60);
        g.drawString("敌人的数量" + tanks.size(), 10, 80);
        g.drawString("爆炸的数量" + explodes.size(), 10, 100);

        //恢复原来的颜色
        g.setColor(c);

        tank.paint(g);

//        b.paint(g);

        //此方法删除子弹会报错
//        for (Bullet b: bullets){
//            b.paint(g);
//        }

        for (int i = 0; i < bullets.size(); i++){
            bullets.get(i).paint(g);
        }

        //画坦克
        for (int i = 0 ; i < tanks.size(); i++){
            tanks.get(i).paint(g);
        }

        //碰撞检测
        for(int i = 0; i < bullets.size(); i++){
            for (int j = 0; j < tanks.size(); j++){

                bullets.get(i).collideWith(tanks.get(j));
            }
        }

        //画爆炸
//        e.paint(g);

        //爆炸集合
        for (int i = 0; i < explodes.size(); i++) {
            explodes.get(i).paint(g);
        }
        //删除子弹的另一种代码
//        for (Iterator<Bullet> it = bullets.iterator(); it.hasNext();){
//            Bullet b = it.next();
//            if (!b.live){
//                it.remove();
//            }
//        }


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
                case KeyEvent.VK_SPACE:
                    if (tanks.size() < 3){

                        tank.fire(FourDirFireStrategy.getInstance());
                    }else{

                    tank.fire(DefaultFireStrategy.getInstance());
                    }
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
            if (!bL && !bU && !bR && !bD){
                tank.setMoving(false);
            }else{
                tank.setMoving(true);

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
}
