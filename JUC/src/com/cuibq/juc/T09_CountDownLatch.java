package com.cuibq.juc;

import java.util.concurrent.CountDownLatch;

public class T09_CountDownLatch {

	private static void useingCountDownLatch() {
		Thread[] threads = new Thread[100];
		CountDownLatch latch = new CountDownLatch(threads.length);
		
		for(int i=0;i<threads.length;i++) {
			threads[i] = new Thread(()->{
				int result = 0;
				for(int j = 0;j<10000;j++) result += j;
				System.out.println("CountDownLatch:"+Thread.currentThread().getName()+":"+result);
				latch.countDown();
			}) ;
		}
		for (int i = 0; i < threads.length; i++) {
			threads[i].start();
		}
		try {
			latch.await();
		}catch (InterruptedException e){
			e.printStackTrace();
		}
		System.out.println("End latch!");
	}
	
	private static void useingJoin() {
		Thread[] threads = new Thread[100];
		
		for(int i=0;i<threads.length;i++) {
			threads[i] = new Thread(()->{
				int result = 0;
				for(int j = 0;j<10000;j++) result += j;
				System.out.println("Join:"+Thread.currentThread().getName()+":"+result);
			}) ;
		}
		
		for (int i = 0; i < threads.length; i++) {
			threads[i].start();
		}
		
		for (int i = 0; i < threads.length; i++) {
			try {
				threads[i].join();
			}catch (InterruptedException e){
				e.printStackTrace();
			}
		}
		
		System.out.println("End join!");
	}
	
	public static void main(String[] args) {
		useingCountDownLatch();
		useingJoin();
	}
	
}
