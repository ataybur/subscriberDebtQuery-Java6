package com.ataybur.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileRunnable implements Runnable {

	private String fileName;
	private CustomMap map;

	public FileRunnable(String fileName, CustomMap map) {
		this.fileName = fileName;
		this.map = map;
	}

	public void run() {
		FileReader fr;
		try {
			File file = new File(fileName);
			fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String sCurrentLine;
			while ((sCurrentLine = br.readLine()) != null) {
				new LineParser(sCurrentLine, map).parseLine();
			}
			fr.close();
			br.close();
			file.delete();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	

}
