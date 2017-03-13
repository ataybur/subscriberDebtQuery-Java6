package com.ataybur.utils;

import javax.swing.JOptionPane;

import com.ataybur.constants.MessageConstants;

public class ExceptionHandler {
    private Exception instance;

    public ExceptionHandler(Exception instance) {
	this.instance = instance;
    }
    
    public void handle(){
	JOptionPane.showMessageDialog(null, instance.getMessage(), MessageConstants.ERROR, JOptionPane.ERROR_MESSAGE);
	instance.printStackTrace();
    }
}
