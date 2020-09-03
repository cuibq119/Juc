package com.cuibq.juc;

public class T03_Sleep_Yield_Join {

	public static void main(String[] args) {
		
	}
	
	static void testSleep() {
		new Thread(() -> {
			for(int i=0;i<10;i++) {
				try {
					Thread.sleep(500);
				} catch(InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	static void testYield() {
		new Thread(() -> {
			for(int i=0;i<10;i++) {
				try {
					Thread.sleep(500);
				} catch(InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	static void testJoin() {
		Thread t1 = new Thread(() -> {
			for(int i=0;i<10;i++) {
				try {
					Thread.sleep(500);
					System.out.println("I am T1£¡");
				} catch(InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		Thread t2 = new Thread(() -> {
			for(int i=0;i<10;i++) {
				try {
					Thread.sleep(500);
					t1.join();
					System.out.println("I am T2£¡");
				} catch(InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
	}
	
}
