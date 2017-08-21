package com.ataybur.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

import com.ataybur.constants.Constants;

public class FileReader {
	private File file;

	public FileReader(File file) {
		this.file = file;
	}

	public MultiThreadReader split() throws IOException {
		List<MyThread> list = splitInner();
		return new MultiThreadReader(list);
	}

	private List<MyThread> splitInner() throws IOException {
		RandomAccessFile inputStream;
		long fileSize = file.length();
		long add = fileSize % Constants.PART_SIZE;
		long tempFileSize = fileSize;
		if (add != 0) {
			tempFileSize += add;
		}
		long lineCountTotal = tempFileSize / Constants.PART_SIZE;
		long fileCount;
		if (lineCountTotal > Constants.MAX_FILE_COUNT) {
			fileCount = Constants.MAX_FILE_COUNT;
		} else {
			fileCount = lineCountTotal;
		}
		List<MyThread> splittedFileNames = new ArrayList<MyThread>();
		inputStream = new RandomAccessFile(file,"r");
		for (int i = 0; i < fileCount; i++) {
			MyThread thread = new MyThread(inputStream, i);
			splittedFileNames.add(thread);
		}
		return splittedFileNames;
	}

	
}
