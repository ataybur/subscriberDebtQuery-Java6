package com.ataybur.components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import com.ataybur.App;
import com.ataybur.constants.Constants;
import com.ataybur.constants.GuiConstants;
import com.ataybur.constants.MessageConstants;
import com.ataybur.utils.CustomMap;
import com.ataybur.utils.ExceptionHandler;
import com.ataybur.utils.FileSplitter;
import com.ataybur.utils.Logger;

public class OpenButton extends JButton {
    private static final long serialVersionUID = 4998596640891773199L;
    public OpenButton(final App app, final CustomMap map) {
	super(GuiConstants.OPEN_BUTTON);
	addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent ae) {
		JFileChooser chooser = new JFileChooser();
		chooser.setMultiSelectionEnabled(false);
		int option = chooser.showOpenDialog(app);
		if (option == JFileChooser.APPROVE_OPTION) {
		    File file = chooser.getSelectedFile();
		    try {
			new FileSplitter(file) //
				.split() //
				.read(map);
			Logger.getInstance().write();
			JOptionPane.showMessageDialog(null, String.format(MessageConstants.INSERTED_RECORDS_COUNT, map.getLineCounter() , System.getProperty(Constants.LINE_SEPERATOR), map.size()));
		    } catch (IOException e) {
			new ExceptionHandler(e).handle();
		    }
		}
	    }
	});
    }
}
