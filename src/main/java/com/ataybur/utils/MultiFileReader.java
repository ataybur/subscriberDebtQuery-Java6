package com.ataybur.utils;

import java.util.ArrayList;
import java.util.List;

import com.ataybur.ThreadReader;

public class MultiFileReader implements ThreadReader {
    private List<String> splittedFileNames;

    public MultiFileReader(List<String> splittedFileNames) {
	this.splittedFileNames = splittedFileNames;
    }

    public void read(CustomMap map) {
	List<Thread> threads = new ArrayList<Thread>();
	for (String fileName : splittedFileNames) {
	    Runnable task = new FileRunnable(fileName, map);
	    Thread worker = new Thread(task);
	    worker.setName(fileName);
	    worker.start();
	    threads.add(worker);
	}
	try {
		CountDownLatchSingleton.getInstance().await();
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
//	int running = 0;
//	do {
//	    running = 0;
//	    for (Thread thread : threads) {
//		if (thread.isAlive()) {
//		    running++;
//		}
//	    }
//	} while (running > 0);
    }
}
