package com.xnj.tank.net;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author chen xuanyi
 * @create 2020-12-01 13:25
 */
public class ServerFrame extends Frame {
    public static final ServerFrame INSTANCE = new ServerFrame();

    Button btnStart = new Button("start");
    TextArea taLeft = new TextArea();
    TextArea taRight = new TextArea();
    Server server = new Server();

    private ServerFrame() {
        this.setSize(900, 400);
        this.setLocation(300, 30);
        this.add(btnStart,BorderLayout.NORTH);
        Panel p = new Panel(new GridLayout(1, 2));
        p.add(taLeft);
        p.add(taRight);
        this.add(p);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

//        this.btnStart.addActionListener((e) -> {
//            server.start();
//        });
    }

    public static void main(String[] args) {
        ServerFrame.INSTANCE.setVisible(true);
        //主线程启动，不会阻塞io线程
        ServerFrame.INSTANCE.server.start();
    }

    public void updateServerMsg(String str) {
        this.taLeft.setText(taLeft.getText() + System.getProperty("line.separator") + str);
    }

    public void updateClientMsg(String str) {
        this.taRight.setText(System.getProperty("line.separator") + str);
    }
}
