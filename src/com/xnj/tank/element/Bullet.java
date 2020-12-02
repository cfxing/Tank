package com.xnj.tank.element;

import com.xnj.tank.*;

import java.awt.*;

/**
 * @author chen xuanyi
 * @create 2020-11-05 20:32
 */
public class Bullet extends GameObject{
    private static final int SPEED = PropertyMgr.getInt("bulletSpeed");
//    private static int WIDTH = 20, HEIGHT = 20;
    //图片子弹的宽和高
    public static int WIDTH = ResourceMgr.bulletD.getWidth();
    public static int HEIGHT = ResourceMgr.bulletD.getHeight();
    private int x, y;
    private Dir dir;
    //判断子弹是否离开边界
    private boolean living = true;
    private Group group = Group.BAD;

    //解决碰撞检测时候会new很多Rectangle对象
    public Rectangle rect = new Rectangle();

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Bullet(int x, int y, Dir dir, Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;

        rect.x = this.x;
        rect.y = this.y;
        rect.width = WIDTH;
        rect.height = HEIGHT;

    }

    public void paint(Graphics g){
        if (!living){
            GameModel.getInstance().remove(this);
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

        //更新 rect
        rect.x = this.x;
        rect.y = this.y;

        if (x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT){
            living = false;
        }
    }

    //碰撞检测后死亡
    public void die() {
        this.living = false;
    }
}
