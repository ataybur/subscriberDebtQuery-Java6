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
		for (MyThread worker : this.list) {
			worker.start();
		}
		int running = 0;
		do {
			running = 0;
			for (Thread thread : this.list) {
				if (thread.isAlive()) {
					running++;
				}
			}
		} while (running > 0);
		try {
			this.list.get(0).getI().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
