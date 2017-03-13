package com.ataybur.components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JTextField;

import com.ataybur.constants.GuiConstants;
import com.ataybur.pojo.SubscriberDebt;
import com.ataybur.utils.CustomMap;

public class OkButton extends JButton {

    private static final long serialVersionUID = 3639376656981329143L;
    
    public OkButton(final SubscriberTableModel model, final JTextField queryField, final CustomMap map) {
	super(GuiConstants.QUERY_BUTTON);
	addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		String input = queryField.getText();
		List<SubscriberDebt> sdList = map.get(input);
		model //
		.clear() //
			.addRow(sdList);
	    }
	});
    }

}
