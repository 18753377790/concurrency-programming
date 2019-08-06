package com.luban.concurrent;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Author 李非凡
 * @Description:
 * 并发环境下
 * 非并发集合遍历的过程中不容许更新操作（增删改）
 * 否则会抛出异常
 * @Date 2019/6/30 15:23
 * @Version 1.0
 */
public class CollectionDemo {
    public static void main(String[] args) {
        List<User> list = new ArrayList<User>();
        for (int i = 0; i < 20; i++) {
            User user = new User(i, "User" + i);
            list.add(user);
        }
        Iterator<User> iterator = list.iterator();
        while (iterator.hasNext()){
            User user = iterator.next();
            if ("User6".equals(user.getName())){
                list.remove(user);
            }
        }
    }
}
