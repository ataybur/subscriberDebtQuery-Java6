package com.ataybur.utils;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import com.ataybur.constants.Constants;

public class Logger {
    private volatile static Logger instance = null;
    private volatile List<String> logList;
    private Logger(){
	logList = new ArrayList<String>();
    }
    
    public static Logger getInstance(){
	if(instance == null){
	    synchronized (Logger.class) {
		if (instance == null) {
			instance = new Logger();
		}
	    }
	}
	return instance;
    }
    
    public void addLog(String str, Object... args){
	String log = String.format(str, args);
	logList.add(log);
    }
    
    public void write() throws FileNotFoundException{
	new FileWriter(Constants.ERROR_LOG_FILE_NAME).write(logList);
    }
}
