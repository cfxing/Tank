package com.xnj.tank;

import java.awt.*;

/**
 * 存儲爆炸的圖片
 * @author chen xuanyi
 * @create 2020-11-08 14:19
 */
public class Explode {
    public static int WIDTH = ResourceMgr.explodes[0].getWidth();
    public static int HEIGHT = ResourceMgr.explodes[0].getHeight();

    private int x,y;
//    private  boolean living = true;
    TankFrame tf = null;

    private int step = 0;

    public Explode(int x, int y, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.tf = tf;

//        new Audio("audio/explode.wav").loop();
    }

    public void paint(Graphics g){
        g.drawImage(ResourceMgr.explodes[step++], x, y, null);

        if (step >= ResourceMgr.explodes.length){
            //不需要living属性，当爆炸完成后就删除
            tf.explodes.remove(this);
        }
    }
}