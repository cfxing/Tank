package com.xnj.cor;

import com.xnj.tank.Bullet;
import com.xnj.tank.GameObject;
import com.xnj.tank.Wall;

/**
 * @author chen xuanyi
 * @create 2020-11-22 20:35
 */
public class BulletWallCollider implements Collider {
    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if (o1 instanceof Bullet && o2 instanceof Wall) {
            Bullet b = (Bullet)o1;
            Wall w = (Wall)o2;

            if (b.rect.intersects(w.rect)){
                b.die();
                return true;
            }
        } else if (o1 instanceof Wall && o2 instanceof Bullet) {
            return collide(o2,o1);
        }
        return true;
    }
}
