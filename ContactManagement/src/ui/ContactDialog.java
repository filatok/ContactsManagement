package ui;

import java.awt.TextField;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ContactDialog extends JFrame{

	private String name;
	private String surname;
	private String tel;
	private String adress;
	private String email ;
	private String category;
	
	public ContactDialog() {
		initializingComponents();
	}
	
	public ContactDialog(String name, String surname, String tel, String email, String adress, String category) {
		initializingComponents(name, surname, tel, email, adress, category);
	}
	
	JLabel nameLabel;
	JLabel surnameLabel;
	JLabel telLabel;
	JLabel emailLabel;
	JLabel adressLabel;
	JLabel categoryLabel;
	
	JTextField nameField;
	JTextField surnameField;
	JTextField telField;
	JTextField emailField;
	JTextField adressField;
	JComboBox categoryField;
	
	private void initializingComponents(String name, String surname, String tel, String email, String adress, String category) {
		
		setSize(400,300);
		setLocationRelativeTo(null);
		
		nameLabel = new JLabel();
		nameLabel.setText("Name:");
		nameLabel.setBounds(10, 10, 100, 20);
		
		surnameLabel = new JLabel();
		surnameLabel.setText("Surname:");
		surnameLabel.setBounds(10, 40, 100, 20);
		
		telLabel = new JLabel();
		telLabel.setText("Tel:");
		telLabel.setBounds(10, 70, 100, 20);
		
		emailLabel = new JLabel();
		emailLabel.setText("Email:");
		emailLabel.setBounds(10, 100, 100, 20);
		
		adressLabel = new JLabel();
		adressLabel.setText("Adress:");
		adressLabel.setBounds(10, 130, 100, 20);
		
		categoryLabel = new JLabel();
		categoryLabel.setText("Category:");
		categoryLabel.setBounds(10, 160, 100, 20);
		
		nameField = new JTextField();
		nameField.setText(name);
		nameField.setBounds(110, 10, 200, 20);
		
		surnameField = new JTextField();
		surnameField.setText(surname);
		surnameField.setBounds(110, 40, 200, 20);
		
		telField = new JTextField();
		telField.setText(tel);
		telField.setBounds(110, 70, 200, 20);
		
		emailField = new JTextField();
		emailField.setText(email);
		emailField.setBounds(110, 100, 200, 20);
		
		adressField = new JTextField();
		adressField.setText(adress);
		adressField.setBounds(110, 130, 200, 20);
		
		ArrayList<String> result = selectCategories();
		//DefaultComboBoxModel<String> comboBox = new DefaultComboBoxModel(result);
		JComboBox<String> categoryField = new JComboBox<>();
		for (String item : result ) {
			categoryField.addItem(item);
		}
		categoryField.setSelectedItem(category);
		categoryField.setBounds(110, 160, 200, 20);
		
		JButton saveButton = new JButton("Save");
		saveButton.setBounds(110,200,80,20);
		saveButton.addActionListener(e -> saveContact(nameField.getText(), surnameField.getText(), telField.getText(), emailField.getText(), adressField.getText(), (String) categoryField.getSelectedItem()));
		
		JButton closeContactButton = new JButton("Close");
		closeContactButton.setBounds(200,200,80,20);
		closeContactButton.addActionListener(e -> this.dispose());
	
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.add(nameLabel);
		panel.add(nameField);
		panel.add(surnameLabel);
		panel.add(surnameField);
		panel.add(telLabel);
		panel.add(telField);
		panel.add(emailLabel);
		panel.add(emailField);
		panel.add(adressLabel);
		panel.add(adressField);
		panel.add(categoryLabel);
		panel.add(categoryField);
		panel.add(saveButton);
		panel.add(closeContactButton);
		
	    setContentPane(panel);
	}
	
private void initializingComponents() {
		
		setSize(400,300);
		setLocationRelativeTo(null);
		
		nameLabel = new JLabel();
		nameLabel.setText("Name:");
		nameLabel.setBounds(10, 10, 100, 20);
		
		surnameLabel = new JLabel();
		surnameLabel.setText("Surname:");
		surnameLabel.setBounds(10, 40, 100, 20);
		
		telLabel = new JLabel();
		telLabel.setText("Tel:");
		telLabel.setBounds(10, 70, 100, 20);
		
		emailLabel = new JLabel();
		emailLabel.setText("Email:");
		emailLabel.setBounds(10, 100, 100, 20);
		
		adressLabel = new JLabel();
		adressLabel.setText("Adress:");
		adressLabel.setBounds(10, 130, 100, 20);
		
		categoryLabel = new JLabel();
		categoryLabel.setText("Category:");
		categoryLabel.setBounds(10, 160, 100, 20);
		
		nameField = new JTextField();
		nameField.setBounds(110, 10, 200, 20);
		
		surnameField = new JTextField();
		surnameField.setBounds(110, 40, 200, 20);
		
		telField = new JTextField();
		telField.setBounds(110, 70, 200, 20);
		
		emailField = new JTextField();
		emailField.setBounds(110, 100, 200, 20);
		
		adressField = new JTextField();
		adressField.setBounds(110, 130, 200, 20);
		
		ArrayList<String> result = selectCategories();
		//DefaultComboBoxModel<String> comboBox = new DefaultComboBoxModel(result);
		JComboBox<String> categoryField = new JComboBox<>();
		for (String item : result ) {
			categoryField.addItem(item);
		}
		categoryField.setBounds(110, 160, 200, 20);
		
		JButton saveButton = new JButton("Save");
		saveButton.setBounds(110,200,80,20);
		saveButton.addActionListener(e -> saveContact(nameField.getText(), surnameField.getText(), telField.getText(), emailField.getText(), adressField.getText(), (String) categoryField.getSelectedItem()));
		
		JButton closeContactButton = new JButton("Close");
		closeContactButton.setBounds(200,200,80,20);
		closeContactButton.addActionListener(e -> dispose());
	
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.add(nameLabel);
		panel.add(nameField);
		panel.add(surnameLabel);
		panel.add(surnameField);
		panel.add(telLabel);
		panel.add(telField);
		panel.add(emailLabel);
		panel.add(emailField);
		panel.add(adressLabel);
		panel.add(adressField);
		panel.add(categoryLabel);
		panel.add(categoryField);
		panel.add(saveButton);
		panel.add(closeContactButton);
		
	    setContentPane(panel);
	}
	
	private void saveContact(String name, String surname, String tel, String email, String adress, String category) {
		
		String dbPath = "jdbc:sqlite:G:\\AKMI\\Java\\ContactManagement\\ContactsDb.db";
		
		String addQuery = "INSERT INTO Contacts (Name, Surname, Tel, Email, Adress, CategoryID) VALUES (?, ?, ?, ?, ?, ?);";
		String categoryQuery = "select ID from Categories WHERE Name = ?;";

		try (Connection dbConnection = DriverManager.getConnection(dbPath);
				
			PreparedStatement ps = dbConnection.prepareStatement(categoryQuery);
			PreparedStatement ps1 = dbConnection.prepareStatement(addQuery)){
			ps.setString(1, category);
			ResultSet resultCategory = ps.executeQuery();
			int CategoryID = 0;
			while (resultCategory.next()) {
				CategoryID = resultCategory.getInt(1);
			}
			ps1.setString(1, name);
			ps1.setString(2, surname);
			ps1.setString(3, tel);
			ps1.setString(4, email);
			ps1.setString(5, adress);
			ps1.setInt(6, CategoryID);
			
			ps1.executeUpdate();
		}
		catch (Exception x) {
			System.err.println(x.getMessage());
		}		
	}
	
	public static ArrayList<String> selectCategories(){
		ArrayList<String> result = new ArrayList<>();
		String dbPath = "jdbc:sqlite:G:\\AKMI\\Java\\ContactManagement\\ContactsDb.db";
		try (Connection dbConnection = DriverManager.getConnection(dbPath)){
			try (Statement statement = dbConnection.createStatement()){
				String query = "select * from Categories;";
				try (ResultSet resultset = statement.executeQuery(query)) {
					while (resultset.next()) {
						String strRow = String.format("%s", resultset.getString(2));
						result.add(strRow);
					}
				}
			}
		}
		catch (Exception x) {
			System.err.print(x.getMessage());
		}
		return result;
	}
}
