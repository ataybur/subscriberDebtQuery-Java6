package com.ataybur.components;

import java.awt.Dimension;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.ataybur.constants.GuiConstants;
import com.ataybur.pojo.SubscriberDebt;
import com.ataybur.utils.CustomMap;
import com.ataybur.utils.SubscriberTableModelHelper;

public class SubscriberNumberTextField extends JTextField {
    private static final long serialVersionUID = -489647332113858360L;

    public SubscriberNumberTextField(final SubscriberTableModel model, final CustomMap map) {
	super(GuiConstants.SUBSCRIBER_NUMBER);
	setPreferredSize(new Dimension(150, 25));
	getDocument().addDocumentListener(new DocumentListener() {
	    public void changedUpdate(DocumentEvent e) {
		warn();
	    }

	    public void removeUpdate(DocumentEvent e) {
		warn();
	    }

	    public void insertUpdate(DocumentEvent e) {
		warn();
	    }

	    public void warn() {
		String input = getText();
		SubscriberTableModelHelper helper = new SubscriberTableModelHelper(model);
		if (null == input || input.isEmpty()) {
		    helper.addMapToRowList(map);
		} else {
		    List<SubscriberDebt> sdList = map.get(input);
		    helper.addRow(sdList);
		}
		helper.showContentDialog();
	    }
	});
    }

}
