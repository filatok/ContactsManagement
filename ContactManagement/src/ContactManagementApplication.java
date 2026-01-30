import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.SwingUtilities;

import ui.MainFrame;

public class ContactManagementApplication {
	public void run() {
		SwingUtilities.invokeLater(() -> new MainFrame().setVisible(true));
	}
	
	private void selectFromDB() {
		String dbPath = "jdbc:sqlite:G:\\AKMI\\Java\\ContactsDb.db";
		try (Connection dbConnection = DriverManager.getConnection(dbPath)){
			try (Statement statement = dbConnection.createStatement()){
				String query = "select * from Contacts;";
				try (ResultSet result = statement.executeQuery(query)) {
					while (result.next()) {
						System.out.println("yes");
						Contact c = new Contact();
						int id = result.getInt(1);
						c.setId(id);
						String name = result.getString(2);
						c.setName(name);
						String surname = result.getString(3);
						c.setSurname(surname);
						String tel = result.getString(4);
						c.setPhone(tel);
						String email = result.getString(5);
						c.setEmail(email);
						String adress = result.getString(6);
						c.setAddress(adress);
						int categoryID = result.getInt(7);
						c.setCategory(categoryID);
					}
				}
			}
		}
		catch (Exception x) {
			
		}
	}
}
