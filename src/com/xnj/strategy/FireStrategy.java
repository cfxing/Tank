package com.xnj.strategy;

import com.xnj.tank.Tank;

/**
 * 坦克开火的策略
 * @author chen xuanyi
 * @create 2020-11-16 13:24
 */
public interface FireStrategy {
    public abstract void fire(Tank t);
}
