package com.ataybur.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import com.ataybur.constants.Constants;
import com.ataybur.constants.MessageConstants;
import com.ataybur.enums.ColumnTypes;
import com.ataybur.pojo.SubscriberDebt;

public class FileRunnable implements Runnable {

    private String fileName;
    private CustomMap map;
    private Logger logger = Logger.getInstance();
    
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
		parseLine(sCurrentLine);
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

    private void parseLine(String line) {
	if (line != null && !line.isEmpty()) {
	    String subscriberNumber = Constants.EMPTY;
	    String debt = Constants.EMPTY;
	    String expiredDay = Constants.EMPTY;
	    String period = Constants.EMPTY;
	    String receiptNo = Constants.EMPTY;
	    ColumnTypes erroredColumnType = null;
	    try {
		erroredColumnType = ColumnTypes.SUBSCRIBER_NUMBER;
		subscriberNumber = line.substring(1, 10);
		erroredColumnType = ColumnTypes.DEBT;
		debt = line.substring(18, 33);
		erroredColumnType = ColumnTypes.EXPIRED_DAY;
		expiredDay = line.substring(33, 43);
		erroredColumnType = ColumnTypes.PERIOD;
		period = line.substring(46, 50);
		erroredColumnType = ColumnTypes.RECEIPT_NO;
		receiptNo = line.substring(50, 61);
	    } catch (Exception e) {
		logger.addLog(MessageConstants.FIELD_LOG_TEMPLATE, erroredColumnType.getName(),line);
		return;
	    }
	    Double debtDouble = 0d;
	    try {
		debtDouble = Double.valueOf(debt);
	    } catch (Exception e) {
		logger.addLog(MessageConstants.FIELD_VALUE_LOG_TEMPLATE, ColumnTypes.DEBT.getName(),debt,line);
		return;
	    }
	    Date expiredDayDate;
	    try {
		expiredDayDate = new DateFormatter().parse(expiredDay);
	    } catch (ParseException e) {
		logger.addLog(MessageConstants.FIELD_VALUE_LOG_TEMPLATE, ColumnTypes.EXPIRED_DAY.getName(),expiredDay,line);
		return;
	    }
	    SubscriberDebt subscriberDebt = new SubscriberDebt(subscriberNumber, debtDouble, expiredDayDate, period, receiptNo);
	    new MapHelper(map).addElement(subscriberDebt);
	}
    }

}
