package com.luban.concurrent;

import java.util.concurrent.TimeUnit;

/**
 * @Author 李非凡
 * @Description:
 * 用一个线程读数据，一个线程改数据
 * 存在数据的不一致性
 * 实现一次更新，一次读取
 * @Date 2019/2/1 0:51
 * @Version 1.0
 */
public class ReaderAndUpdater {
    /**
     * 要修改的变量值的上限
     */
    private final static int MAX = 50;

    /**
     * 要修改的变量，需要加一个volatile，才能使数据同步
     */
    private static volatile int init_value = 0;

    public static void main(String[] args) {
        // 读的线程
        new Thread(()->{
            // 定义一个线程私有的局部变量，赋值为全局的静态变量
            int localValue = init_value;
            while (localValue < MAX){
                //
                if (localValue != init_value){
                    System.out.println("Reader" + init_value);
                    localValue = init_value;
                }
            }
        },"Reader").start();
        // 改的线程
        new Thread(()->{
            // 虽然两个变量的变量名都是localValue，但是这两个同名变量是线程私有的，是两个不同的变量
            int localValue = init_value;
            while (localValue < MAX){
                System.out.println("Updater" + (++localValue));
                init_value = localValue;
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"Updater").start();
    }
}
