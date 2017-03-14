package com.ataybur.components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;

import com.ataybur.constants.GuiConstants;
import com.ataybur.utils.DBHelper;
import com.ataybur.utils.ExceptionHandler;

public class DeleteFromDB extends JButton {

    private static final long serialVersionUID = 906217703373925169L;

    public DeleteFromDB() {
	super(GuiConstants.DELETE_BUTTON);
	addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent ae) {
		try {
		    new DBHelper() //
			    .openConnection() //
			    .deleteDB() //
			    .closeAll();
		} catch (SQLException e) {
		    new ExceptionHandler(e).handle();
		}
	    }
	});
    }

}
