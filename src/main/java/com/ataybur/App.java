package com.ataybur;

import java.awt.Container;
import java.awt.FlowLayout;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.ataybur.components.DeleteFromDB;
import com.ataybur.components.ExportButton;
import com.ataybur.components.ImportFromDB;
import com.ataybur.components.InsertToDBButton;
import com.ataybur.components.OpenButton;
import com.ataybur.components.SubscriberNumberTextField;
import com.ataybur.components.SubscriberTable;
import com.ataybur.components.SubscriberTableModel;
import com.ataybur.constants.GuiConstants;
import com.ataybur.utils.CustomMap;
import com.ataybur.utils.DBHelper;
import com.ataybur.utils.ExceptionHandler;

/**
 * @author Burak ATAY
 * @since 13.03.2017
 * */
public class App extends JFrame {

    private static final long serialVersionUID = 2515960725434180468L;

    private volatile CustomMap map = new CustomMap();

    public App() {
	super(GuiConstants.TITLE);
	setSize(900, 400);
	setDefaultCloseOperation(EXIT_ON_CLOSE);
	Container c = getContentPane();
	c.setLayout(new FlowLayout());
	SubscriberTableModel model = new SubscriberTableModel();
	JTable table = new SubscriberTable(model);
	JScrollPane scrollPane = new JScrollPane(table);
	JTextField queryField = new SubscriberNumberTextField(model, map);
	JButton openButton = new OpenButton(this, map, model);
	JButton exportButton = new ExportButton(this, model);
	JButton insertToDBButton = new InsertToDBButton(map);
	JButton importFromDB = new ImportFromDB(map, model);
	JButton deleteFromDB = new DeleteFromDB();
	c.add(openButton);
	c.add(queryField);
	c.add(exportButton);
	c.add(insertToDBButton);
	c.add(importFromDB);
	c.add(deleteFromDB);
	c.add(scrollPane);
    }

    public static void main(String args[]) {
	App app = new App();
	try {
	    new DBHelper() //
		    .createSqlLiteDB() //
		    .closeAll();
	} catch (SQLException e) {
	    new ExceptionHandler(e).handle();
	} catch (Exception e) {
	    new ExceptionHandler(e).handle();
	}
	app.setVisible(true);
    }
}
