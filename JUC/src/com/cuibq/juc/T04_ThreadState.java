package com.cuibq.juc;

public class T04_ThreadState {

	static Thread t = new MyThread();

	static Thread t2 = new Thread(() -> {
		try {
			Thread.sleep(1000);
			System.out.println(t.getState());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	});

	static Thread t3 = new Thread(() -> {
		System.out.println(t.getState());
		t.notify();
		System.out.println(t.getState());
	});

	static class MyThread extends Thread {
		@Override
		public void run() {
			System.out.println(this.getState());

			for (int i = 0; i < 10; i++) {
				try {
					Thread.sleep(500);
					if (i == 5) {
						t2.join();
						t2.start();
					}
					if (i == 9) {
						t.wait();
						t3.start();
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				System.out.println(i);
			}
		}
	}

	public static void main(String[] args) {
		System.out.println(t.getState());

		t.start();

		try {
			t.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println(t.getState());

	}

}
