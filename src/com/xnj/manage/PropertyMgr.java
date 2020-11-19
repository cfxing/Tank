package com.xnj.manage;

import java.io.IOException;
import java.util.Properties;

/**
 * 管理配置文件 properties
 *
 * @author chen xuanyi
 * @create 2020-11-08 19:50
 */
public class PropertyMgr {

    private PropertyMgr(){}

    private static Properties props = new Properties();

    static{
        try {
            props.load(PropertyMgr.class.getClassLoader().getResourceAsStream("config"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String get(String key){
        if (props == null){
            return null;
        }
        return (String)props.get(key);
    }

    //getInt
    public static int getInt(String key) {
        return Integer.parseInt(PropertyMgr.get(key));
    }

//    public static void main(String[] args) {
//        System.out.println(PropertyMgr.get("initTankCount"));
//    }

}
