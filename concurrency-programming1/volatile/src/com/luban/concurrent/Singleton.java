package com.luban.concurrent;

/**
 * @Author 李非凡
 * @Description:
 * volatile实现懒汉式单例模式
 * @Date 2019/6/30 13:36
 * @Version 1.0
 */
public class Singleton {
    /**
     *
     */
    private static volatile Singleton instance;

    /**
     *
     * @return
     */
    public static Singleton getInstance(){
        if (instance == null){
            synchronized (Singleton.class){
                instance = new Singleton();
            }
        }
        return instance;
    }
}
