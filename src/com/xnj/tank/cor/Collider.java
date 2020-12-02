package com.xnj.tank.cor;

import com.xnj.tank.GameObject;

/**
 * @author chen xuanyi
 * @create 2020-12-02 16:25
 */
public interface Collider {
    public abstract boolean collide(GameObject o1, GameObject o2);
}
