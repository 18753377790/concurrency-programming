package com.chapter3.section1;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author 李非凡
 * @Description:
 * ReentrantLock实现可重入锁
 * @Date 2019/8/5 11:11
 * @Version 1.0
 */
public class ReenterLock implements Runnable{
    
    /**
     *
     */
    private static ReentrantLock lock = new ReentrantLock();

    /**
     *
     */
    private static int i = 0;

    @Override
    public void run() {
        for (int j = 0; j < 10000000; j++) {
            lock.lock();
            lock.lock();
            try {
                i++;
            }finally {
                lock.unlock();
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReenterLock tl = new ReenterLock();
        Thread thread1 = new Thread(tl);
        Thread thread2 = new Thread(tl);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(i);
    }
}
