package com.luban.concurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @Author 李非凡
 * @Description:
 * 自定义锁
 * @Date 2019/1/13 9:51
 * @Version 1.0
 */
public class MyLock implements Lock {

    // 实例化帮助器
    private Helper helper = new Helper();

    /**
     * 非公共内部帮助器类
     */
    private class Helper extends AbstractQueuedSynchronizer {
        /**
         * 获取锁（独占锁，返回值是布尔类型）
         */
        @Override
        protected boolean tryAcquire(int arg) {
            int state = getState();
            // 如果state等于0，则证明能获取到锁
            if (state == 0) {
                // 利用CAS的原理修改state，保证原子性
                if (compareAndSetState(0, arg)){
                    // 设置当前线程占有资源，独占方式
                    setExclusiveOwnerThread(Thread.currentThread());
                    return true;
                }
            }
            return false;
        }

        /**
         * 释放锁
         */
        @Override
        protected boolean tryRelease(int arg) {
            //
            int state = getState() - arg;
            // 释放是否成功标记
            boolean flag = false;
            // 判断释放后是否为0
            if (state == 0){
                setExclusiveOwnerThread(null);
                return true;
            }
            // 可重入性的问题，当前已独占了资源
            setState(state);
            return false;
        }

        /**
         * 条件线程
         * @return
         */
        public Condition newConditionObject(){
            return new ConditionObject();
        }
    }

    /**
     * 获取锁，加锁的方法
     */
    @Override
    public void lock() {
        helper.acquire(1);
    }

    /**
     * 以中断的方式获取锁
     * @throws InterruptedException
     */
    @Override
    public void lockInterruptibly() throws InterruptedException {
        helper.acquireInterruptibly(1);
    }

    /**
     *
     * @return
     */
    @Override
    public boolean tryLock() {
        return helper.tryAcquire(1);
    }

    /**
     * 设置超时
     * @param time
     * @param unit
     * @return
     * @throws InterruptedException
     */
    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return helper.tryAcquireNanos(1, unit.toNanos(time));
    }

    /**
     * 释放锁
     */
    @Override
    public void unlock() {
        helper.release(1);
    }

    /**
     * 条件线程
     * @return
     */
    @Override
    public Condition newCondition() {
        return helper.newConditionObject();
    }
}
