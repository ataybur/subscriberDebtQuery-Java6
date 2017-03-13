package com.ataybur.utils;

import com.ataybur.enums.ColumnTypes;
import com.ataybur.pojo.SubscriberDebt;

public class SubscriberDebtColumnHelper {
    private SubscriberDebt instance;

    public SubscriberDebtColumnHelper(SubscriberDebt instance) {
	super();

	this.instance = instance;
    }

    public Object columnSupplier(int column) {
	Object result;
	if (column == ColumnTypes.SUBSCRIBER_NUMBER.getValue()) {
	    result = this.instance.getSubscriberNumber();
	} else if (column == ColumnTypes.DEBT.getValue()) {
	    result = this.instance.getDebt();
	} else if (column == ColumnTypes.EXPIRED_DAY.getValue()) {
	    result = this.instance.getExpiredDay();
	} else if (column == ColumnTypes.PERIOD.getValue()) {
	    result = this.instance.getPeriod();
	} else if (column == ColumnTypes.RECEIPT_NO.getValue()) {
	    result = this.instance.getReceiptNo();
	} else {
	    throw new UnsupportedOperationException();
	}
	return result;
    }

}
