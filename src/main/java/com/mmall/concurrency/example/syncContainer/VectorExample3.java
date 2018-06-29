package com.mmall.concurrency.example.syncContainer;

import lombok.extern.slf4j.Slf4j;

import java.util.Iterator;
import java.util.Vector;

@Slf4j
public class VectorExample3 {
	//java.util.ConcurrentModificationException
	public static void test1(Vector<Integer> v1){
		for(Integer i : v1){
			if(i.equals(3)){
				v1.remove(i);
			}
		}
	}

	//java.util.ConcurrentModificationException
	public static void test2(Vector<Integer> v1){
		Iterator<Integer> iterator = v1.iterator();
		while (iterator.hasNext()){
			Integer next = iterator.next();
			if(next.equals(3)){
				v1.remove(next);
			}
		}
	}
	public static void test3(Vector<Integer> v1){
		for (int i = 0; i < v1.size(); i ++){
			if(v1.get(i).equals(3)){
				v1.remove(i);
			}
		}
	}

	public static void main(String[] args) {
		Vector<Integer> v1 = new Vector<>();
		v1.add(1);
		v1.add(2);
		v1.add(3);
		test3(v1);
	}
}
