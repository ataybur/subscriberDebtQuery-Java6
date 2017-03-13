package com.ataybur.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.ataybur.constants.Constants;

public class FileSplitter {
    private File file;

    public FileSplitter(File file) {
	this.file = file;
    }

    public MultiFileReader split() throws IOException {
	List<String> list = splitInner();
	return new MultiFileReader(list);
    }

    private List<String> splitInner() throws IOException {
	FileInputStream inputStream;
	String newFileName;
	long fileSize = file.length();
	long add = fileSize % Constants.PART_SIZE;
	long tempFileSize = fileSize;
	if (add != 0) {
	    tempFileSize += add;
	}
	long lineCountTotal = tempFileSize / Constants.PART_SIZE;
	long lineCountPerFile = 1;
	long fileCount;
	if (lineCountTotal > Constants.MAX_FILE_COUNT) {
	    fileCount = Constants.MAX_FILE_COUNT;
	} else {
	    fileCount = lineCountTotal;
	}
	lineCountPerFile = lineCountTotal / fileCount;

	int nChunks = 0, read = 0, readLength = (int) (Constants.PART_SIZE * lineCountPerFile);
	List<String> splittedFileNames = new ArrayList<String>();
	byte[] byteChunkPart;
	inputStream = new FileInputStream(file);
	String tempDir = System.getProperty(Constants.TEMP_DIR_PROPERTY);
	while (fileSize > 0) {
	    if (fileSize <= 5) {
		readLength = (int) fileSize;
	    }
	    byteChunkPart = new byte[readLength];
	    read = inputStream.read(byteChunkPart, 0, readLength);
	    fileSize -= read;
	    nChunks++;
	    newFileName = String.format(Constants.TEMP_FILE_TEMPLATE, tempDir, File.separatorChar, Integer.toString(nChunks - 1));
	    splittedFileNames.add(newFileName);
	    FileOutputStream filePart = new FileOutputStream(new File(newFileName));
	    filePart.write(byteChunkPart);
	    filePart.flush();
	    filePart.close();
	    byteChunkPart = null;
	    filePart = null;
	}
	inputStream.close();
	return splittedFileNames;
    }

}
