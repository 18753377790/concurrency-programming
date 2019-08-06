package com.luban.concurrent;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @Author 李非凡
 * @Description:
 * 任务对象
 * 创建线程的方法
 * new Thread（代表真正意义的线程，有生命周期）
 * implements Runnable（重写run()方法，执行的时候要传到一个线程里面去）
 * @Date 2019/7/1 15:06
 * @Version 1.0
 */
public class TaskDemo implements Runnable{
    /**
     * 运行方法
     */
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "is running");
        try {
            TimeUnit.SECONDS.sleep(new Random().nextInt(10));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
