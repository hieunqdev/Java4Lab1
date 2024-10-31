package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

//Annotation cho biết class User đại diện cho một bảng trong csdl
@Entity
//Annotation cho biết đây là tên bảng trong csdl
@Table(name="Users")
public class User {
	// khóa chính
	@Id 
	// tên trường 
	@Column(name="id")
	// String -> nvarchar
	String id;
	
	@Column(name = "password")
	String password;
	
	@Column(name = "fullname")
	String fullname;
	
	@Column(name = "email")
	String email;
	
	@Column(name = "admin")
	// Boolean -> bit
	Boolean admin = false;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getAdmin() {
		return admin;
	}

	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}
}
