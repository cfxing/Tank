package com.xnj.tank.cor;

import com.xnj.tank.GameObject;

import java.util.LinkedList;
import java.util.List;

/**
 * @author chen xuanyi
 * @create 2020-12-02 16:36
 */
public class ColliderChain implements Collider{
    private List<Collider> colliders = new LinkedList<>();

    public ColliderChain() {
        add(new TankBulletCollider());
    }

    public void add(Collider c) {
        colliders.add(c);
    }

    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        for (int i = 0; i < colliders.size(); i++) {
            if (!colliders.get(i).collide(o1, o2)) {
                return false;
            }
        }
        return true;
    }
}
