package com.luban.concurrent;

/**
 * @Author 李非凡
 * @Description:
 * 叫号机
 * @Date 2019/2/1 12:47
 * @Version 1.0
 */
public class TicketRobertDemo extends Thread{

    /**
     * 叫号开始的位置，应该是所有线程共享的变量，所以设置成静态变量
     */
    private static int index = 1;

    /**
     * 叫号的最大人数
     */
    private static final int MAX = 5000;

    /**
     * 线程运行的方法
     */
    @Override
    public void run() {
        // 加上同步块，可以防止重号，跳号，超过最大值等现象。特性：独占性，排他性，互斥性，可见性
        synchronized (this){
            while (index <= MAX){
                System.out.println(Thread.currentThread().getName() + "叫到号码是：" + (index++));
            }
        }
    }

    public static void main(String[] args) {
        TicketRobertDemo t1 = new TicketRobertDemo();
        TicketRobertDemo t2 = new TicketRobertDemo();
        TicketRobertDemo t3 = new TicketRobertDemo();
        TicketRobertDemo t4 = new TicketRobertDemo();
        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}
