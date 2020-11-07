package com.xnj.tank;

import java.awt.*;
import java.util.Random;

/**
 * @author chen xuanyi
 * @create 2020-11-05 19:36
 */
public class Tank {
    private int x, y;
    private Dir dir;
    private static final int SPEED = 10;

    public static int WIDTH = ResourceMgr.tankD.getWidth();
    public static int HEIGHT = ResourceMgr.tankD.getHeight();

    private boolean moving = false;

    //持有对象的引用
    private TankFrame tf = null;
    //判断坦克是否活着
    private boolean living = true;

    //生成随机数
    private Random random;


    public Tank(int x, int y, Dir dir, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
    }

    public Dir getDir() {
        return dir;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public void paint(Graphics g) {
//        Color c = g.getColor();
//        g.setColor(Color.YELLOW);
//        g.fillRect(x,y,50,50);
//        g.setColor(c);

        if (!living) {
            tf.tanks.remove(this);
        }
        //画图片
        //判断方向画图
        switch (dir){
            case UP:
                g.drawImage(ResourceMgr.tankU,x,y,null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.tankR,x,y,null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.tankD,x,y,null);
                break;
            case LEFT:
                g.drawImage(ResourceMgr.tankL,x,y,null);
                break;
            default:
                break;

        }
//        g.drawImage(ResourceMgr.tankL,x,y,null);

//        g.fillRect(x,y,50, 50);

        move();

    }

    private void move() {
        if (!moving){
            return;
        }
        switch (dir){
            case LEFT:
                x -= SPEED;
                break;
            case UP:
                y -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
            default:
                break;
        }
    }

    public void fire() {
        //持有对方的引用
//        tf.b = new Bullet(this.x, this.y,this.dir);

        //计算，时子弹在坦克中间
        int bX = this.x + Tank.WIDTH/2 - Bullet.WIDTH/2;
        int bY = this.y + Tank.HEIGHT/2 - Bullet.HEIGHT/2;
        //使用容器来装多个子弹
        tf.bullets.add(new Bullet(bX, bY, this.dir,this.tf));
    }

    //碰撞检测后死亡
    public void die() {
        this.living = false;
    }
}
