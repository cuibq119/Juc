package com.cuibq.juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class T08_ReentrantLock {

	ReentrantLock lock = new ReentrantLock();

	void m1() {
		try {
			lock.lock();
			for (int i = 0; i < 10; i++) {
				TimeUnit.SECONDS.sleep(1);
				System.out.println(i);
				if (i == 2) {
					m2();
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	void m2() {
		try {
			lock.lock();
			System.out.println("m2 ... ");
		} finally {
			lock.unlock();
		}
	}

	void m3() {
		boolean locked = false;
		try {
			locked = lock.tryLock();
			System.out.println("m3.locked:" + locked);
			if (!locked)
				lock.lock();
			System.out.println("m3 ... ");
		} finally {
			if (!locked)
				lock.unlock();
		}
	}

	public static void main(String[] args) {
//========================================
//		T08_ReentrantLock rl = new T08_ReentrantLock();
//		new Thread(rl::m1).start();
//		try {
//			TimeUnit.SECONDS.sleep(1);
//		}catch(InterruptedException e) {
//			e.printStackTrace();
//		}
//		new Thread(rl::m3).start();

//========================================
//		Lock lock = new ReentrantLock();
//		Thread t1 = new Thread(() -> {
//			try {
//				lock.lock();
//				System.out.println("t1 strat");
//				TimeUnit.SECONDS.sleep(20);
//				System.out.println("t1 end");
//			}catch(InterruptedException e) {
//				e.printStackTrace();
//			}finally {
//				lock.unlock();
//			}
//		});
//		
//		t1.start();
//		Thread t2 = new Thread(() -> {
//			try {
//				lock.lockInterruptibly();
//				System.out.println("t2 strat");
//				TimeUnit.SECONDS.sleep(5);
//				System.out.println("t2 end");
//			}catch(InterruptedException e) {
//				System.out.println("t2 Interrupted!");
//			}finally {
//				lock.unlock();
//			}
//		});
//		t2.start();
//		try {
//			System.out.println(t2.getState());
//			TimeUnit.SECONDS.sleep(2);
//			System.out.println(t2.getState());
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		t2.interrupt();
//		try {
//			TimeUnit.SECONDS.sleep(2);
//			System.out.println(t2.getState());
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//	}

		ReentrantLockBlanceTest blanceTest = new ReentrantLockBlanceTest();
		Thread t1 = new Thread(blanceTest);
		Thread t2 = new Thread(blanceTest);
		t1.start();
		t2.start();
	}
}

class ReentrantLockBlanceTest extends Thread {
	private static ReentrantLock lock = new ReentrantLock(true);// true表示公平锁，默认为false非公平

	public void run() {
		for (int i = 0; i < 100; i++) {
			try {
				lock.lock();
				System.out.println(Thread.currentThread().getName() + "获取锁");
			} finally {
				lock.unlock();
			}
		}
	}
}