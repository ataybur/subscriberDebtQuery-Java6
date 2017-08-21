package com.ataybur.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

import com.ataybur.constants.Constants;

public class MyThread extends Thread {
	// volatile StringBuffer stringBuffer = new StringBuffer();

	final int length;
	public static AtomicInteger counter = new AtomicInteger(0);
	private MyThreadLocal fileLocal;
	private static Object mutex;

	public MyThread(RandomAccessFile i, int index) {
		super();
		AtomicInteger indexAI = new AtomicInteger(index * Constants.PART_SIZE);
		this.length = Constants.PART_SIZE - System.lineSeparator().getBytes().length;
		this.fileLocal = new MyThreadLocal(i, indexAI);
		if (this.mutex == null) {
			this.mutex = new Object();
		}
		System.out.println("this.index: " + indexAI + ", this.length: " + this.length);
	}

	@Override
	public void run() {
		// synchronized (i) {
		ConcurrentLinkedQueue<String> concurrentLinkedQueue = new ConcurrentLinkedQueue<>();
		System.out.println("counter: " + counter.incrementAndGet());
//		AtomicInteger ind = new AtomicInteger(0);
//		while (true) {
//			if (this.length == ind.get()) {
//				break;
//			}
//			ind.getAndIncrement();
//			synchronized (mutex) {
				concurrentLinkedQueue.add(fileLocal.get());
//			}
//		}
		CountDownLatchSingleton.getInstance().countDown();
		StringBuffer stringBuffer = new StringBuffer();
		concurrentLinkedQueue.forEach(stringBuffer::append);
		System.out.println("last index: , read: " + stringBuffer.toString());
	}

	public RandomAccessFile getI() {
		return fileLocal.getI();
	}
}

class MyThreadLocal extends ThreadLocal<String> {

	private FileChannel fileChannel;
	private RandomAccessFile i;
	private AtomicInteger index;

	public MyThreadLocal(RandomAccessFile i, AtomicInteger index) {
		super();
		this.i = i;
		this.fileChannel = i.getChannel();
		this.index = index;
	}

	@Override
	protected String initialValue() {
		return common();
	}

	@Override
	public String get() {
//		String result;
//		try {
//			fileChannel.position(index.getAndIncrement());
//			result = String.valueOf((char) i.read());
//		} catch (IOException e) {
//			e.printStackTrace();
//			result = "";
//		}
//		return result;
		return common();
	}

	private String common(){
		String result;
		ByteBuffer byteBuffer = ByteBuffer.allocate(Constants.PART_SIZE);
		try {
			fileChannel.read(byteBuffer, index.get());
//			.position(index.getAndIncrement());
			
//			result = String.valueOf((char) i.read());
			byteBuffer.flip();
			result = recover(byteBuffer);
//			result = byteBuffer.asCharBuffer().toString();
		} catch (IOException e) {
			e.printStackTrace();
			result = "";
		}
		return result;
	}
	
	public RandomAccessFile getI() {
		return i;
	}
	
    public String recover(ByteBuffer buffer) {
        byte[] rawBytes = new byte[Constants.PART_SIZE];
        buffer.get(rawBytes, 0, Constants.PART_SIZE);
        return new String(rawBytes);
    }

	
//	  public static void read(String filename, Persistable object) {
//	        try {
//	            // Opening RandomAccessFile for reading data
//	            RandomAccessFile store = new RandomAccessFile("tablet", "rw");
//
//	            // getting file channel
//	            FileChannel channel = store.getChannel();
//
//	            // preparing buffer to read data from file
//	            ByteBuffer buffer = ByteBuffer.allocate(1024);
//
//	            // reading data from file channel into buffer
//	            int numOfBytesRead = channel.read(buffer);
//	            System.out.println("number of bytes read : " + numOfBytesRead);
//
//	            // You need to filp the byte buffer before reading
//	            buffer.flip();
//
//	            // Recovering object
//	            object.recover(buffer);
//
//	            channel.close();
//	            store.close();
//
//	        } catch (FileNotFoundException e) {
//	            e.printStackTrace();
//	        } catch (IOException e) {
//	            e.printStackTrace();
//	        }
//	    }
}
