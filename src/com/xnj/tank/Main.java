package com.xnj.tank;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author chen xuanyi
 * @create 2020-11-05 15:08
 */
public class Main {

     public static void main(String[] args) throws InterruptedException {
        TankFrame f = new TankFrame();

        int initTankCount = PropertyMgr.getInt("initTankCount");
        //初始化敌方坦克
        for (int i = 0; i < initTankCount; i++){
            f.tanks.add(new Tank(50 + i * 80, 200, Dir.DOWN, Group.BAD,f));
        }

        new Thread(() -> new Audio("audio/war1.wav").loop()).start();
        while (true) {
            Thread.sleep(50);

            //repaint 会先调用 update
            //截获update ， 首先把需要画的画的内存的图片中，图片的大小和游戏画面一致
            //把内存中的图片一次性画到屏幕上（内存内容复制到显存）
            f.repaint();
        }

    }

}
