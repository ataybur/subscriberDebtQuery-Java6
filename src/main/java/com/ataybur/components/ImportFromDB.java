package com.ataybur.components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import com.ataybur.constants.Constants;
import com.ataybur.constants.GuiConstants;
import com.ataybur.constants.MessageConstants;
import com.ataybur.utils.CustomMap;
import com.ataybur.utils.DBHelper;
import com.ataybur.utils.ExceptionHandler;
import com.ataybur.utils.SubscriberTableModelHelper;

public class ImportFromDB extends JButton {

    private static final long serialVersionUID = -2984629747115677293L;

    public ImportFromDB(final CustomMap map, final SubscriberTableModel model) {
	super(GuiConstants.IMPORT_BUTTON);
	addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent ae) {
		try {
		    new DBHelper() //
			    .openConnection() //
			    .selectIntoMap(map) //
			    .closeAll();
		    new SubscriberTableModelHelper(model) //
			    .addMapToRowList(map);
		    JOptionPane.showMessageDialog(null, String.format(MessageConstants.INSERTED_RECORDS_COUNT, map.getLineCounter(), Constants.SYS_LINE_SEPERATOR, map.size()));
		} catch (SQLException e) {
		    new ExceptionHandler(e).handle();
		} catch (ParseException e) {
		    new ExceptionHandler(e).handle();
		}
	    }
	});
    }

}
