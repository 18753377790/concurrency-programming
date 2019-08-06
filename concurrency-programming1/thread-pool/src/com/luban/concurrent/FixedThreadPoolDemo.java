package com.luban.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author 李非凡
 * @Description:
 * 固定大小的线程池
 * 核心类是ThreadPoolExecutor
 * @Date 2019/7/1 15:05
 * @Version 1.0
 */
public class FixedThreadPoolDemo {

    public static void main(String[] args) {
        // 创建固定大小的线程池
        ExecutorService pool = Executors.newFixedThreadPool(5);
        // 创建10个任务给线程池
        for (int i = 0; i < 10; i++) {
            // 创建任务
            Runnable task = new TaskDemo();
            // 把任务交给线程池去执行
            pool.execute(task);
        }
        // 关闭线程池，进入shutdown状态
        pool.shutdown();
        // 停止线程池，进入stop状态
//        pool.shutdownNow();
        // 判断线程池是否到终止状态，如果没有就一直循环等待
        while (!pool.isTerminated()){

        }
        System.out.println("finished");
    }
}
