package com.luban.concurrent;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author 李非凡
 * @Description:
 * CAS,全称为CompareAndSwap，是一种无锁的比较交换原子算法。
 * @Date 2019/1/10 21:58
 * @Version 1.0
 */
public class CompareAndSwap1 {

    /**
     *
     */
    private static volatile int number = 0;

    /**
     * 原子性的整型
     */
    private static AtomicInteger atomicInteger = new AtomicInteger(0);

    /**
     * 自增方法
     */
    public static void increase1(){
        number++;
    }

    /**
     * 原子性的自增方法
     */
    public static void increase2(){
        atomicInteger.incrementAndGet();
    }

    public static void main(String[] args) throws InterruptedException{
        Thread[] threads1 = new Thread[20];
        for (int i = 0; i < 20; i++) {
           threads1[i] = new Thread(()->{
                CompareAndSwap1.increase1();
            });
           threads1[i].start();
           threads1[i].join();//join方法加入group main() join线程有了交互性
        }
        System.out.println("number : " + number);

        Thread[] threads2 = new Thread[20];
        for (int i = 0; i < 20; i++) {
            threads2[i] = new Thread(()->{
                CompareAndSwap1.increase2();
            });
            threads2[i].start();
            threads2[i].join();
        }
        System.out.println("atomic : " + atomicInteger.get());

    }
}
