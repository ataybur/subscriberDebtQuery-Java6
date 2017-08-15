package com.ataybur.utils;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map.Entry;

import com.ataybur.constants.Constants;
import com.ataybur.constants.DBConstants;
import com.ataybur.enums.ColumnTypes;
import com.ataybur.pojo.SubscriberDebt;

public class DBHelper {
	private Connection connection;
	private String url;

	public DBHelper() {
		File dbfile = new File(Constants.DOT);
		url = String.format(DBConstants.URL_TEMPLATE, dbfile.getAbsolutePath(), DBConstants.DB_NAME);
	}

	public DBHelper openConnection() throws SQLException {
		connection = DriverManager.getConnection(url);
		return this;
	}

	public WrapperHelper createSqlLiteDB() throws SQLException {
		File file = new File(DBConstants.DB_NAME);
		if (!file.exists()) {
			connection = DriverManager.getConnection(url);
			Statement stmt = connection.createStatement();
			stmt.execute(DBConstants.CREATE_QUERY);
			return new WrapperHelper(connection);
		}
		return new WrapperHelper();
	}

	public WrapperHelper insertToDB(CustomMap map) throws SQLException {
		PreparedStatement pstmt = connection.prepareStatement(DBConstants.INSERT_QUERY);
		for (Entry<String, List<SubscriberDebt>> entry : map.entrySet()) {
			for (SubscriberDebt subscriberDebt : entry.getValue()) {
				pstmt.setString(1, subscriberDebt.getSubscriberNumber());
				pstmt.setDouble(2, subscriberDebt.getDebt());
				pstmt.setString(3, new DateFormatter().format(subscriberDebt.getExpiredDay()));
				pstmt.setString(4, subscriberDebt.getPeriod());
				pstmt.setString(5, subscriberDebt.getReceiptNo());
				pstmt.addBatch();
			}
		}
		pstmt.executeBatch();
		return new WrapperHelper(connection);
	}

	public WrapperHelper selectIntoMap(CustomMap map) throws SQLException, ParseException {
		Statement stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery(DBConstants.SELECT_QUERY);
		while (rs.next()) {
			String subscriberNumber = rs.getString(ColumnTypes.SUBSCRIBER_NUMBER.getDbName());
			Double debt = rs.getDouble(ColumnTypes.DEBT.getDbName());
			String expiredDay = rs.getString(ColumnTypes.EXPIRED_DAY.getDbName());
			Date expiredDayDate = new DateFormatter().parse(expiredDay);
			String period = rs.getString(ColumnTypes.PERIOD.getDbName());
			String receiptNumber = rs.getString(ColumnTypes.RECEIPT_NO.getDbName());
			SubscriberDebt subscriberDebt = new SubscriberDebt(subscriberNumber, debt, expiredDayDate, period,
					receiptNumber);
			new MapHelper(map).addElement(subscriberDebt);
		}
		return new WrapperHelper(connection, rs);
	}

	public WrapperHelper deleteDB() throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement(DBConstants.DELETE_QUERY);
		preparedStatement.executeUpdate();
		return new WrapperHelper(connection);
	}
}
