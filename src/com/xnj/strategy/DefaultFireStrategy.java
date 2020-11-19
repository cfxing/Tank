package com.xnj.strategy;

import com.xnj.tank.Audio;
import com.xnj.tank.Bullet;
import com.xnj.tank.Group;
import com.xnj.tank.Tank;

/**
 * 默认开火策略，由于坦克 fire时候会创建很多对象，所以应该设计为单例模式
 * @author chen xuanyi
 * @create 2020-11-16 13:26
 */
public class DefaultFireStrategy implements FireStrategy {

    private DefaultFireStrategy(){
        System.out.println("你好");
    }
    private static final DefaultFireStrategy INSTANCE = new DefaultFireStrategy();
    public static DefaultFireStrategy getInstance(){
        return INSTANCE;
    }
    @Override
    public void fire(Tank t) {
        //持有对方的引用
        //        tf.b = new Bullet(this.x, this.y,this.dir);
        //计算，时子弹在坦克中间
        int bX = t.getX() + Tank.WIDTH/2 - Bullet.WIDTH/2;
        int bY = t.getY() + Tank.HEIGHT/2 - Bullet.HEIGHT/2;
        //使用容器来装多个子弹
        //可以直接new bullet,构造器会直接将其加入
        new Bullet(bX, bY, t.getDir(), t.getGroup(), t.tf);

        //声音
        if (t.getGroup() == Group.GOOD){
            new Thread(() -> new Audio("audio/tank_fire.wav").play()).start();
        }
    }
}
