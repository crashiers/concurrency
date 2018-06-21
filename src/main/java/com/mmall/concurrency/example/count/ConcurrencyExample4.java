package com.mmall.concurrency.example.count;

import com.mmall.concurrency.annotations.NotThreadSafe;
import com.mmall.concurrency.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.stream.Stream;

@Slf4j
@NotThreadSafe
public class ConcurrencyExample4 {
	//请求总数
	public static int clientTotal = 5000;

	public static int threadTotal = 200;

	public static volatile int count = 0;

	public static void main(String[] args) throws InterruptedException {
		ExecutorService executorService = Executors.newCachedThreadPool();
		//信号量
		final Semaphore semaphore = new Semaphore(threadTotal);
		//计数器闭锁
		final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
		Stream.iterate(0,n->n+1)
				.limit(clientTotal)
				.forEach(e->{
					executorService.execute(()->{
						try {
							semaphore.acquire();
							add();
							semaphore.release();
						} catch (Exception e1) {
							log.error("exception",e);
						}
						countDownLatch.countDown();
					});
				});
		countDownLatch.await();
		executorService.shutdown();
		log.info("count:{}",count);
	}
	public static  void add(){
		count ++;
	}
}

