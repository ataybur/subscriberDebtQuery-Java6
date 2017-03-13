package com.ataybur.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import com.ataybur.constants.Constants;
import com.ataybur.constants.HTMLConstants;
import com.ataybur.enums.ColumnTypes;
import com.ataybur.pojo.SubscriberDebt;

public class HtmlWriter {
    private List<SubscriberDebt> instance;
    private String html;

    public HtmlWriter(List<SubscriberDebt> instance) {
	this.instance = instance;
    }

    public HtmlWriter create() {
	StringBuffer sb = new StringBuffer(HTMLConstants.HTML_TEMPLATE_1);
	for (ColumnTypes columnTypes : ColumnTypes.values()) {
	    String columnName = String.format(HTMLConstants.HTML_TEMPLATE_2, columnTypes.getName());
	    sb.append(columnName);
	}
	for (SubscriberDebt subscriberDebt : instance) {
	    sb.append(HTMLConstants.HTML_TEMPLATE_3);
	    String subscriberNumber = String.format(HTMLConstants.HTML_TEMPLATE_4, subscriberDebt.getSubscriberNumber());
	    String debt = String.format(HTMLConstants.HTML_TEMPLATE_4, subscriberDebt.getDebt());
	    String expiredDay = String.format(HTMLConstants.HTML_TEMPLATE_4, subscriberDebt.getExpiredDay());
	    String period = String.format(HTMLConstants.HTML_TEMPLATE_4, subscriberDebt.getPeriod());
	    String receiptNo = String.format(HTMLConstants.HTML_TEMPLATE_4, subscriberDebt.getReceiptNo());
	    sb.append(subscriberNumber);
	    sb.append(debt);
	    sb.append(expiredDay);
	    sb.append(period);
	    sb.append(receiptNo);
	}
	sb.append(HTMLConstants.HTML_TEMPLATE_5);
	html = sb.toString();
	return this;
    }

    public void write(File file) throws FileNotFoundException {
	String fileName = file.getAbsolutePath() + File.separatorChar + Constants.EXPORT_FILE_NAME;
	new FileWriter(fileName).write(html);
    }
}
