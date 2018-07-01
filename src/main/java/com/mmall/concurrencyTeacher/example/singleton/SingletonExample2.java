package com.mmall.concurrencyTeacher.example.singleton;

import com.mmall.concurrency.annoations.ThreadSafe;

/**
 * 饿汉模式
 * 单例实例在类装载时进行创建
 */
@ThreadSafe
public class SingletonExample2 {

    // 私有构造函数
    private SingletonExample2() {

    }

    // 单例对象
    private static com.mmall.concurrency.example.singleton.SingletonExample2 instance = new com.mmall.concurrency.example.singleton.SingletonExample2();

    // 静态的工厂方法
    public static com.mmall.concurrency.example.singleton.SingletonExample2 getInstance() {
        return instance;
    }
}
