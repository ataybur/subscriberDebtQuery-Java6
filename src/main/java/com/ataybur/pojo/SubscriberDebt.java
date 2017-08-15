package com.ataybur.pojo;

import java.util.Date;

public class SubscriberDebt {
    private String subscriberNumber;
    private Double debt;
    private Date expiredDay;
    private String period;
    private String receiptNo;

    public SubscriberDebt(String subscriberNumber, Double debt, Date expiredDay, String period, String receiptNo) {
	super();
	this.subscriberNumber = subscriberNumber;
	this.debt = debt;
	this.expiredDay = expiredDay;
	this.period = period;
	this.receiptNo = receiptNo;
    }

    public String getSubscriberNumber() {
	return subscriberNumber;
    }

    public Double getDebt() {
	return debt;
    }

    public Date getExpiredDay() {
	return expiredDay;
    }

    public String getPeriod() {
	return period;
    }

    public String getReceiptNo() {
	return receiptNo;
    }
}
