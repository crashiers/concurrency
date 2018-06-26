package com.mmall.concurrency.example.syncContainer;

import com.mmall.concurrency.annotations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Vector;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.stream.Stream;

@Slf4j
@NotThreadSafe
public class VectorExample2 {

	private static Vector<Integer> vector = new Vector<>();

	public static void main(String[] args) {
		while (true) {
			Stream.iterate(0, n -> n + 1)
					.limit(10)
					.forEach(i -> vector.add(i));
			Runnable r1 = () -> {
				Stream.iterate(0, n -> n + 1)
						.limit(10)
						.forEach(i -> vector.remove(i));
			};
			Runnable r2 = () -> {
				Stream.iterate(0, n -> n + 1)
						.limit(10)
						.forEach(i -> vector.get(i));
			};

			new Thread(r1).run();
			new Thread(r2).run();
		}
	}
}
