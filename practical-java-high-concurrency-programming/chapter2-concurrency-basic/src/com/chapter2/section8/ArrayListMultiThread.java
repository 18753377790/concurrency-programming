package com.chapter2.section8;

import java.util.ArrayList;

/**
 * @Author 李非凡
 * @Description:
 * 并发下的ArrayList
 * @Date 2019/8/3 18:02
 * @Version 1.0
 */
public class ArrayListMultiThread {

    /**
     *
     */
    private static ArrayList<Integer> list = new ArrayList<Integer>();

    /**
     *
     */
    public static class AddThread implements Runnable{
        /**
         *
         */
        @Override
        public void run() {
            for (int i = 0; i < 1000000; i++) {
                list.add(i);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(new AddThread());
        Thread thread2 = new Thread(new AddThread());
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(list.size());
    }
}
