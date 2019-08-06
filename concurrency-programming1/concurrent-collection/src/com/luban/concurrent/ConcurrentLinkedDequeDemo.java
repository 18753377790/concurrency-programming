package com.luban.concurrent;

import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * @Author 李非凡
 * @Description:
 * 非阻塞式并发集合:
 * 这类集合也包括添加和移除数据的方法。
 * 如果方法不能立即被执行，则返回null或抛出异常，但是调用这个方法的线程不会被阻塞。
 * ConcurrentLinkedDeque
 * @Date 2019/6/30 19:30
 * @Version 1.0
 */
public class ConcurrentLinkedDequeDemo {

    public static void main(String[] args) throws InterruptedException {
        ConcurrentLinkedDeque<String> list = new ConcurrentLinkedDeque<>();
        // 添加数据
        Thread[] add = new Thread[100];
        for (int i = 0; i < 100; i++) {
           add[i] = new Thread(()->{
               for (int j = 0; j < 10000; j++) {
                    list.add(Thread.currentThread().getName() + "Element" + j);
               }
           });
           add[i].start();
           // join()方法使线程执行有序
           add[i].join();
        }
        System.out.println("after add size:" + list.size());
        // 移除数据
        Thread[] poll = new Thread[100];
        for (int i = 0; i < 100; i++) {
            poll[i] = new Thread(()->{
                // 因为队列移除时，头尾都移除一次，所以只需要移除5000次
                for (int j = 0; j < 5000; j++) {
                    // 移除尾部
                    list.pollLast();
                    // 移除头部，相当于poll()
                    list.pollFirst();
                }
            });
            poll[i].start();
            poll[i].join();
        }
        System.out.println("after poll size:" + list.size());
    }
}
