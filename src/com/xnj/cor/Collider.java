package com.xnj.cor;

import com.xnj.tank.GameObject;

/**
 * 碰撞检测器
 * @author chen xuanyi
 * @create 2020-11-21 10:41
 */
public interface Collider {
    //让 碰撞检测有返回值，如果为false，说明产生碰撞，不再检测，如果为true，说明没有发生碰撞，继续
    boolean collide(GameObject o1, GameObject o2);
}
