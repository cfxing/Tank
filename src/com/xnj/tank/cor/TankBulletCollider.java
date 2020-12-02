package com.xnj.tank.cor;

import com.xnj.tank.GameModel;
import com.xnj.tank.GameObject;
import com.xnj.tank.element.Bullet;
import com.xnj.tank.element.Explode;
import com.xnj.tank.element.Tank;

/**
 * @author chen xuanyi
 * @create 2020-12-02 16:26
 */
public class TankBulletCollider implements Collider{

    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if (o1 instanceof Tank && o2 instanceof Bullet){
            Tank tank = (Tank)o1;
            Bullet bullet = (Bullet)o2;
            //开启队友之间不伤害
            if (tank.getGroup() == bullet.getGroup()) {
                return true;
            }

            if (bullet.rect.intersects(tank.rect)){
                tank.die();
                bullet.die();
                //写到 die方法也可以
                int eX = tank.getX() + Tank.WIDTH/2 - Explode.WIDTH/2;
                int eY = tank.getY() + Tank.HEIGHT/2 - Explode.HEIGHT/2;
                GameModel.getInstance().add(new Explode(eX,eY));
                return false;
            }
        }else {
            //TODO: 出现异常
            return collide(o2,o1);
        }
        return true;
    }
}
