package com.ataybur.utils;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

import com.ataybur.constants.Constants;

public class FileWriter {
    private String fileName;

    public FileWriter(String fileName) {
	this.fileName = fileName;
    }

    public void write(String text) throws FileNotFoundException {
	PrintWriter printWriter = new PrintWriter(fileName);
	printWriter.write(text);
	printWriter.flush();
	printWriter.close();
	text = null;
    }

    public void write(List<String> list) throws FileNotFoundException {
	StringBuffer sb = new StringBuffer();
	String lineSeperator = System.getProperty(Constants.LINE_SEPERATOR);
	if (list != null) {
	    for (String item : list) {
		sb //
		.append(item) //
			.append(lineSeperator);
	    }
	}
	write(sb.toString());
    }
}
