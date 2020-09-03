package com.cuibq.juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class T10_AQS {
	
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ThreadLocal<String> tl = new ThreadLocal<>();
		
		tl.set("123");
		
		
		Lock lock = new ReentrantLock();
		
		lock.lock();
		
		lock.unlock();
		
	}
	
}
