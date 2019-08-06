package com.luban.concurrent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @Author 李非凡
 * @Description:
 * 用CountDownLatch实现航班查询功能
 * CountDownLatch使用计数器来实现线程等待，计数器初始值一般是线程数
 * 线程每运行一次计数器减1，当计数器为0时，放弃等待，执行主线程
 * @Date 2019/3/31 0:18
 * @Version 1.0
 */
public class FlightQueryDemo {
    /**
     * 航空公司集合
     */
    private static List<String> company = Arrays.asList("东方航空","南方航空","海南航空");

    /**
     * 查询结果集合
     */
    private static List<String> flightList = new ArrayList<String>();

    public static void main(String[] args) throws InterruptedException {
        String origin = "Beijing";
        String destination = "Shanghai";

        Thread[] threads = new Thread[company.size()];
        CountDownLatch latch = new CountDownLatch(company.size());

        for (int i = 0; i < threads.length; i++) {
            String name = company.get(i);
            threads[i] = new Thread(()->{
                System.out.printf("%s查询从%s到%s的机票\n",name,origin,destination);
                //随机产生票数
                int val = new Random().nextInt(10);
                try {
                    TimeUnit.SECONDS.sleep(val);
                    flightList.add(name + "--" + val);
                    System.out.printf("%s公司查询成功！\n",name);
                    latch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            threads[i].start();
        }
        latch.await();
        System.out.println("查询结果如下：");
        flightList.forEach(System.out::println);
    }
}
