package com.ataybur;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.ataybur.constants.Constants;
import com.ataybur.constants.GuiConstants;
import com.ataybur.constants.MessageConstants;
import com.ataybur.enums.ColumnTypes;
import com.ataybur.pojo.SubscriberDebt;
import com.ataybur.utils.CustomMap;
import com.ataybur.utils.DBHelper;
import com.ataybur.utils.DateCellRenderer;
import com.ataybur.utils.FileSplitter;
import com.ataybur.utils.HtmlWriter;
import com.ataybur.utils.Logger;
import com.ataybur.utils.SubscriberTableModel;

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
	final SubscriberTableModel model = new SubscriberTableModel();
	JTable table = new JTable(model);
	table //
	.getColumnModel() //
		.getColumn(ColumnTypes.EXPIRED_DAY.getValue()) //
		.setCellRenderer(new DateCellRenderer());
	JScrollPane scrollPane = new JScrollPane(table);
	final JTextField queryField = new JTextField(GuiConstants.SUBSCRIBER_NUMBER);
	queryField.setPreferredSize(new Dimension(150, 25));

	JButton openButton = new JButton(GuiConstants.OPEN_BUTTON);
	openButton.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent ae) {
		JFileChooser chooser = new JFileChooser();
		chooser.setMultiSelectionEnabled(false);
		int option = chooser.showOpenDialog(App.this);
		if (option == JFileChooser.APPROVE_OPTION) {
		    File file = chooser.getSelectedFile();
		    try {
			new FileSplitter(file) //
				.split() //
				.read(map);
			Logger.getInstance().write();
			JOptionPane.showMessageDialog(null, String.format(MessageConstants.INSERTED_RECORDS_COUNT, map.getLineCounter() , System.getProperty(Constants.LINE_SEPERATOR), map.size()));
		    } catch (IOException e) {
			handleException(e);
		    }
		}
	    }
	});

	JButton okButton = new JButton(GuiConstants.QUERY_BUTTON);
	okButton.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		String input = queryField.getText();
		List<SubscriberDebt> sdList = map.get(input);
		model //
		.clear() //
			.addRow(sdList);
	    }
	});

	JButton exportButton = new JButton(GuiConstants.EXPORT_BUTTON);
	exportButton.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent ae) {
		try {
		    JFileChooser chooser = new JFileChooser();
		    chooser.setCurrentDirectory(new java.io.File(Constants.DOT));
		    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		    chooser.setAcceptAllFileFilterUsed(false);
		    int option = chooser.showOpenDialog(App.this);
		    if (option == JFileChooser.APPROVE_OPTION) {
			File file = chooser.getSelectedFile();
			new HtmlWriter(model.getList()) //
				.create() //
				.write(file);
		    }
		} catch (FileNotFoundException e) {
		    handleException(e);
		}
	    }
	});

	JButton insertToDBButton = new JButton(GuiConstants.WRITE_BUTTON);
	insertToDBButton.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent ae) {
		try {
		    new DBHelper() //
			    .insertToDB(map) //
			    .closeAll();
		} catch (SQLException e) {
		    handleException(e);
		}
	    }
	});

	JButton importFromDB = new JButton(GuiConstants.IMPORT_BUTTON);
	importFromDB.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent ae) {
		try {
		    new DBHelper() //
			    .selectIntoMap(map) //
			    .closeAll();
		    JOptionPane.showMessageDialog(null, String.format(MessageConstants.INSERTED_RECORDS_COUNT, map.getLineCounter(), System.getProperty(Constants.LINE_SEPERATOR), map.size()));
		} catch (SQLException e) {
		    handleException(e);
		} catch (ParseException e) {
		    handleException(e);
		}
	    }
	});
	JButton deleteFromDB = new JButton(GuiConstants.DELETE_BUTTON);
	deleteFromDB.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent ae) {
		try {
		    new DBHelper() //
			    .deleteDB() //
			    .closeAll();
		} catch (SQLException e) {
		    handleException(e);
		}
	    }
	});
	c.add(openButton);
	c.add(queryField);
	c.add(okButton);
	c.add(exportButton);
	c.add(insertToDBButton);
	c.add(importFromDB);
	c.add(deleteFromDB);
	c.add(scrollPane);
    }

    public void handleException(Exception e) {
	JOptionPane.showMessageDialog(null, e.getMessage(), MessageConstants.ERROR, JOptionPane.ERROR_MESSAGE);
	e.printStackTrace();
    }

    public static void main(String args[]) {
	App app = new App();
	try {
	    new DBHelper() //
		    .createSqlLiteDB() //
		    .closeAll();
	} catch (SQLException e) {
	    app.handleException(e);
	}
	app.setVisible(true);
    }
}
