package com.mmall.concurrency.example.atomic;

import com.mmall.concurrency.annotations.ThreadSafe;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.stream.Stream;

/**
 * 线程安全:
 * 	不管运行时采用何种调度方式,并且在主调代码中不需要额外的协同或者同步,都能表现出正确的行为
 */
@Slf4j
@ThreadSafe
/**
 * AtomicStampReference:CAS的ABA问题
 */
public class AtomicExample6 {
	private static AtomicBoolean isHappend = new AtomicBoolean(false);

	private static int clientTotal = 5000;
	private static int threadTotal = 200;

	public static void main(String[] args) throws Exception {
		ExecutorService executorService = Executors.newCachedThreadPool();
		final Semaphore semaphore = new Semaphore(threadTotal);
		final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
		Stream.iterate(0, n->n+1)
				.limit(clientTotal)
				.forEach(e->{
					executorService.execute(()->{
						try {
							semaphore.acquire();
							test();
							semaphore.release();
						}catch (Exception a){
							a.printStackTrace();
						}
						countDownLatch.countDown();
					});
				});
		countDownLatch.await();
		executorService.shutdown();
		log.info("count:{}",isHappend.get());
	}
	public static void test(){
		if(isHappend.compareAndSet(false,true)){
			log.info("execute");
		}
	}
}

