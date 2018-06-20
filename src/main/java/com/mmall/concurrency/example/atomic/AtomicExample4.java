package com.mmall.concurrency.example.atomic;

import com.mmall.concurrency.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.Stream;

/**
 * 线程安全:
 * 	不管运行时采用何种调度方式,并且在主调代码中不需要额外的协同或者同步,都能表现出正确的行为
 */
@Slf4j
@ThreadSafe
/**
 * AtomicReference AtomicReferenceFieldUpdater
 */
public class AtomicExample4 {
	private static AtomicReference<Integer> count = new AtomicReference<>(0);
	public static void main(String[] args) {
		count.compareAndSet(0,2);
		count.compareAndSet(0,1);
		count.compareAndSet(2,4);
		count.compareAndSet(6,1);
		count.compareAndSet(5,2);
		count.compareAndSet(4,3);
		count.compareAndSet(2,42);
		count.compareAndSet(2,2);
		log.info("count:{}",count.get());
	}
}

