package com.ataybur.constants;

public class DBConstants {
    public static final String CREATE_QUERY = " CREATE TABLE IF NOT EXISTS subscriber_debt ( " + //
	    " subscriber_number text, " + //
	    "  debt real NOT NULL, " + //
	    "  expired_day text, " + //
	    "  period text, " + //
	    "  receipt_number text " + //
	    " ) ";

    public static final String INSERT_QUERY = " INSERT INTO subscriber_debt ( " + //
	    "  subscriber_number,debt,expired_day, period, receipt_number)  " + //
	    " 	VALUES " + //
	    " 	 ( ?, ?, ?, ?, ? )";

    public static final String DELETE_QUERY = " DELETE FROM subscriber_debt ";

    public static final String SELECT_QUERY = "SELECT * FROM subscriber_debt";

    public static final String URL_TEMPLATE = "jdbc:sqlite:%s\\%s";

    public static final String DB_NAME = "customer.db";

}
