package com.mmall.concurrency.example.publish;

import com.mmall.concurrency.annotations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Slf4j
@NotThreadSafe
public class UnSafePublish {
	private String [] states = {"a","b","c"};
	public String[] getStates(){
		return states;
	}

	public static void main(String[] args) {
		UnSafePublish unSafePublish = new UnSafePublish();
		log.info("{}", Arrays.toString(unSafePublish.getStates()));

		unSafePublish.getStates()[0] = "d";
		log.info("{}",Arrays.toString(unSafePublish.getStates()));
	}
}
