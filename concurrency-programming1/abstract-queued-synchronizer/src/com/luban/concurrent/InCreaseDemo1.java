package com.luban.concurrent;

/**
 * @Author 李非凡
 * @Description:
 * 自定义锁的使用
 * @Date 2019/7/7 0:39
 * @Version 1.0
 */
public class InCreaseDemo1 {

    //
    private MyLock lock = new MyLock();

    //
    private int m = 0;

    /**
     * @return
     */
    public int next() {
        // 获取锁
        lock.lock();
        try {
            return m++;
        } finally {
            // 释放锁
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        InCreaseDemo1 demo1 = new InCreaseDemo1();
        Thread[] threads = new Thread[20];
        for (int i = 0; i < 20; i++) {
            threads[i] = new Thread(() -> {
                System.out.println(demo1.next());
            });
            threads[i].start();
        }
    }
}
