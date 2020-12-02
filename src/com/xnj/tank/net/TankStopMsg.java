package com.xnj.tank.net;

import com.xnj.tank.Tank;
import com.xnj.tank.TankFrame;

import java.io.*;
import java.util.UUID;

/**
 * @author chen xuanyi
 * @create 2020-12-02 14:11
 */
public class TankStopMsg extends Msg{

    UUID id;
    int x,y;

    public TankStopMsg(UUID id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }

    public TankStopMsg(Tank tank){
        this.id = tank.getId();
        this.x = tank.getX();
        this.y = tank.getY();
    }

    public TankStopMsg() {
    }

    @Override
    public void handle() {
        //说明是自己
        if (this.id.equals(TankFrame.getInstance().getMainTank().getId())){
            return;//不处理
        }

        //找到坦克
        Tank t = TankFrame.getInstance().findUUID(this.id);

        //说明找到
        if (t != null) {
            t.setMoving(false);
            t.setX(this.x);
            t.setY(this.y);
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
            dos.flush();
            bytes = baos.toByteArray();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (dos != null) {
                try {
                    dos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (baos != null) {
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
        return MsgType.TankStop;
    }
}
