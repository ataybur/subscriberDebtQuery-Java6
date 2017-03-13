package com.ataybur.utils;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import com.ataybur.pojo.SubscriberDebt;

public class CustomMap implements ConcurrentMap<String, List<SubscriberDebt>> {
    private ConcurrentMap<String, List<SubscriberDebt>> map;
    private Long lineCounter;

    public CustomMap() {
	map = new ConcurrentHashMap<String, List<SubscriberDebt>>();
	lineCounter = 0L;
    }

    public void clear() {
	map.clear();
    }

    public boolean containsKey(Object key) {
	return map.containsKey(key);
    }

    public boolean containsValue(Object value) {
	return map.containsValue(value);
    }

    public Set<java.util.Map.Entry<String, List<SubscriberDebt>>> entrySet() {
	return map.entrySet();
    }

    public List<SubscriberDebt> get(Object key) {
	return map.get(key);
    }

    public boolean isEmpty() {
	return map.isEmpty();
    }

    public Set<String> keySet() {
	return map.keySet();
    }

    public synchronized List<SubscriberDebt> put(String key, List<SubscriberDebt> value) {
	lineCounter += value.size();
	return map.put(key, value);
    }

    public void putAll(Map<? extends String, ? extends List<SubscriberDebt>> m) {
	map.putAll(m);
    }

    public List<SubscriberDebt> remove(Object key) {
	return map.remove(key);
    }

    public int size() {
	return map.size();
    }

    public Collection<List<SubscriberDebt>> values() {
	return map.values();
    }

    public List<SubscriberDebt> putIfAbsent(String key, List<SubscriberDebt> value) {
	return map.putIfAbsent(key, value);
    }

    public boolean remove(Object key, Object value) {
	return map.remove(key, value);
    }

    public List<SubscriberDebt> replace(String key, List<SubscriberDebt> value) {
	return map.replace(key, value);
    }

    public boolean replace(String key, List<SubscriberDebt> oldValue, List<SubscriberDebt> newValue) {
	return map.replace(key, oldValue, newValue);
    }

    public Long getLineCounter() {
	return lineCounter;
    }

}
