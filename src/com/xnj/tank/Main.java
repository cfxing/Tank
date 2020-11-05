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
        Frame f = new TankFrame();

        while (true) {
            Thread.sleep(50);
            f.repaint();
        }

    }

}
