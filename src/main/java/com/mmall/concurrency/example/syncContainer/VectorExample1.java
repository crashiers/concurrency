package com.mmall.concurrency.example.syncContainer;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.stream.Stream;

@Slf4j
public class VectorExample1 {
	//请求总数
	public static int clientTotal = 5000;

	public static int threadTotal = 200;

	public static int count = 0;

	public static Vector<Integer> vector = new Vector<>();

	public static List<Integer> list = Collections.synchronizedList(Lists.newArrayList());

	public static void main(String[] args) throws InterruptedException {
		ExecutorService executorService = Executors.newCachedThreadPool();
		//信号量
		final Semaphore semaphore = new Semaphore(threadTotal);
		//计数器闭锁
		final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
		Stream.iterate(0, n->n+1)
				.limit(clientTotal)
				.forEach(e->{
					executorService.execute(()->{
						try {
							semaphore.acquire();
							update();
							semaphore.release();
						} catch (Exception e1) {
							log.error("exception",e);
						}
						countDownLatch.countDown();
					});
				});
		countDownLatch.await();
		executorService.shutdown();
		log.info("count:{}",vector.size());
	}
	public static void add(){
		count ++;
	}

	public static void update(){
		vector.add(1);
	}
}
