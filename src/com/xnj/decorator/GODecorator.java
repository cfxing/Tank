package com.xnj.decorator;

import com.xnj.tank.GameObject;

import java.awt.*;

/**装饰器模式 decorator
 * @author chen xuanyi
 * @create 2020-11-23 10:33
 */
public abstract class GODecorator extends GameObject {

    GameObject go;

    public GODecorator(GameObject go) {
        this.go = go;
    }

    public abstract void paint(Graphics g);

}
