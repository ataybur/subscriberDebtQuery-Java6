package com.ataybur.components;

import java.awt.Dimension;

import javax.swing.JTextField;

import com.ataybur.constants.GuiConstants;

public class SubscriberNumberTextField extends JTextField {
    private static final long serialVersionUID = -489647332113858360L;
    
    public SubscriberNumberTextField() {
	super(GuiConstants.SUBSCRIBER_NUMBER);
	setPreferredSize(new Dimension(150, 25));
    }

}
