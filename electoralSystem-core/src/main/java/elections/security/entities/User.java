package elections.security.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import elections.dao.entities.AbstractEntity;

@Entity
@Table(name = "USERS")
public class User extends AbstractEntity {

	private static final long serialVersionUID = 1L;
	// propriétés
	@Column(name = "NAME")
	private String name;
	@Column(name = "LOGIN")
	private String login;
	@Column(name = "PASSWORD")
	private String password;

	// constructeur
	public User() {
	}

	public User(String name, String login, String password) {
		this.name = name;
		this.login = login;
		this.password = password;
	}

	// getters et setters
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
