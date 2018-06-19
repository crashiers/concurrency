package com.mmall.concurrency.example.atomic;

import com.mmall.concurrency.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

@Slf4j
@ThreadSafe
public class AtomicExample1 {
	//请求总数
	public static int clientTotal = 5000;

	public static int threadTotal = 200;

	private static AtomicInteger count = new AtomicInteger(1);

	public static void main(String[] args) throws InterruptedException {
		ExecutorService executorService = Executors.newCachedThreadPool();
		final Semaphore semaphore = new Semaphore(threadTotal);
		final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
		Stream.iterate(0,n->n+1)
				.limit(clientTotal)
				.forEach(e->{
					executorService.execute(()->{
						try {
							semaphore.acquire();
							add();
							semaphore.release();
						}catch (Exception a){
							a.printStackTrace();
						}
						countDownLatch.countDown();
					});
				});
		countDownLatch.await();
		executorService.shutdown();
		log.info("count:{}",count);
	}
	public static void add(){
		count.incrementAndGet();
	}
}
