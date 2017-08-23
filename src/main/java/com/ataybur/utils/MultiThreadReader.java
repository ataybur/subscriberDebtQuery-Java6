package com.ataybur.utils;

import java.io.IOException;
import java.util.List;

import com.ataybur.ThreadReader;

public class MultiThreadReader implements ThreadReader {
	private List<MyThread> list;

	public MultiThreadReader(List<MyThread> list) {
		this.list = list;
	}

	@Override
	public void read(CustomMap map) {
		long start = System.currentTimeMillis();
		for (MyThread worker : this.list) {
			worker.setMap(map);
			worker.start();
		}
		try {
			CountDownLatchSingleton.getInstance().await();
			long end = System.currentTimeMillis();
			long delta = end-start;
			System.out.println("Total1: "+delta);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		try {
			this.list.get(0).getI().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
