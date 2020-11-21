package com.xnj.tank;

import java.awt.*;

/**
 * 游戏物体从此类继承：Tank，Bullet, Explode
 * @author chen xuanyi
 * @create 2020-11-21 10:02
 */
public abstract class GameObject {
    int x,y;

    public abstract void paint(Graphics g);
}
