package com.mmall.concurrency.example.sync;

import lombok.extern.slf4j.Slf4j;
import sun.applet.Main;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * synchronized:
 * 	修饰代码块:作用于调用的对象
 * 	修饰方法:作用于调用的对象
 * 	修饰静态方法:作用于所有对象
 * 	修饰类:作用于所有对象
 * synchronized-Lock-Atomic
 * 	synchronized:不可中断锁,适合竞争不激烈,可读性好
 * 	Lock:可中断锁,多样化同步,竞争激烈时能维持常态
 * 	Atomic:竞争激烈时能维持常态,比Lock性能好,只能同步一个值
 */
@Slf4j
public class SynchronizedExample1 {
	//修饰一个代码块
	public void test1(int j){
		synchronized (this){
			for(int i = 0; i < 10; i ++){
				log.info("test1 {} - {}",j,i);
			}
		}
	}

	//修饰一个方法
	public synchronized void test2(int j){
		for (int i = 0; i < 10; i++) {
			log.info("test2 {} - {}",j,i);
		}
	}
	public static void main(String[] args) {
		SynchronizedExample1 example1 = new SynchronizedExample1();
		SynchronizedExample1 example2 = new SynchronizedExample1();
		ExecutorService executorService = Executors.newCachedThreadPool();
		executorService.execute(()->{
			example2.test1(1);
		});
		executorService.execute(()->{
			example1.test1(2);
		});
	}
}
