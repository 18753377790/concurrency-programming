package com.luban.concurrent;

/**
 * @Author 李非凡
 * @Description:
 * volatile实现状态模式（开关模式）
 * @Date 2019/6/30 13:32
 * @Version 1.0
 */
public class ShutDownDemo extends Thread{
    /**
     * 开关变量，默认是关闭
     */
    private volatile boolean started = false;

    /**
     *
     */
    @Override
    public void run() {
        while (started){
            //do work
        }
    }

    /**
     *
     */
    public void shutdown(){
        started = false;
    }
}
