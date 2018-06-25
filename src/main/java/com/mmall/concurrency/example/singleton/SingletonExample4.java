package com.mmall.concurrency.example.singleton;

import com.mmall.concurrency.annotations.Recommend;
import com.mmall.concurrency.annotations.ThreadSafe;

//饿汉式
@ThreadSafe
@Recommend
/**
 * 枚举模式:最安全
 */
public class SingletonExample4 {
	//构造私有化
	private SingletonExample4(){

	}
	//单例对象
	static {
		instance = new SingletonExample4();
	}
	private static SingletonExample4 instance = null;

	public static SingletonExample4 getInstance(){
		return Singleton.INSTANCE.getInstace();
	}


	private enum Singleton{
		INSTANCE;

		private SingletonExample4 singleton;

		//JVM保证这个方法绝对只能调用一次
		Singleton(){
			singleton = new SingletonExample4();
		}
		public SingletonExample4 getInstace(){
			return singleton;
		}
	}

}
