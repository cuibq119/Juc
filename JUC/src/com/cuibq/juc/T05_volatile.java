package com.cuibq.juc;

import java.util.ArrayList;
import java.util.List;

public class T05_volatile {

	int count = 0;
	
	void m() {
		for(int i=0;i<10000; i++) {
			count ++;
		}
	}
	
	public static void main(String[] args) {
		T05_volatile t = new T05_volatile();
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
