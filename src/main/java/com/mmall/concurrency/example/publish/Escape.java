package com.mmall.concurrency.example.publish;

import com.mmall.concurrency.annotations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NotThreadSafe
/**
 * 发布对象:使一个对象能够被当前范围之外的代码使用
 * 对象逸出:一种错误的发布方式,当对象还没有完全构造完成时,就使他被其他线程可见
 */
public class Escape {
	private int thisCanBeEscape = 0;
	public Escape(){
		new InnerClass();
	}
	private class InnerClass{
		public InnerClass(){
			log.info("{}",Escape.this.thisCanBeEscape);
		}
	}

	public static void main(String[] args) {
		new Escape();
	}
}
