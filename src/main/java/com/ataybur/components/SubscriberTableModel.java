package com.ataybur.components;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.ataybur.enums.ColumnTypes;
import com.ataybur.pojo.SubscriberDebt;
import com.ataybur.utils.SubscriberDebtColumnHelper;

public class SubscriberTableModel extends AbstractTableModel {

    private static final long serialVersionUID = 3114469218353381362L;

    private List<SubscriberDebt> list = new ArrayList<SubscriberDebt>();

    public SubscriberTableModel() {
	super();
    }

    public void addRow(List<SubscriberDebt> subscriberDebtList) {
	if (subscriberDebtList != null) {
	    list.addAll(subscriberDebtList);
	}
	fireTableRowsInserted(list.size(), list.size());
    }

    public SubscriberTableModel clear() {
	list = new ArrayList<SubscriberDebt>();
	fireTableDataChanged();
	return this;
    }

    public String getColumnName(int column) {
	return ColumnTypes.getByValue(column).getName();
    }

    public Class<?> getColumnClass(int c) {
	return ColumnTypes.getByValue(c).getClazz();
    }

    public int getRowCount() {
	return list.size();
    }

    public int getColumnCount() {
	return ColumnTypes.values().length;
    }

    public Object getValueAt(int row, int column) {
	SubscriberDebt instance = list.get(row);
	return new SubscriberDebtColumnHelper(instance) //
		.columnSupplier(column);
    }

    public List<SubscriberDebt> getList() {
	return list;
    }

}
