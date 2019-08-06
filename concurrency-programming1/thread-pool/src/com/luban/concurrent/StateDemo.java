package com.luban.concurrent;

/**
 * @Author 李非凡
 * @Description:TODE
 * @Date 2019/7/2 16:26
 * @Version 1.0
 */
public class StateDemo {
    // 这个数是29位，32位的Integer最高三位是状态码，后面29位是线程的数量
    private static final int COUNT_BITS = Integer.SIZE - 3;
    private static final int CAPACITY   = (1 << COUNT_BITS) - 1;

    // runState is stored in the high-order bits
    private static final int RUNNING    = -1 << COUNT_BITS;// 运行状态 111
    private static final int SHUTDOWN   =  0 << COUNT_BITS;// 关闭状态 000
    private static final int STOP       =  1 << COUNT_BITS;// 停止状态 001
    private static final int TIDYING    =  2 << COUNT_BITS;// 整理状态 010
    private static final int TERMINATED =  3 << COUNT_BITS;// 终止状态 011
    public static void main(String[] args) {
        // 输出数值和数值的二进制
        System.out.println(COUNT_BITS+":"+Integer.toBinaryString(COUNT_BITS));
        System.out.println(CAPACITY+":"+Integer.toBinaryString(CAPACITY));
        System.out.println(RUNNING+":"+Integer.toBinaryString(RUNNING));
        System.out.println(SHUTDOWN+":"+Integer.toBinaryString(SHUTDOWN));
        System.out.println(STOP+":"+Integer.toBinaryString(STOP));
        System.out.println(TIDYING+":"+Integer.toBinaryString(TIDYING));
        System.out.println(TERMINATED+":"+Integer.toBinaryString(TERMINATED));
    }
}
