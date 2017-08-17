package com.ataybur.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.concurrent.atomic.AtomicInteger;

import com.ataybur.constants.Constants;

public class MyThread extends Thread {
	FileInputStream i;
	final int initialIndex;
	int index;
	final int length;
	final long lineCountTotal;
	public static AtomicInteger counter = new AtomicInteger(0);
	public MyThread(FileInputStream i, int index, long lineCountTotal) {
		super();
		this.i = i;
		this.index = index * Constants.PART_SIZE;
		this.lineCountTotal = lineCountTotal;
		this.initialIndex = this.index;
		this.length = Constants.PART_SIZE - System.lineSeparator().getBytes().length;
		System.out.println("this.index: " + this.index + ", this.length: " + this.length);
	}

	@Override
	public void run() {
		synchronized (i) {
			StringBuffer stringBuffer = new StringBuffer();
			System.out.println("counter: "+counter.incrementAndGet());
			try {
				int ind = 0;	
//				long localLineCountTotal = 0;
				while (i.read() != -1) {
					if (this.length == ind) {
//						index += System.lineSeparator().getBytes().length;
//						ind = 0;
//						localLineCountTotal++;
						break;
					}
//					if(localLineCountTotal == lineCountTotal){
//						break;
//					}
					ind++;
					FileChannel fileChannel = i.getChannel();
					fileChannel.position(index++);
					char ch = (char) i.read();
					stringBuffer.append(ch);
				}
				System.out.println("last index: "+index+", read: " + stringBuffer.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public FileInputStream getI() {
		return i;
	}
}
