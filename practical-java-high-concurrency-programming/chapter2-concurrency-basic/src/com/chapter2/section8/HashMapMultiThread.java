package com.chapter2.section8;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author 李非凡
 * @Description:
 * 并发下诡异的HashMap
 * @Date 2019/8/4 0:26
 * @Version 1.0
 */
public class HashMapMultiThread {

    /**
     *
     */
    private static Map<String, String> map = new HashMap<String, String>();

    /**
     * 
     */
    public static class AddThread implements Runnable{
        int start = 0;

        public AddThread(int start){
            this.start = start;
        }

        /**
         *
         */
        @Override
        public void run() {
            for (int i = 0; i < 100000; i++) {
                map.put(Integer.toString(i), Integer.toBinaryString(i));
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(new HashMapMultiThread.AddThread(0));
        Thread thread2 = new Thread(new HashMapMultiThread.AddThread(1));
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(map.size());
    }
}
