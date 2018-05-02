package edu.vt.finaid.gui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class GUI extends JFrame {
	//Variables
	private static final long serialVersionUID = 1;
	private int progress;
	private String aidYear;;
	private char[] password;
	private String username;
	
	public String getAidYear() {
		return aidYear;
	}
	
	public char[] getPassword() {
		return password;
	}
	
	public String getUsername() {
		return username;
	}
	
	public GUI() {
		//Create components
		JButton runButton = new JButton();
		JLabel aidYearLabel = new JLabel();
		JLabel passwordLabel = new JLabel();
		JLabel usernameLabel = new JLabel();
		JPasswordField passwordPField = new JPasswordField();
		JProgressBar progressBar = new JProgressBar();
		JTextField aidYearTField = new JTextField();
		JTextField usernameTField = new JTextField();
		SpringLayout springLayout = new SpringLayout();
		
		//Define this, component properties
		aidYearLabel.setText("Aid Year Code:");
		aidYearTField.setColumns(10);
		passwordLabel.setText("Oracle Password:");
		passwordPField.setColumns(10);
		progressBar.setMaximum(10);
		runButton.setEnabled(false);
		runButton.setText("Run");
		usernameLabel.setText("Oracle Username:");
		usernameTField.setColumns(10);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(springLayout);
		this.setResizable(false);
		this.setSize(300, 150);
		this.setLocationRelativeTo(null);
		this.setTitle("ISIR Pre-Wash");
		
		//Define springLayout
		//SpringLayout.NORTH/EAST/SOUTH/WEST, child, pixels, SpringLayout.NORTH/EAST/SOUTH/WEST, parent
		//aidYearLabel
		springLayout.putConstraint(SpringLayout.NORTH, aidYearLabel, 5, SpringLayout.NORTH, this.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, aidYearLabel, 5, SpringLayout.WEST, this.getContentPane());
		//aidYearTField
		springLayout.putConstraint(SpringLayout.NORTH, aidYearTField, 0, SpringLayout.NORTH, aidYearLabel);
		springLayout.putConstraint(SpringLayout.WEST, aidYearTField, 5, SpringLayout.EAST, usernameLabel);
		//passwordLabel
		springLayout.putConstraint(SpringLayout.NORTH, passwordLabel, 5, SpringLayout.SOUTH, usernameTField);
		springLayout.putConstraint(SpringLayout.WEST, passwordLabel, 5, SpringLayout.WEST, this.getContentPane());
		//passwordPField
		springLayout.putConstraint(SpringLayout.NORTH, passwordPField, 0, SpringLayout.NORTH, passwordLabel);
		springLayout.putConstraint(SpringLayout.WEST, passwordPField, 5, SpringLayout.EAST, passwordLabel);
		//progressBar
		springLayout.putConstraint(SpringLayout.EAST, progressBar, 0, SpringLayout.EAST, usernameTField);
		springLayout.putConstraint(SpringLayout.SOUTH, progressBar, -5, SpringLayout.SOUTH, this.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, progressBar, 5, SpringLayout.WEST, this.getContentPane());
		//runButton
		springLayout.putConstraint(SpringLayout.EAST, runButton, -5, SpringLayout.EAST, this.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, runButton, -5, SpringLayout.SOUTH, this.getContentPane());
		//usernameLabel
		springLayout.putConstraint(SpringLayout.NORTH, usernameLabel, 5, SpringLayout.SOUTH, aidYearTField);
		springLayout.putConstraint(SpringLayout.WEST, usernameLabel, 5, SpringLayout.WEST, this.getContentPane());
		//usernameTField
		springLayout.putConstraint(SpringLayout.NORTH, usernameTField, 0, SpringLayout.NORTH, usernameLabel);
		springLayout.putConstraint(SpringLayout.WEST, usernameTField, 5, SpringLayout.EAST, usernameLabel);
		
		//Add components to JFrame
		this.add(aidYearLabel);
		this.add(aidYearTField);
		this.add(passwordLabel);
		this.add(passwordPField);
		this.add(progressBar);
		this.add(runButton);
		this.add(usernameLabel);
		this.add(usernameTField);
		
		//Define listeners
		//Called when runButton is clicked
		ActionListener runClick = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(passwordPField.getPassword());
			}
		};
		
		//Handles validation of text fields
		DocumentListener filledText = new DocumentListener() {
			@Override
			public void removeUpdate(DocumentEvent e) {
				tfieldChecks();
			}
			@Override
			public void insertUpdate(DocumentEvent e) {
				tfieldChecks();
			}
			@Override
			public void changedUpdate(DocumentEvent e) {
				tfieldChecks();
			}
			private void tfieldChecks() {
				if (aidYearTField.getText().isEmpty() != true) {
					if (usernameTField.getText().isEmpty() != true) {
						if (passwordPField.getPassword().length > 0) {
							runButton.setEnabled(true);
						} else {
							runButton.setEnabled(false);
						}
					} else {
						runButton.setEnabled(false);
					}
				} else {
					runButton.setEnabled(false);
				}
			}
		};
		
		//Add listeners
		aidYearTField.getDocument().addDocumentListener(filledText);
		passwordPField.getDocument().addDocumentListener(filledText);
		runButton.addActionListener(runClick);
		usernameTField.getDocument().addDocumentListener(filledText);
		
		//Passed
		aidYear = aidYearTField.getText();
		password = passwordPField.getPassword();
		username = usernameTField.getText().toLowerCase();
	}
}