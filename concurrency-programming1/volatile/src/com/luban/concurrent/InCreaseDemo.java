package com.luban.concurrent;

/**
 * @Author 李非凡
 * @Description:
 * volatile不能保证原子性
 * @Date 2019/6/30 12:43
 * @Version 1.0
 */
public class InCreaseDemo {
    /**
     *
     */
    private static volatile int m = 0;

    /**
     * m自增加的方法
     * m++不具有原子性，它可以分成3步
     */
    private synchronized static void increase(){
       m++;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                for (int j = 0; j < 5; j++) {
                    increase();
                }
            }).start();
        }
        System.out.println(m);
    }
}
