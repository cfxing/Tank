package com.xnj.tank;

import com.xnj.cor.BulletTankCollider;
import com.xnj.cor.Collider;
import com.xnj.cor.ColliderChain;
import com.xnj.cor.TankTankCollider;

import javax.xml.crypto.KeySelector;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 门面模型 Facade
 * 应该为单例
 * Serializable: 实现序列化
 * @author chen xuanyi
 * @create 2020-11-20 14:58
 */
public class GameModel{

    //在init（）中初始化
    Tank tank;

    static {
        getInstance().init();
    }

//    java.util.List<Bullet> bullets = new ArrayList<Bullet>();
////    Bullet b = new Bullet(300, 300, DOWN);
//
//    java.util.List<Tank> tanks = new ArrayList<Tank>();
//
//    //画爆炸
////    Explode e = new Explode(100, 100, this);
//
//    //爆炸集合
//    List<Explode> explodes = new ArrayList<>();
//    Collider collider1 = new BulletTankCollider();
//    Collider collider2 = new TankTankCollider();
    ColliderChain colliderChain = new ColliderChain();

    //使用一个物体集合管理物体
    private List<GameObject> objects = new ArrayList<>();

    private void init() {

        tank = new Tank(200,400,Dir.UP, Group.GOOD);

        int initTankCount = PropertyMgr.getInt("initTankCount");
        //初始化敌方坦克
        for (int i = 0; i < initTankCount; i++){
            new Tank(50 + i * 80, 200, Dir.DOWN, Group.BAD);
        }

        //初始化墙
        add(new Wall(150,150,200,50));
        add(new Wall(500,150,200,50));
        add(new Wall(300,300,50, 200));
        add(new Wall(550,300,50,200));
    }

    public void  add(GameObject go){
        this.objects.add(go);
    }

    public void remove(GameObject go){
        this.objects.remove(go);
    }

    private GameModel(){
    }

    private static class GameModelLoder{
        private static final GameModel INSTANCE = new GameModel();
    }
    public static GameModel getInstance(){
        return GameModelLoder.INSTANCE;
    }


    public void paint(Graphics g) {
        //        System.out.println("print");
//        g.fillRect(x,y,50,50);
//        x += 10;
//        y += 10;

        //修改颜色
        //保存之前颜色
//        Color c = g.getColor();
//        //修改颜色
//        g.setColor(Color.WHITE);
//        g.drawString("子弹的数量" + bullets.size(), 10, 60);
//        g.drawString("敌人的数量" + tanks.size(), 10, 80);
//        g.drawString("爆炸的数量" + explodes.size(), 10, 100);
//
//        //恢复原来的颜色
//        g.setColor(c);

        tank.paint(g);

//        b.paint(g);

        //此方法删除子弹会报错
//        for (Bullet b: bullets){
//            b.paint(g);
//        }

        for (int i = 0; i < objects.size(); i++){
            objects.get(i).paint(g);
        }

        //碰撞检测，互相碰撞
        for (int i = 0; i < objects.size(); i++) {
            for (int j = i+1; j < objects.size(); j++) {
                GameObject o1 = objects.get(i);
                GameObject o2 = objects.get(j);
//                collider1.collide(o1, o2);
//                collider2.collide(o1,o2);
                colliderChain.collide(o1, o2);
            }
        }

        //画坦克
//        for (int i = 0 ; i < tanks.size(); i++){
//            tanks.get(i).paint(g);
//        }

        //碰撞检测
//        for(int i = 0; i < bullets.size(); i++){
//            for (int j = 0; j < tanks.size(); j++){
//
//                bullets.get(i).collideWith(tanks.get(j));
//            }
//        }

        //画爆炸
//        e.paint(g);

        //爆炸集合
//        for (int i = 0; i < explodes.size(); i++) {
//            explodes.get(i).paint(g);
//        }
        //删除子弹的另一种代码
//        for (Iterator<Bullet> it = bullets.iterator(); it.hasNext();){
//            Bullet b = it.next();
//            if (!b.live){
//                it.remove();
//            }
//        }
    }

    public Tank getMainTank() {
        return tank;
    }

    public void save() {
        File f = new File("C:\\Users\\19453\\Desktop\\IT_book\\tank.data");
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(f));
            oos.writeObject(tank);
            oos.writeObject(objects);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (oos != null){
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void load() {
        File f = new File("C:\\Users\\19453\\Desktop\\IT_book\\tank.data");
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(f));
            tank = (Tank)ois.readObject();
            objects = (List)ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
