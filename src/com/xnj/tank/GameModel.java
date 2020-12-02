package com.xnj.tank;

import com.xnj.tank.cor.ColliderChain;
import com.xnj.tank.element.*;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * @author chen xuanyi
 * @create 2020-12-02 15:21
 */
public class GameModel {
    private static class GameModelLoader {
        private static final GameModel INSTANCE = new GameModel();
    }
    public static GameModel getInstance(){
        return GameModelLoader.INSTANCE;
    }
    Random r = new Random();
    Tank tank = new Tank(r.nextInt(TankFrame.GAME_WIDTH),r.nextInt(TankFrame.GAME_HEIGHT), Dir.UP, Group.GOOD);


    List<GameObject> objects = new ArrayList<>();

    Map<UUID, Tank> tanks = new HashMap<>();

    ColliderChain chain = new ColliderChain();

    public Tank findUUID(UUID id) {
        return tanks.get(id);
    }

    public void addTank(Tank t) {
        tanks.put(t.getId(), t);
    }

    public void paint(Graphics g){
        tank.paint(g);

        tanks.values().stream().forEach( (e) -> e.paint(g));

        for (int i = 0; i < objects.size(); i++) {
            objects.get(i).paint(g);
        }

        for (int i = 0; i < objects.size(); i++) {
            for (int j = i+1; j <objects.size() ; j++) {
                GameObject o1 = objects.get(i);
                GameObject o2 = objects.get(j);
                chain.collide(o1, o2);

            }
        }
    }

    public void add(GameObject go) {
        objects.add(go);
    }
    public void remove(GameObject go){
        objects.remove(go);
    }

    public Tank getMainTank() {
        return tank;
    }
}

