package com.luban.concurrent;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author 李非凡
 * @Description:
 * HashMap(Hashtable)结构是数组加链表，jdk1.8以后加了红黑树
 * 数组table
 * 数组的每一个元素叫桶
 * 一个桶里面存放一个链表，链表每个结点有三个元素，k，v，next
 * factor扩容因子是0.75
 * 初始容量是1<<4
 * 最大容量是1<<30，integer最大值减去8
 * 链表结点大于8时可以自动转成红黑树
 *
 * 红黑树类似平衡二叉树，时间复杂度O(logN)
 * 根节点是黑色的
 * 红色的
 *
 * ConcurrentHashMap
 * 利用Segment进行分段加锁，将table分成几段，对哪一段进行操作就锁哪一段，称为分段锁，默认是一个桶（数组元素）分一段
 * put()利用synchronized进行加锁
 * @Date 2019/7/1 1:23
 * @Version 1.0
 */
public class ConcurrentHashMapDemo {

    public static void main(String[] args) {
        ConcurrentHashMap<String, String> hashMap = new ConcurrentHashMap();

    }
}
