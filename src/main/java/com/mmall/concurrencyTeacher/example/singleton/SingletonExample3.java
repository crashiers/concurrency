package com.mmall.concurrencyTeacher.example.singleton;

import com.mmall.concurrency.annoations.NotRecommend;
import com.mmall.concurrency.annoations.ThreadSafe;

/**
 * 懒汉模式
 * 单例实例在第一次使用时进行创建
 */
@ThreadSafe
@NotRecommend
public class SingletonExample3 {

    // 私有构造函数
    private SingletonExample3() {

    }

    // 单例对象
    private static com.mmall.concurrency.example.singleton.SingletonExample3 instance = null;

    // 静态的工厂方法
    public static synchronized com.mmall.concurrency.example.singleton.SingletonExample3 getInstance() {
        if (instance == null) {
            instance = new com.mmall.concurrency.example.singleton.SingletonExample3();
        }
        return instance;
    }
}
