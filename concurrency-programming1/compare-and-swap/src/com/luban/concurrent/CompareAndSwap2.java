package com.luban.concurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @Author 李非凡
 * @Description:
 * @Date 2019/1/12 15:20
 * @Version 1.0
 */
public class CompareAndSwap2 {

    /**
     *
     */
    private static volatile int number = 0;

    /**
     * 原子性的整型
     */
    private static AtomicInteger atomicInteger = new AtomicInteger(100);

    /**
     * 带版本号（时间戳）的原子类型
     */
    private static AtomicStampedReference atomicStampedReference = new AtomicStampedReference(100, 1);

    public static void main(String[] args) {
        Thread thread1 = new Thread(()->{
            System.out.println(atomicInteger.compareAndSet(100, 110));
        });
//        thread1.start();

        Thread thread2 = new Thread(()->{
            try {
                //延迟2秒等thread1线程执行完毕
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(atomicInteger.compareAndSet(110, 100));
        });
//        thread2.start();

        Thread thread3 = new Thread(()->{
            try {
                //延迟3秒等thread2线程执行完毕
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(atomicInteger.compareAndSet(100, 120));
        });
//        thread3.start();

        System.out.println("==========================================");

        Thread thread4 = new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(atomicStampedReference.compareAndSet(100,
                                                                    110,
                                                                    atomicStampedReference.getStamp(),
                                                                    atomicStampedReference.getStamp()+1));
            System.out.println(atomicStampedReference.compareAndSet(110,
                                                                    100,
                                                                    atomicStampedReference.getStamp(),
                                                                    atomicStampedReference.getStamp()+1));
        });

        Thread thread5 = new Thread(()->{
            //获取版本号
            int stamp = atomicStampedReference.getStamp();
            try {
                TimeUnit.SECONDS.sleep(4);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(atomicStampedReference.compareAndSet(100,
                                                                    120,
                                                                    stamp,
                                                                    stamp+1));
        });
        thread4.start();
        thread5.start();
    }
}
