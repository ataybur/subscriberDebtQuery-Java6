package com.ataybur.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import com.ataybur.ThreadReader;

public class SingleThreadReader implements ThreadReader {
	private File file;

	public SingleThreadReader(File file) {
		this.file = file;
	}

	@Override
	public void read(CustomMap map) {
		try {
			long start = System.currentTimeMillis();
			String line;
			InputStream fis = new FileInputStream(file);
			InputStreamReader isr = new InputStreamReader(fis, Charset.forName("UTF-8"));
			BufferedReader br = new BufferedReader(isr);
			while ((line = br.readLine()) != null) {
				new LineParser(line, map).parseLine();
			}
//			CountDownLatchSingleton.getInstance().await();
			long end = System.currentTimeMillis();
			long delta = end - start;
			System.out.println("Total2: " + delta);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
