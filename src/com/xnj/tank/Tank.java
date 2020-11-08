package com.xnj.tank;

import java.awt.*;
import java.time.Year;
import java.util.Random;

/**
 * @author chen xuanyi
 * @create 2020-11-05 19:36
 */
public class Tank {
    private int x, y;
    private Dir dir;
    private static final int SPEED = 5;

    public static int WIDTH = ResourceMgr.goodTankU.getWidth();
    public static int HEIGHT = ResourceMgr.goodTankU.getHeight();

    private boolean moving = true;

    //持有对象的引用
    private TankFrame tf = null;
    //判断坦克是否活着
    private boolean living = true;

    //生成随机数
    private Random random = new Random();

    private Group group = Group.BAD;

    Rectangle rect = new Rectangle();


    public Tank(int x, int y, Dir dir,Group group, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.tf = tf;

        rect.x = this.x;
        rect.y = this.y;
        rect.width = WIDTH;
        rect.height = HEIGHT;
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

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
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
                g.drawImage(this.group == Group.GOOD? ResourceMgr.goodTankU : ResourceMgr.badTankU,x,y,null);
                break;
            case RIGHT:
                g.drawImage(this.group == Group.GOOD? ResourceMgr.goodTankR : ResourceMgr.badTankR,x,y,null);
                break;
            case DOWN:
                g.drawImage(this.group == Group.GOOD? ResourceMgr.goodTankD : ResourceMgr.badTankD,x,y,null);
                break;
            case LEFT:
                g.drawImage(this.group == Group.GOOD? ResourceMgr.goodTankL : ResourceMgr.badTankL,x,y,null);
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


//        if (this.group == Group.GOOD){
//            new Thread(() -> new Audio("audio/tank_move.wav").play()).start();
//        }

        //定义随机发射子弹
        if(this.group == Group.BAD && random.nextInt(100) > 95){
            this.fire();
        }

        //定义随机改变方向
        if (this.group == Group.BAD && random.nextInt(100) > 95){

            randomDir();
        }

        //边界检测
        boundsCheck();


        //更新 rect
        rect.x = this.x;
        rect.y = this.y;
    }

    private void boundsCheck() {
        if (this.x < 0){ x = 0; }
        if (this.y < 30){ y = 30; }
        if (this.x > TankFrame.GAME_WIDTH - Tank.WIDTH - 2 ){ x = TankFrame.GAME_WIDTH - Tank.WIDTH -2 ; }
        if (this.y > TankFrame.GAME_HEIGHT - Tank.HEIGHT - 2 ){ y = TankFrame.GAME_HEIGHT - Tank.HEIGHT - 2; }
    }

    private void randomDir() {
        //产生4以内的随机数

        //Dir.values(): 返回一个数组，数组内存的是它的值，用4以内的下标来对于不同的方向
        this.dir = Dir.values()[random.nextInt(4)];
    }

    public void fire() {
        //持有对方的引用
//        tf.b = new Bullet(this.x, this.y,this.dir);

        //计算，时子弹在坦克中间
        int bX = this.x + Tank.WIDTH/2 - Bullet.WIDTH/2;
        int bY = this.y + Tank.HEIGHT/2 - Bullet.HEIGHT/2;
        //使用容器来装多个子弹
        tf.bullets.add(new Bullet(bX, bY, this.dir,this.group, this.tf));

        //声音
        if (this.group == Group.GOOD){
            new Thread(() -> new Audio("audio/tank_fire.wav").play()).start();
        }
    }

    //碰撞检测后死亡
    public void die() {
        this.living = false;
    }
}
