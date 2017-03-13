package com.ataybur.utils;

import java.util.ArrayList;
import java.util.List;

import com.ataybur.pojo.SubscriberDebt;

public class MapHelper {
    private CustomMap instance;

    public MapHelper(CustomMap instance) {
	this.instance = instance;
    }

    public synchronized MapHelper addElement(SubscriberDebt element) {
	String subscriberNumber = element.getSubscriberNumber();
	List<SubscriberDebt> list = this.instance.get(subscriberNumber);
	if (null == list) {
	    list = new ArrayList<SubscriberDebt>();
	}
	list.add(element);
	this.instance.put(subscriberNumber, list);
	return this;
    }
}
