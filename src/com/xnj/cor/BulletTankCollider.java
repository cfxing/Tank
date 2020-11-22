package com.xnj.cor;

import com.xnj.tank.*;


/**
 * 负责墙和坦克的相撞
 * @author chen xuanyi
 * @create 2020-11-21 11:03
 */
public class BulletTankCollider implements Collider {
    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if (o1 instanceof Bullet && o2 instanceof Tank){
            Bullet b = (Bullet)o1;
            Tank t = (Tank)o2;

            //collideWith
            //队友不伤害
            if (b.getGroup() == t.getGroup()){
                return true;
            }
            if (b.rect.intersects(t.rect)){
                t.die();
                b.die();
                int eX = t.getX() + Tank.WIDTH/2 - Explode.WIDTH/2;
                int eY = t.getY() + Tank.HEIGHT/2 - Explode.HEIGHT/2;
                new Explode(eX,eY);
                return false;
            }
        }else if (o1 instanceof Tank && o2 instanceof Bullet){
            return collide(o2, o1);
        }
            return true;
    }
}
