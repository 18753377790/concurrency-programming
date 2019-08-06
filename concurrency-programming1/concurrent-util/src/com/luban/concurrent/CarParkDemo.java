package com.luban.concurrent;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @Author 李非凡
 * @Description:
 * 用Semaphore实现停车场场景
 * @Date 2019/3/31 9:08
 * @Version 1.0
 */
public class CarParkDemo {

    public static void main(String[] args) {
        //创建Semaphore
        Semaphore semaphore = new Semaphore(5);

        Thread[] car = new Thread[10];
        for (int i = 0; i < car.length; i++) {
            car[i] = new Thread(()->{
                //请求许可
                try {
//                    TimeUnit.SECONDS.sleep(2);
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "可以进停车场");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //使用资源
                try {
                    int val = new Random().nextInt(10);
                    TimeUnit.SECONDS.sleep(val);
                    System.out.println(Thread.currentThread().getName() + "停留了" + val + "秒");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //释放资源（离开）
                try {
                    TimeUnit.SECONDS.sleep(2);
                    semaphore.release();
                    System.out.println(Thread.currentThread().getName() + "离开停车场");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },"car[" + i + "]");
            car[i].start();
        }
    }
}
