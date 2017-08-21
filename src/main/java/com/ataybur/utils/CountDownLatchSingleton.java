package com.ataybur.utils;

import java.util.concurrent.CountDownLatch;

import com.ataybur.constants.Constants;

public class CountDownLatchSingleton {
	private static CountDownLatch instance = null;
	private static Object mutex = new Object();

	private CountDownLatchSingleton() {
	}

	public static CountDownLatch getInstance() {
		if (instance == null) {
			synchronized (mutex) {
				instance = new CountDownLatch(Constants.MAX_FILE_COUNT);
			}
		}
		return instance;
	}
}
