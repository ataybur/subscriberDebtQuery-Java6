package com.ataybur.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.ataybur.constants.Constants;

public class DateFormatter {

    private final ThreadLocal<DateFormat> df = new ThreadLocal<DateFormat>() {
	@Override
	protected DateFormat initialValue() {
	    return new SimpleDateFormat(Constants.DATE_FORMAT);
	}
    };

    public Date parse(String instance) throws ParseException {
	Date result;
	if (null == instance) {
	    result = null;
	} else {
	    result = df.get().parse(instance);
	}
	return result;
    }

    public String format(Date instance) {
	String result;
	if (null == instance) {
	    result = Constants.EMPTY;
	} else {
	    result = df.get().format(instance);
	}
	return result;
    }
}
