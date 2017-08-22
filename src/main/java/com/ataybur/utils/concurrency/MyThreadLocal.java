package com.ataybur.utils.concurrency;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.concurrent.atomic.AtomicInteger;

import com.ataybur.constants.Constants;
import com.ataybur.utils.MyThread;

public class MyThreadLocal extends ThreadLocal<String> {

	private FileChannel fileChannel;
	private RandomAccessFile i;
	private AtomicInteger index;
	private MyThread myThread;

	public MyThreadLocal(RandomAccessFile i, AtomicInteger index, MyThread myThread) {
		super();
		this.i = i;
		this.fileChannel = i.getChannel();
		this.index = index;
		this.myThread = myThread;
	}

	@Override
	protected String initialValue() {
		return common();
	}

	@Override
	public String get() {
		return common();
	}

	private String common() {
		String result;
		ByteBuffer byteBuffer = ByteBuffer.allocate(Constants.PART_SIZE);
		try {
			fileChannel.read(byteBuffer, index.getAndAdd(Constants.PART_SIZE));
			byteBuffer.flip();
			result = recover(byteBuffer);
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
		if (buffer.hasRemaining()) {
		} else {
			this.myThread.stop();
		}

		byte[] rawBytes = new byte[Constants.PART_SIZE];
		try {
			buffer.get(rawBytes, 0, Constants.PART_SIZE);
		} catch (java.nio.BufferUnderflowException e) {
			e.printStackTrace();
		}

		return new String(rawBytes);

	}
}
