package com.mmall.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;
import java.util.stream.Stream;

@Slf4j
public class CyclicBarrierExample2 {

	private static CyclicBarrier barrier = new CyclicBarrier(5);
	public static void main(String[] args) {
		ExecutorService executorService = Executors.newCachedThreadPool();
		Stream.iterate(0,n->n+1)
				.limit(10)
				.forEach(e->{
					try {
						Thread.sleep(1000);
					}catch (Exception aa){
						aa.printStackTrace();
					}
					executorService.execute(()->{
						try{
							race(e);
						}catch (Exception a){
							a.printStackTrace();
						}
					});
				});
		executorService.shutdown();

	}

	public static void race(int threadNum) throws Exception {
		Thread.sleep(1000);
		log.info("{} is ready",threadNum);
		try {
			barrier.await(2000, TimeUnit.MILLISECONDS);
		}catch (BrokenBarrierException e){

		}
		log.info("{} is continue",threadNum);
	}
}
