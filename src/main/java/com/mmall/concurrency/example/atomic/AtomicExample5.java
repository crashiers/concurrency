package com.mmall.concurrency.example.atomic;

import com.mmall.concurrency.annotations.ThreadSafe;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * 线程安全:
 * 	不管运行时采用何种调度方式,并且在主调代码中不需要额外的协同或者同步,都能表现出正确的行为
 */
@Slf4j
@ThreadSafe
//AtomicReferenceFieldUpdater
public class AtomicExample5 {
	private static AtomicIntegerFieldUpdater<AtomicExample5> updater
			= AtomicIntegerFieldUpdater.newUpdater(AtomicExample5.class,"count");

	@Getter
	public volatile int count = 100;

	public static void main(String[] args) {
		AtomicExample5 example5 = new AtomicExample5();
		if(updater.compareAndSet(example5,100,200)){
			log.info("update success {}",example5.count);
		}
	}
}

