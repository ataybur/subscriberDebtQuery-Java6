package com.ataybur.utils;

import java.io.FileInputStream;
import java.io.IOException;

import com.ataybur.constants.Constants;

public class MyThread extends Thread {
	FileInputStream i;
	int index;

	public MyThread(FileInputStream i, int index) {
		super();
		this.i = i;
		this.index = index;
		
	}

	@Override
	public void run() {
		synchronized (this) {
			StringBuffer stringBuffer = new StringBuffer();
			try {
				int ind = 0;
				while (i.read() != -1) {
					if (Constants.PART_SIZE == ind) {
						break;
					}
					ind++;
					i.getChannel().position(index++);
					char ch = (char) i.read();
					stringBuffer.append(ch);
				}
				System.out.println("read: " + stringBuffer.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public FileInputStream getI() {
		return i;
	}
}
