package com.xnj.tank.net;

import com.xnj.tank.Dir;
import com.xnj.tank.Tank;
import com.xnj.tank.TankFrame;

import java.io.*;
import java.util.UUID;

/**
 * @author chen xuanyi
 * @create 2020-12-02 12:02
 */
public class TankStartMovingMsg extends Msg {

    UUID id;
    int x, y;
    Dir dir;

    public TankStartMovingMsg() {
    }

    public TankStartMovingMsg(Tank tank) {
        this.id = tank.getId();
        this.x = tank.getX();
        this.y = tank.getY();
        this.dir = tank.getDir();
    }

    @Override
    public void handle() {
        if (this.id.equals(TankFrame.getInstance().getMainTank().getId())){
            return;
        }

        Tank tank = TankFrame.getInstance().findUUID(this.id);
        if (tank != null) {
            tank.setMoving(true);
            tank.setX(this.x);
            tank.setY(this.y);
            tank.setDir(this.dir);
        }
    }

    @Override
    public byte[] toBytes() {
        ByteArrayOutputStream baos = null;
        DataOutputStream dos = null;
        byte[] bytes = null;
        try {
            baos = new ByteArrayOutputStream();
            dos = new DataOutputStream(baos);
            dos.writeLong(id.getMostSignificantBits());
            dos.writeLong(id.getLeastSignificantBits());
            dos.writeInt(x);
            dos.writeInt(y);
            dos.writeInt(dir.ordinal());
            dos.flush();
            bytes = baos.toByteArray();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (dos != null){
                try {
                    dos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (baos != null){
                try {
                    baos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return bytes;
    }

    @Override
    public void parse(byte[] bytes) {
        ByteArrayInputStream bais = null;
        DataInputStream dis = null;
        try {
            bais = new ByteArrayInputStream(bytes);
            dis = new DataInputStream(bais);
            this.id = new UUID(dis.readLong(), dis.readLong());
            this.x = dis.readInt();
            this.y = dis.readInt();
            this.dir = Dir.values()[dis.readInt()];
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (dis != null) {
                try {
                    dis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bais != null) {
                try {
                    bais.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @Override
    public MsgType getMsgType() {
        return MsgType.TankStartMoving;
    }
}
