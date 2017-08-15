package com.ataybur.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Wrapper;

public class WrapperHelper {
    private Wrapper[] instance;

    public WrapperHelper(Wrapper... instance) {
	this.instance = instance;
    }

    public WrapperHelper() {
	this.instance = new Wrapper[0];
    }

    public void closeAll() throws SQLException {
	for (Wrapper wrapper : instance) {
	    if (wrapper instanceof Connection) {
		((Connection) wrapper).close();
	    } else if (wrapper instanceof ResultSet) {
		((ResultSet) wrapper).close();
	    } else if (wrapper instanceof PreparedStatement) {
		((PreparedStatement) wrapper).close();
	    } else if (wrapper instanceof Statement) {
		((Statement) wrapper).close();
	    }
	}
    }
}
