package com.xnj.cor;

import com.xnj.tank.Bullet;
import com.xnj.tank.Explode;
import com.xnj.tank.GameObject;
import com.xnj.tank.Tank;

/**
 * @author chen xuanyi
 * @create 2020-11-21 11:43
 */
public class TankTankCollider implements Collider {
    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if (o1 instanceof Tank && o2 instanceof Tank){
            Tank t1 = (Tank)o1;
            Tank t2 = (Tank)o2;

            //collideWith
            //队友不伤害

            if (t1.rect.intersects(t2.rect)){
                t1.setX(t1.prevX);
                t1.setY(t1.prevY);
                t2.setX(t2.prevX);
                t2.setY(t2.prevY);
            }
        }
        return true;

    }
}
