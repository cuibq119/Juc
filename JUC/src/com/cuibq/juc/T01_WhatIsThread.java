package com.cuibq.juc;

import java.util.concurrent.TimeUnit;

public class T01_WhatIsThread {

	private static class MyThread extends Thread {

		public void run() {
				for(int i=0;i<10;i++) {
					try {
						TimeUnit.MICROSECONDS.sleep(1);
					} catch(InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("T1");
				}
		}
	}

	public static void main(String[] args) {
		new MyThread().start();
		for(int i=0;i<10;i++) {
			try {
				TimeUnit.MICROSECONDS.sleep(1);
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("main");
		}
	}
	
}
