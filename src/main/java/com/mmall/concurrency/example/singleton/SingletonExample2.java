package com.mmall.concurrency.example.singleton;

import com.mmall.concurrency.annotations.NotRecommend;
import com.mmall.concurrency.annotations.ThreadSafe;

//懒汉式
@ThreadSafe
@NotRecommend
public class SingletonExample2 {
	//构造私有化
	private SingletonExample2() {
	}

	//单例对象
	private static SingletonExample2 instance = null;

	public synchronized static SingletonExample2 getInstance() {
		if (instance == null) {
			instance = new SingletonExample2();
		}
		return instance;
	}
}
