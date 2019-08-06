package com.luban.concurrent;

import java.util.Date;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

/**
 * @Author 李非凡
 * @Description:
 * 阻塞式并发集合
 * 这类集合包括添加和移除数据的方法。
 * 当集合已满或为空时，被调用的添加或者移除方法就不能立即被执行，
 * 那么调用这个方法的线程将被阻塞，一直到该方法可以被成功执行。
 * LinkedBlockingDeque
 * @Date 2019/7/1 1:19
 * @Version 1.0
 */
public class LinkedBlockingDequeDemo {

    public static void main(String[] args) {
        LinkedBlockingDeque<String> list = new LinkedBlockingDeque<>(3);
        // 添加数据
        Thread thread = new Thread(()->{
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 5; j++) {
                    String string = new String(i + ":" + j);
                    try {
                        list.put(string.toString());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("client:" + string +(new Date()));
                }
            }
        });
        thread.start();
        // 移除数据
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 3; j++) {
                try {
                    String string = list.take();
                    System.out.println("main:take " + string + " size:" + list.size());
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("end");
    }
}
