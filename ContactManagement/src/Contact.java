
public class Contact {
	
	private int id;
	private String name;
	private String surname;
	private String phone;
	private String email;
	private String address;
	private int categoryId;
	
	public Contact(int id, String name, String surname, String phone, String email, String address, int categoryId) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.phone = phone;
		this.email = email;
		this.address = address;
		this.categoryId = categoryId;
	}

	public Contact() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategory(int categoryId) {
		this.categoryId = categoryId;
	}
	
	
}
