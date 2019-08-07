package com.concurrency.chapter1;

/**
 * @Author 李非凡
 * @Description:
 * long型变量的原子性
 * 在32位的操作系统中，64位的long型数据是没有原子性的
 * @Date 2019/8/5 9:32
 * @Version 1.0
 */
public class MultiThreadLong {

    /**
     *
     */
    private static long t = 0;

    /**
     * 修改long值
     */
    public static class ChangeT implements Runnable{
        private long to;

        public ChangeT(long to){
            this.to = to;
        }

        @Override
        public void run() {
            while (true){
                MultiThreadLong.t = to;
                Thread.yield();
            }
        }
    }

    /**
     * 读取long值
     */
    public static class ReadT implements Runnable{

        @Override
        public void run() {
            while (true){
                long tmp = MultiThreadLong.t;
                if (tmp != 111L && tmp != -999L && tmp != 333L && tmp != -444L){
                    System.out.println(tmp);
                }
                Thread.yield();
            }
        }
    }

    public static void main(String[] args) {
        new Thread(new ChangeT(111L)).start();
        new Thread(new ChangeT(-999L)).start();
        new Thread(new ChangeT(333L)).start();
        new Thread(new ChangeT(-444L)).start();
        new Thread(new ReadT()).start();
    }
}
