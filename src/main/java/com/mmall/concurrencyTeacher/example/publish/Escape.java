package com.mmall.concurrencyTeacher.example.publish;

import com.mmall.concurrency.annotations.NotRecommend;
import com.mmall.concurrency.annotations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NotThreadSafe
@NotRecommend
public class Escape {

    private int thisCanBeEscape = 0;

    public Escape () {
        new InnerClass();
    }

    private class InnerClass {

        public InnerClass() {
            log.info("{}", thisCanBeEscape);
        }
    }

    public static void main(String[] args) {
        new com.mmall.concurrency.example.publish.Escape();
    }
}
