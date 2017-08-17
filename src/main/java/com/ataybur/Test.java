package com.ataybur;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Test {
	private static final int DEFAULT_BUFFER_SIZE = 1024 * 4;

	private static byte[] out = new StringBuffer() //
			.append("abc1").append(System.lineSeparator()) //
			.append("abc2").append(System.lineSeparator()) //
			.append("abc3").append(System.lineSeparator()) //
			.append("abc4").append(System.lineSeparator()) //
			.toString().getBytes();

	private static int length = out.length / 4;
	private static File f;

	public static void main(String[] args) {
		FileInputStream i;
		try {
			createFile();
			i = new FileInputStream(f);
			MyThread first = new MyThread(i, 0, length);
			MyThread sec = new MyThread(i, 1, length);
			MyThread th = new MyThread(i, 2, length);
			MyThread thq = new MyThread(i, 3, length);
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

class MyThread extends Thread {
	FileInputStream i;
	int index;
	final int length;

	public MyThread(FileInputStream i, int index, final int length) {
		super();
		this.i = i;
		this.index = index * length;
		this.length = length - System.lineSeparator().getBytes().length;
	}

	@Override
	public void run() {
		StringBuffer stringBuffer = new StringBuffer();
		try {
			int ind = 0;
			while (i.read() != -1) {
				if (this.length == ind) {
					break;
				}
				ind++;
				i.getChannel().position(index++);
				char ch = (char) i.read();
				stringBuffer.append(ch);
			}
			System.out.println("read: " + stringBuffer.toString());
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	public FileInputStream getI() {
		return i;
	}
}
