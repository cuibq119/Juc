package com.cuibq.juc;

public class T_A1B2C4_wait_notify {

	public static void main(String[] args) throws Throwable {

		char[] c1 = "ABCDEFG".toCharArray();
		char[] c2 = "1234567".toCharArray();

		Object o = new Object();

		new Thread(() -> {
			synchronized (o) {
				for (char c : c1) {
					System.out.print(c);
					try {
						o.notify();
						o.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				o.notify();
			}
		}, "t1").start();
		new Thread(() -> {
			synchronized (o) {
				for (char c : c2) {
					System.out.print(c);
					try {
						o.notify();
						o.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				o.notify();
			}
		}, "t2").start();
	}

}
