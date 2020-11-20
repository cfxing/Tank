package com.xnj.tank;

import javax.xml.crypto.KeySelector;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 门面模型 Facade
 * 应该为单例
 * @author chen xuanyi
 * @create 2020-11-20 14:58
 */
public class GameModel {

    private GameModel(){
        int initTankCount = PropertyMgr.getInt("initTankCount");
        //初始化敌方坦克
        for (int i = 0; i < initTankCount; i++){
            tanks.add(new Tank(50 + i * 80, 200, Dir.DOWN, Group.BAD,this));
        }
    }

    private static class GameModelLoder{
        private static final GameModel INSTANCE = new GameModel();
    }

    public static GameModel getInstance(){
        return GameModelLoder.INSTANCE;
    }

    Tank tank = new Tank(200,400,Dir.UP, Group.GOOD, this);
    java.util.List<Bullet> bullets = new ArrayList<Bullet>();
//    Bullet b = new Bullet(300, 300, DOWN);

    java.util.List<Tank> tanks = new ArrayList<Tank>();

    //画爆炸
//    Explode e = new Explode(100, 100, this);

    //爆炸集合
    List<Explode> explodes = new ArrayList<>();



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

    public Tank getMainTank() {
        return tank;
    }

}
