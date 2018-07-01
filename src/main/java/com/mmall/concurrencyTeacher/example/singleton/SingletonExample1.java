package com.mmall.concurrencyTeacher.example.singleton;

import com.mmall.concurrency.annoations.NotThreadSafe;

/**
 * 懒汉模式
 * 单例实例在第一次使用时进行创建
 */
@NotThreadSafe
public class SingletonExample1 {

    // 私有构造函数
    private SingletonExample1() {

    }

    // 单例对象
    private static com.mmall.concurrency.example.singleton.SingletonExample1 instance = null;

    // 静态的工厂方法
    public static com.mmall.concurrency.example.singleton.SingletonExample1 getInstance() {
        if (instance == null) {
            instance = new com.mmall.concurrency.example.singleton.SingletonExample1();
        }
        return instance;
    }
}
