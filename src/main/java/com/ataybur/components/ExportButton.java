package com.ataybur.components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.JButton;
import javax.swing.JFileChooser;

import com.ataybur.App;
import com.ataybur.constants.Constants;
import com.ataybur.constants.GuiConstants;
import com.ataybur.utils.ExceptionHandler;
import com.ataybur.utils.HtmlWriter;

public class ExportButton extends JButton {

    private static final long serialVersionUID = -6099955497035297046L;
    
    public ExportButton(final App app, final SubscriberTableModel model) {
	super(GuiConstants.EXPORT_BUTTON);
	addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent ae) {
		try {
		    JFileChooser chooser = new JFileChooser();
		    chooser.setCurrentDirectory(new java.io.File(Constants.DOT));
		    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		    chooser.setAcceptAllFileFilterUsed(false);
		    int option = chooser.showOpenDialog(app);
		    if (option == JFileChooser.APPROVE_OPTION) {
			File file = chooser.getSelectedFile();
			new HtmlWriter(model.getList()) //
				.create() //
				.write(file);
		    }
		} catch (FileNotFoundException e) {
		    new ExceptionHandler(e).handle();
		}
	    }
	});
    }

}
