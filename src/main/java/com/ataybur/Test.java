package com.ataybur;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.ataybur.utils.MyThread;

public class Test {
	private static final int DEFAULT_BUFFER_SIZE = 1024 * 4;
	
	private static byte[] out = new StringBuffer() //
			.append("abc1").append(System.lineSeparator()) //
			.append("abc2").append(System.lineSeparator()) //
			.append("abc3").append(System.lineSeparator()) //
			.append("abc4").append(System.lineSeparator()) //
			.toString()
			.getBytes();
	private static File f;

	public static void main(String[] args) {
		FileInputStream i;
		try {
			createFile();
			i = new FileInputStream(f);
			MyThread first = new MyThread(i, 0);
			MyThread sec = new MyThread(i, 1);
			MyThread th = new MyThread(i, 2);
			MyThread thq = new MyThread(i,3);
			first.run();
			sec.run();
			th.run();
			thq.run();
			close(i);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static long copyLarge(InputStream input, OutputStream output) throws IOException {
		byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
		long count = 0;
		int n = 0;
		while (-1 != (n = input.read(buffer))) {
			output.write(buffer, 0, n);
			count += n;
		}
		return count;
	}

	public static void createFile() throws IOException {
		f = File.createTempFile("aaa", null);
		FileOutputStream o = new FileOutputStream(f);
		o.write(out);
		o.close();
	}

	public static void close(FileInputStream i) throws IOException {
		i.close();
		f.delete();
	}
}

class MyThread1 extends Thread {
	FileInputStream i;
	int index;

	public MyThread1(FileInputStream i, int index) {
		super();
		this.i = i;
		this.index = index;
	}

	@Override
	public void run() {
		try {
			i.getChannel().position(index);
			System.out.println("read: " + (char) i.read());
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
