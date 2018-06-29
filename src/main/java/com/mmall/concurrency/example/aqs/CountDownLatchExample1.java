package com.mmall.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Slf4j
public class CountDownLatchExample1 {
	private final static int threadTotal = 200;

	public static void main(String[] args) throws Exception {
		ExecutorService executorService = Executors.newCachedThreadPool();
		final CountDownLatch countDownLatch = new CountDownLatch(threadTotal);
		for(int i = 0; i < threadTotal; i ++) {
			executorService.execute(() -> {
				try {
					test(threadTotal);
				} catch (Exception e) {
					log.error("Exception", e);
				} finally {
					countDownLatch.countDown();
				}
			});
		}
		countDownLatch.await();
		log.info("finish");
		executorService.shutdown();
	}

	private static void test(int threadNum) throws Exception {
		Thread.sleep(100);
		log.info("{}", threadNum);
		Thread.sleep(100);
	}
}
