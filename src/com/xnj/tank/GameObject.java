package com.xnj.tank;

import java.awt.*;

/**
 * 游戏物体从此类继承：Tank，Bullet, Explode
 * @author chen xuanyi
 * @create 2020-11-21 10:02
 */
public abstract class GameObject {
    public int x,y;

    public abstract void paint(Graphics g);
    public abstract int getWidth();
    public abstract int getHeight();
}
