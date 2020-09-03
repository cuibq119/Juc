package com.cuibq.juc;

import java.util.LinkedList;

public class Test_10Producer_2Consumer<T> {

	final private LinkedList<T> list = new LinkedList<>();
	final private int MAX = 10;
	private int count = 0;

	public synchronized void put(T t) {
		while (MAX == count) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		list.add(t);
		System.out.println("put:" + t.toString());
		++count;
		this.notifyAll();
	}

	public synchronized T get() {
		T t = null;
		while (count == 0) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		t = list.removeFirst();
		System.out.println("get:" + t.toString());
		--count;
		this.notifyAll();
		return t;
	}

	synchronized int getCount() {
		return count;
	}

	public static void main(String[] args) {
		Test_10Producer_2Consumer<String> test = new Test_10Producer_2Consumer<>();

		for (int i = 0; i < 2; i++) {
			new Thread(() -> {
				for (int j = 0; j < 50; j++) {
					test.put(Thread.currentThread().getName() + ":"+ j);
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}).start();
		}

		for (int i = 0; i < 10; i++) {
			new Thread(() -> {
				for (int j = 0; j < 10; j++) {
					test.get();
					try {
						Thread.sleep(200);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}).start();
		}
	}

}
