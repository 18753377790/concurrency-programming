package com.luban.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author 李非凡
 * @Description:
 * 可变大小的线程池
 * 核心类是ThreadPoolExecutor
 * @Date 2019/7/1 15:08
 * @Version 1.0
 */
public class CachedThreadPoolDemo {

    public static void main(String[] args) {
        // 创建可变大小的线程池
        ExecutorService pool = Executors.newCachedThreadPool();
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
