package com.xnj.tank.net;

/**
 * @author chen xuanyi
 * @create 2020-12-02 10:22
 */
public abstract class Msg {
    public abstract void handle();
    public abstract byte[] toBytes();
    public abstract void parse(byte[] bytes);
    public abstract MsgType getMsgType();
}
