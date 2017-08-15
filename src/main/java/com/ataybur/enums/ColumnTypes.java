package com.ataybur.enums;


public enum ColumnTypes {
    SUBSCRIBER_NUMBER(0, "Subscriber Number", "subscriber_number", String.class), //
    DEBT(1, "Debt","debt", Double.class),  //
    EXPIRED_DAY(2, "Expire Day","expired_day",String.class),  //
    PERIOD(3, "Period","period",String.class),  //
    RECEIPT_NO(4, "Receipt Number","receipt_number",String.class);

    private int value;
    private String name;
    private String dbName;
    private Class<?> clazz;

    private ColumnTypes(int value, String name, String dbName, Class<?> clazz) {
	this.value = value;
	this.name = name;
	this.dbName = dbName;
	this.clazz = clazz;
    }

    public int getValue() {
	return value;
    }

    public String getName() {
	return name;
    }

    public Class<?> getClazz() {
	return clazz;
    }
    
    public String getDbName() {
	return dbName;
    }
    
    public static ColumnTypes getByValue(int value) {
	ColumnTypes result = null;
	for (ColumnTypes columnTypes : values()) {
	    if (columnTypes.getValue() == value) {
		result = columnTypes;
	    }
	}
	if(result == null){
	    throw new UnsupportedOperationException();
	}
	return result;
    }

}
