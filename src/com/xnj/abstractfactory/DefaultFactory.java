package com.xnj.abstractfactory;

import com.xnj.tank.*;

/**
 * @author chen xuanyi
 * @create 2020-11-19 17:05
 */
public class DefaultFactory extends GameFactory {
    @Override
    public BaseTank creatTank(int x, int y, Dir dir, Group group, TankFrame tf) {
        return new Tank(x, y, dir, group, tf);
    }

    @Override
    public BaseExplode creatExplode(int x, int y, TankFrame tf) {
        return new Explode(x, y, tf);
    }

    @Override
    public BaseBullet createBullet(int x, int y, Dir dir, Group group, TankFrame tf) {
        return new Bullet(x, y, dir, group, tf);
    }
}
