package com.mmall.concurrency.example.commonUnsafe;

import com.mmall.concurrency.annotations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.stream.Stream;

/**
 * @description:
 * @author: XuJinNiu
 * @create: 2018-06-25 18:46
 **/
@Slf4j
@NotThreadSafe
public class StringExample1 {
    //请求总数
    public static int clientTotal = 5000;

    public static int threadTotal = 200;

    public static int count = 0;

    public static StringBuilder stringBuilder = new StringBuilder();

    public static StringBuffer stringBuffer = new StringBuffer();

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        //信号量
        final Semaphore semaphore = new Semaphore(threadTotal);
        //计数器闭锁
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        Stream.iterate(0, n -> n + 1)
                .limit(clientTotal)
                .forEach(e -> {
                    executorService.execute(() -> {
                        try {
                            semaphore.acquire();
//                            add();
                            update();
                            updtae1();
                            semaphore.release();
                        } catch (Exception e1) {
                            log.error("exception", e);
                        }
                        countDownLatch.countDown();
                    });
                });
        countDownLatch.await();
        executorService.shutdown();
        log.info("StringBuilder:{}", stringBuilder.length());
        log.info("StringBuffer:{}", stringBuffer.length());
    }

    public static void add() {
        count++;
    }

    public static void update() {
        stringBuilder.append("1");

    }
    public static void updtae1(){
        stringBuffer.append("1");
    }
}
