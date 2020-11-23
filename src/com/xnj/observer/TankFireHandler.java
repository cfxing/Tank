package com.xnj.observer;

import com.xnj.tank.Tank;

/**
 * @author chen xuanyi
 * @create 2020-11-23 20:34
 */
public class TankFireHandler implements TankFireObserver {
    @Override
    public void actionOnFire(TankFireEvent e) {
        Tank t = e.getSource();
        t.fire();
    }
}
