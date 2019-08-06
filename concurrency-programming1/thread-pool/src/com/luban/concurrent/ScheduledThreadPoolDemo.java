package com.luban.concurrent;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * @Author 李非凡
 * @Description:
 * 可调度的线程池
 * @Date 2019/7/2 15:37
 * @Version 1.0
 */
public class ScheduledThreadPoolDemo {

    public static void main(String[] args) {
        // 创建一个可调度的线程池
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(5);
        // 创建10个任务给线程池
        for (int i = 0; i < 10; i++) {
            // 创建任务
            Runnable task = new TaskDemo();
            // 把任务交给线程池去执行
            pool.execute(task);
        }
        // 关闭线程池
        pool.shutdown();
    }
}
