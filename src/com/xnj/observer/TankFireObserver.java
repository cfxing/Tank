package com.xnj.observer;

/**
 * @author chen xuanyi
 * @create 2020-11-23 20:34
 */
public interface TankFireObserver {
    public abstract void actionOnFire(TankFireEvent e);
}
