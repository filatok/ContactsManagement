package ui;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.imageio.plugins.jpeg.JPEGHuffmanTable;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class MainFrame extends JFrame{
	
	public MainFrame() {
		initializingComponents();
	}
	
	private void initializingComponents() {
		
		setLocationRelativeTo(null);
		setSize(400,100);
		setLayout(new BorderLayout());
				
		JPanel mainPanel = new JPanel();
		
		JButton contactsButton = new JButton("Contacts list");
		contactsButton.addActionListener(e -> this.showContactsList());
		
		mainPanel.add(contactsButton);
		
		JButton closeContactsButton = new JButton("Close");
		closeContactsButton.addActionListener(e -> dispose());
		mainPanel.add(closeContactsButton);
		
		add(mainPanel, BorderLayout.CENTER);
		setVisible(true);
	}
	
	private void showContactsList() {
	
		JFrame frame = new JFrame();
		frame.setLocationRelativeTo(null);
		frame.setSize(500,500);
		frame.setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		DefaultTableModel tblContacts = new DefaultTableModel(0, 0);

		tblContacts.addColumn("Name");
		tblContacts.addColumn("Surname");
		tblContacts.addColumn("Tel");
		tblContacts.addColumn("Email");
		tblContacts.addColumn("Adress");
		tblContacts.addColumn("Category");
		
		String dbPath = "jdbc:sqlite:G:\\AKMI\\Java\\ContactManagement\\ContactsDb.db";
		try (Connection dbConnection = DriverManager.getConnection(dbPath)){
			try (Statement statement = dbConnection.createStatement()){
				String query = "select Contacts.Name, Contacts.Surname, Contacts.Tel, Contacts.Email, Contacts.Adress, Categories.Name as Category from Contacts\r\n"
						+ "inner JOIN Categories\r\n"
						+ "on Contacts.CategoryID = Categories.ID;";
				try (ResultSet result = statement.executeQuery(query)) {
					while (result.next()) {
						String name = result.getString(1);
						String surname = result.getString(2);
						String tel = result.getString(3);
						String email = result.getString(4);
						String adress = result.getString(5);
						String category = result.getString(6);
						tblContacts.addRow(new String[] { name,surname,tel,email,adress, category});
					}
				}
			}
		}
		catch (Exception x) {
			System.err.println(x.getMessage());
		}		
		
		JTable table = new JTable();
		JButton editButton = new JButton("Edit");
		JButton deleteButton = new JButton("Delete");
		
		table.addMouseListener(new MouseAdapter(){
		    @Override
		    public void mouseClicked(MouseEvent event) {
		    	
		    	int[] selectedRows = table.getSelectedRows();
		    	int lastRowIndex = 0;
			    	if (selectedRows.length > 0){
			    	lastRowIndex = selectedRows[selectedRows.length - 1];
			    	}
 
			    	{
			        	String name = (String) table.getValueAt(lastRowIndex, 0);
						String surname = (String) table.getValueAt(lastRowIndex, 1);
						String tel = (String) table.getValueAt(lastRowIndex, 2);
						String email = (String) table.getValueAt(lastRowIndex, 3);
						String adress = (String) table.getValueAt(lastRowIndex,4);
						String category = (String) table.getValueAt(lastRowIndex, 5);
						ActionListener[] listeners = editButton.getListeners(ActionListener.class);
						for(ActionListener listener: listeners) {
							editButton.removeActionListener(listener);
						}
			    		editButton.addActionListener(e -> editContact(name, surname, tel, email, adress, category));
			    		
			    		ActionListener[] listeners1 = deleteButton.getListeners(ActionListener.class);
						for(ActionListener listener: listeners1) {
							deleteButton.removeActionListener(listener);
						}
						deleteButton.addActionListener(e -> deleteContact(name, surname, tel, email, adress, category));
			    	}
			        
		    }
		});
		
		table.setModel(tblContacts);
		JScrollPane tblScroll = new JScrollPane(table);
		tblScroll.setBounds(10, 10, 450, 200);
		
		JPanel panel = new JPanel();
		panel.add(tblScroll);
		
		JPanel buttonsPanel = new JPanel();
		JButton addButton = new JButton("Add");
		addButton.addActionListener(e -> this.addNewContact());
		buttonsPanel.add(addButton);
		buttonsPanel.add(editButton);
		buttonsPanel.add(deleteButton);
		JButton closeButton = new JButton("Close");
		closeButton.addActionListener(e -> frame.dispose());
		buttonsPanel.add(closeButton);
		
		frame.add(panel,  BorderLayout.NORTH);
		frame.add(buttonsPanel, BorderLayout.SOUTH);
		frame.setVisible(true);	
	}
	
	private void addNewContact(){
		new ContactDialog().setVisible(true);
	}
	
	private void editContact(String name, String surname, String tel, String email, String adress, String category) {
		new ContactDialog(name, surname, tel, email, adress, category).setVisible(true);
	}
	
	private void deleteContact(String name, String surname, String tel, String email, String adress, String category) {
		
		String dbPath = "jdbc:sqlite:G:\\AKMI\\Java\\ContactManagement\\ContactsDb.db";
		String deleteQuery = "DELETE from Contacts WHERE Name = ? AND Surname = ? AND Tel = ? AND Email = ? AND Adress = ?;";
		try (Connection dbConnection = DriverManager.getConnection(dbPath);
				PreparedStatement ps = dbConnection.prepareStatement(deleteQuery)){
				ps.setString(1, name);
				ps.setString(2, surname);
				ps.setString(3, tel);
				ps.setString(4, email);
				ps.setString(5, adress);
				ps.executeUpdate();
			}
			catch (Exception x) {
				System.err.println(x.getMessage());
			}	
	}
	
}

