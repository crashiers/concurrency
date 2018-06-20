package com.mmall.concurrency.example.atomic;

import com.mmall.concurrency.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.Stream;

/**
 * 线程安全:
 * 	不管运行时采用何种调度方式,并且在主调代码中不需要额外的协同或者同步,都能表现出正确的行为
 */
@Slf4j
@ThreadSafe
/**
 * LongAdder
 */
public class AtomicExample3 {
	//请求总数
	public static int clientTotal = 5000;

	public static int threadTotal = 200;

	/**
	 * LongAdder - AtomicLong
	 * ceil     -  do-while
	 */
	public static LongAdder count = new LongAdder();

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
	public static void add(){
		count.increment();
	}
}

