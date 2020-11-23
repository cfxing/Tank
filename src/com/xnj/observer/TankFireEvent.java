package com.xnj.observer;

import com.xnj.tank.Tank;

/**
 * @author chen xuanyi
 * @create 2020-11-23 20:32
 */
public class TankFireEvent {
    Tank tank;

    public Tank getSource() {
        return tank;
    }

    public TankFireEvent(Tank tank) {
        this.tank = tank;
    }
}
