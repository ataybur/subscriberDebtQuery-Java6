package com.ataybur.utils;

import java.util.List;
import java.util.Map.Entry;

import javax.swing.JOptionPane;

import com.ataybur.components.SubscriberTableModel;
import com.ataybur.constants.Constants;
import com.ataybur.constants.MessageConstants;
import com.ataybur.pojo.SubscriberDebt;

public class SubscriberTableModelHelper {
    private SubscriberTableModel instance;

    public SubscriberTableModelHelper(SubscriberTableModel instance) {
	this.instance = instance;
    }

    public SubscriberTableModelHelper addMapToRowList(CustomMap map) {
	instance //
	.clear();
	for (Entry<String, List<SubscriberDebt>> entry : map.entrySet()) {
	    instance //
	    .addRow(entry.getValue());
	}
	return this;
    }

    public SubscriberTableModelHelper addRow(List<SubscriberDebt> list) {
	instance //
	.clear() //
		.addRow(list);
	return this;
    }

    public void showContentDialog() {
	List<SubscriberDebt> list = instance.getList();
	Integer countRecord = list.size();
	Double totalDebt = 0D;
	for (SubscriberDebt subscriberDebt : list) {
	    totalDebt += subscriberDebt.getDebt();
	}
	JOptionPane.showMessageDialog(null, String.format(MessageConstants.FOUNDED_RECORDS_COUNT, countRecord, Constants.SYS_LINE_SEPERATOR, totalDebt));
    }
}
