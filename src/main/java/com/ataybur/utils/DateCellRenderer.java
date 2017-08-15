package com.ataybur.utils;

import java.util.Date;

import javax.swing.table.DefaultTableCellRenderer;

public class DateCellRenderer extends DefaultTableCellRenderer {
    private static final long serialVersionUID = -1395957450137575681L;

    public DateCellRenderer() { super(); }

    public void setValue(Object value) {
        setText(new DateFormatter().format((Date) value));
    }
}