package com.xnj.tank.element;

import com.xnj.tank.GameModel;
import com.xnj.tank.GameObject;
import com.xnj.tank.ResourceMgr;
import com.xnj.tank.TankFrame;

import java.awt.*;

/**
 * 存儲爆炸的圖片
 * @author chen xuanyi
 * @create 2020-11-08 14:19
 */
public class Explode extends GameObject {
    public static int WIDTH = ResourceMgr.explodes[0].getWidth();
    public static int HEIGHT = ResourceMgr.explodes[0].getHeight();

    private int x,y;

    private int step = 0;

    public Explode(int x, int y) {
        this.x = x;
        this.y = y;

    }

    public void paint(Graphics g){
        g.drawImage(ResourceMgr.explodes[step++], x, y, null);

        if (step >= ResourceMgr.explodes.length){
            //不需要living属性，当爆炸完成后就删除
            GameModel.getInstance().remove(this);
        }
    }
}
