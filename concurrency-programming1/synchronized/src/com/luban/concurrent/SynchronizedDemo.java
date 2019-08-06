package com.luban.concurrent;

import java.util.concurrent.TimeUnit;

/**
 * @Author 李非凡
 * @Description:
 * synchronized的用法
 * 保证原子性
 * 保证可见性：使用monitor
 * 保证有序性，但是代价太大，重量级锁使并发退化到串行
 * @Date 2019/2/1 13:04
 * @Version 1.0
 */
public class SynchronizedDemo {

    /**
     * 同步修饰静态方法
     */
    public synchronized static void accessResources0() {
        try {
            TimeUnit.SECONDS.sleep(2);
            System.out.println(Thread.currentThread().getName() + " is running");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 同步修饰非静态方法
     */
    public synchronized void accessResources1(){
        try {
            // 此时会触发锁消除，因为赋值操作有原子性，不需要加锁
            synchronized (this){
                int i = 10;
            }
            //
            synchronized (this){
                TimeUnit.SECONDS.sleep(2);
            }
            System.out.println(Thread.currentThread().getName() + " is running");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 同步代码块(对象)
     * this指的是当前对象
     */
    public void accessResources2() {
        synchronized (this){
            try {
                TimeUnit.SECONDS.sleep(2);
                System.out.println(Thread.currentThread().getName() + " is running");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 同步代码块(CLASS类)
     */
    public void accessResources3() {
        // ClassLoader class ——-》堆 Class 所有的对象，有Class对象的所有的对象都共同使用这一个锁
        synchronized (SynchronizedDemo.class){
            try {
                TimeUnit.SECONDS.sleep(2);
                System.out.println(Thread.currentThread().getName() + " is running");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        // 测试静态方法
        for (int i = 0; i < 5; i++) {
            new Thread(SynchronizedDemo::accessResources0).start();
        }
        // 测试非静态方法
        final SynchronizedDemo demo = new SynchronizedDemo();
        for (int i = 0; i < 5; i++) {
            new Thread(demo::accessResources1).start();
        }
        // 测试同步代码块
        for (int i = 0; i < 5; i++) {
            new Thread(demo::accessResources2).start();
        }
    }
}
