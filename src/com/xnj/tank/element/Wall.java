package com.xnj.tank.element;

import com.xnj.tank.GameModel;
import com.xnj.tank.GameObject;
import com.xnj.tank.ResourceMgr;

import java.awt.*;

/**
 * @author chen xuanyi
 * @create 2020-12-02 16:08
 */
public class Wall extends GameObject {

    int w,h;

    public Rectangle rect;

    GameModel gm;

    public Wall(int x, int y,int w, int h, GameModel gm) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        gm.add(this);

        this.rect = new Rectangle();


    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(ResourceMgr.walls[0],this.x, this.y,null);
    }
}
