package com.ataybur.components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;

import com.ataybur.constants.GuiConstants;
import com.ataybur.utils.CustomMap;
import com.ataybur.utils.DBHelper;
import com.ataybur.utils.ExceptionHandler;

public class InsertToDBButton extends JButton {

    private static final long serialVersionUID = 2448777027105508142L;
    
    public InsertToDBButton(final CustomMap map) {
	super(GuiConstants.WRITE_BUTTON);
	addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent ae) {
		try {
		    new DBHelper() //
			    .insertToDB(map) //
			    .closeAll();
		} catch (SQLException e) {
		    new ExceptionHandler(e).handle();
		}
	    }
	});
    }

}
