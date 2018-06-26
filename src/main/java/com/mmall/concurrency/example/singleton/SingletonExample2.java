package com.mmall.concurrency.example.singleton;

import com.mmall.concurrency.annotations.NotRecommend;
import com.mmall.concurrency.annotations.ThreadSafe;

import java.util.Vector;

//饿汉式
@ThreadSafe
public class SingletonExample2 {
	//构造私有化
	private  SingletonExample2(){

	}
	//单例对象
	static {
		instance = new SingletonExample2();
	}
	private static SingletonExample2 instance = null;

	public static SingletonExample2 getInstance(){
		return instance;
	}
	public static void main(String[] args){
		System.out.println(getInstance());
		System.out.println(getInstance());
	}

}
