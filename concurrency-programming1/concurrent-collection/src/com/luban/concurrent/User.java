package com.luban.concurrent;

/**
 * @Author 李非凡
 * @Description:
 * 用户实体类
 * @Date 2019/6/30 15:20
 * @Version 1.0
 */
public class User {
    // 用户id
    private int id;
    // 用户名
    private String name;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
