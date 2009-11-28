package dao;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.SequenceGenerator;

@Entity
@Table(name="user")
public class User2 implements Serializable {
	
	@Id
	@GeneratedValue
	@SequenceGenerator (name="user_id_seq")
	int id;
	String login;
	String pass;
	String role;
	
	public User2() {}
	
	public User2(String login, String pass) {
		this.login = login;
		this.pass = pass;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
}
