package com.ataybur.components;

import javax.swing.JTable;

import com.ataybur.enums.ColumnTypes;
import com.ataybur.utils.DateCellRenderer;

public class SubscriberTable extends JTable {

    private static final long serialVersionUID = -1685213896537824400L;
    
    public SubscriberTable(final SubscriberTableModel model) {
	super(model);
	getColumnModel() //
		.getColumn(ColumnTypes.EXPIRED_DAY.getValue()) //
		.setCellRenderer(new DateCellRenderer());
    }

}
