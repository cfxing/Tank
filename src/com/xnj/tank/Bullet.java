package com.xnj.tank;

import java.awt.*;

/**
 * @author chen xuanyi
 * @create 2020-11-05 20:32
 */
public class Bullet {
    private static final int SPEED = 15;
//    private static int WIDTH = 20, HEIGHT = 20;
    //图片子弹的宽和高
    public static int WIDTH = ResourceMgr.bulletD.getWidth();
    public static int HEIGHT = ResourceMgr.bulletD.getHeight();
    private int x, y;
    private Dir dir;
    private TankFrame tf = null;

    //判断子弹是否离开边界
    private boolean living = true;
    private  Group group = Group.BAD;

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Bullet(int x, int y, Dir dir, Group group, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.tf = tf ;
    }

    public void paint(Graphics g){
        if (!living){
            tf.bullets.remove(this);
        }
//        Color c = g.getColor();
//        g.setColor(Color.BLUE);
//        g.fillOval(x, y, WIDTH, HEIGHT);
//        g.setColor(c);

        //画图片
        switch (dir) {
            case UP:
                g.drawImage(ResourceMgr.bulletU, x, y , null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.bulletR, x , y , null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.bulletD, x , y , null);
                break;
            case LEFT:
                g.drawImage(ResourceMgr.bulletL, x , y , null);
                break;
            default:
                break;
        }

        move();
    }

    private void move() {
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

        if (x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT){
            living = false;
        }
    }

    public void collideWith(Tank tank) {
        //开启队友之间不伤害
        if (this.group == tank.getGroup()) {
            return;
        }

        //TODO:用一个rect 来记录子弹的位置
        //Rectangle 为辅助类，矩形
        //获得子弹的矩形
        Rectangle rect1 = new Rectangle(this.x, this.y,WIDTH, HEIGHT);

        //坦克的方块
        Rectangle rect2 = new Rectangle(tank.getX(), tank.getY(), Tank.WIDTH, Tank.HEIGHT);

        if (rect1.intersects(rect2)){
            tank.die();
            this.die();
        }
    }

    //碰撞检测后死亡
    private void die() {
        this.living = false;
    }
}
