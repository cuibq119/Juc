package com.cuibq.juc;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class T06_CAS {

	AtomicInteger count = new AtomicInteger();
	
	void m() {
		for(int i = 0;i<10000;i++) {
			count.incrementAndGet();
		}
	}
	
	public static void main(String[] args) {
		T06_CAS t = new T06_CAS();
		List<Thread> threads = new ArrayList<>();
		for(int i=0;i<10; i++){
			threads.add(new Thread(t::m,"thread-"+i));
		}
		
		threads.forEach((o) -> o.start());
		
		threads.forEach((o) -> {
			try {
				o.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		
		System.out.println(t.count);
	}
}
