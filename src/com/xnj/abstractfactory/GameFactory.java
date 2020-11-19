package com.xnj.abstractfactory;

import com.xnj.tank.Dir;
import com.xnj.tank.Group;
import com.xnj.tank.TankFrame;

/**
 * 抽象工厂父类，可以生产坦克，爆炸，子弹
 *
 * @author chen xuanyi
 * @create 2020-11-19 16:59
 */
public abstract class GameFactory {
    public abstract BaseTank creatTank(int x, int y, Dir dir, Group group, TankFrame tf);
    public abstract BaseExplode creatExplode(int x, int y, TankFrame tf);
    public abstract BaseBullet createBullet(int x, int y, Dir dir, Group group, TankFrame tf);
}
