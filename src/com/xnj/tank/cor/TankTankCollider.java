package com.xnj.tank.cor;

import com.xnj.tank.GameObject;
import com.xnj.tank.element.Tank;

/**
 * @author chen xuanyi
 * @create 2020-12-02 16:40
 */
public class TankTankCollider implements Collider {

    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if (o1 instanceof Tank && o2 instanceof Tank) {
            Tank t1 = (Tank)o1;
            Tank t2 = (Tank)o2;
            if (t1.rect.intersects(t2.rect)){
                t1.back();
            }
        }
        return true;
    }
}
