package com.luban.concurrent;

import java.util.Random;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * @Author 李非凡
 * @Description:
 * 用CyclicBarrier实现运动员（线程）竞赛场景
 * CyclicBarrier每当拦截下一个线程计数器会加1
 * 当计数器的值等于初始设定的parties时，所有线程会一起执行
 * @Date 2019/3/31 0:58
 * @Version 1.0
 */
public class RaceDemo {

    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(8);
        Thread[] player = new Thread[8];

        for (int i = 0; i < player.length; i++) {
            player[i] = new Thread(()->{
                try {
                    TimeUnit.SECONDS.sleep(new Random().nextInt(10));
                    System.out.println(Thread.currentThread().getName() + "准备好了");
                    barrier.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("选手" + Thread.currentThread().getName() + "起跑");
            },"player[" + i + "]");
            player[i].start();
        }
    }
}
