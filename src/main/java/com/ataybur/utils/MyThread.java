package com.ataybur.utils;

import java.io.RandomAccessFile;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import com.ataybur.constants.Constants;
import com.ataybur.utils.concurrency.MyThreadLocal;

public class MyThread extends Thread {
	// volatile StringBuffer stringBuffer = new StringBuffer();

	final int length;
	public static AtomicInteger counter = new AtomicInteger(0);
	private MyThreadLocal fileLocal;
	private AtomicLong lineCountTotal;

	public MyThread(RandomAccessFile i, int index, long lineCountTotal) {
		super();
		AtomicInteger indexAI = new AtomicInteger(index * Constants.PART_SIZE);
		this.length = Constants.PART_SIZE - System.lineSeparator().getBytes().length;
		this.fileLocal = new MyThreadLocal(i, indexAI, this);
		this.lineCountTotal = new AtomicLong(lineCountTotal);
		System.out.println("this.index: " + indexAI + ", this.length: " + this.length);
	}

	@Override
	public void run() {
		// synchronized (i) {

		AtomicLong initialValue = new AtomicLong(0);
		while (this.lineCountTotal.get() != initialValue.getAndIncrement()) {
			String result = fileLocal.get();
			System.out.println("last index: "+initialValue.get()+", read: " + result);
			System.out.println("counter: " + counter.incrementAndGet());
		}
		CountDownLatchSingleton.getInstance().countDown();
	}

	public RandomAccessFile getI() {
		return fileLocal.getI();
	}
}


