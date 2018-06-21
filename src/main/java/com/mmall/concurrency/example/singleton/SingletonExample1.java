package com.mmall.concurrency.example.singleton;

import com.mmall.concurrency.annotations.NotRecommend;
import com.mmall.concurrency.annotations.ThreadSafe;

import java.time.Instant;

//懒汉式
@ThreadSafe
@NotRecommend
public class SingletonExample1 {
	//构造私有化
	private SingletonExample1() {

	}

	//单例对象
	private static SingletonExample1 instance = null;

	public synchronized static SingletonExample1 getInstance() {
		if (instance == null) {
			instance = new SingletonExample1();
		}
		return instance;
	}
}
