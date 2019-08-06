package com.luban.concurrent;

import java.util.concurrent.TimeUnit;

/**
 * @Author 李非凡
 * @Description:
 *
 * @Date 2019/7/7 0:32
 * @Version 1.0
 */
public class InCreaseDemo {

    //
    private int m = 0;

    /**
     *
     * @return
     */
    public int next(){
        try {
            TimeUnit.SECONDS.sleep(2);
            return m++;
        } catch (InterruptedException e) {
            throw new RuntimeException("ERROR");
        }
    }

    public static void main(String[] args) {
        InCreaseDemo demo = new InCreaseDemo();
        Thread[] threads = new Thread[20];
        for (int i = 0; i < 20; i++) {
            threads[i] = new Thread(() ->{
                System.out.println(demo.next());
            });
            threads[i].start();
        }
    }
}
