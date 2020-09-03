package com.cuibq.juc;

import java.util.concurrent.locks.LockSupport;

public class T_A1B2C4_wait_notify {

	static Thread t1 = null;
	static Thread t2 = null;
	
	public static void main(String[] args) {

		char[] c1 = "ABCDEFG".toCharArray();
		char[] c2 = "1234567".toCharArray();

		t1 = new Thread(() -> {
			for (char c : c1) {
				System.out.print(c);
				LockSupport.unpark(t2);
				LockSupport.park();
			}
		}, "t1");
		t2 = new Thread(() -> {
			for (char c : c2) {
				LockSupport.park();
				System.out.print(c);
				LockSupport.unpark(t1);
			}
		}, "t2");
		
		t1.start();
		t2.start();
	}

}
