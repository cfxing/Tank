package com.xnj.strategy;

import com.xnj.tank.*;

/**
 * 4 个方向都能发射子弹的策略
 *
 * @author chen xuanyi
 * @create 2020-11-16 13:46
 */
public class FourDirFireStrategy implements FireStrategy {

    private FourDirFireStrategy(){}
    private static final FourDirFireStrategy INSTANCE = new FourDirFireStrategy();
    public static FourDirFireStrategy getInstance(){
        return  INSTANCE;
    }

    @Override
    public void fire(Tank t) {
        //持有对方的引用
        //        tf.b = new Bullet(this.x, this.y,this.dir);
        //计算，时子弹在坦克中间
        int bX = t.getX() + Tank.WIDTH/2 - Bullet.WIDTH/2;
        int bY = t.getY() + Tank.HEIGHT/2 - Bullet.HEIGHT/2;

        Dir[] dirs = Dir.values();
        for (Dir dir: dirs){

            //使用容器来装多个子弹
            //可以直接new bullet,构造器会直接将其加入
            new Bullet(bX, bY, dir, t.getGroup(), t.tf);
        }


        //声音
        if (t.getGroup() == Group.GOOD){
            new Thread(() -> new Audio("audio/tank_fire.wav").play()).start();
        }
    }
}
