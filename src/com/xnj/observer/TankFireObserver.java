package com.xnj.observer;

import java.io.Serializable;

/**
 * @author chen xuanyi
 * @create 2020-11-23 20:34
 */
public interface TankFireObserver extends Serializable {
    public abstract void actionOnFire(TankFireEvent e);
}
