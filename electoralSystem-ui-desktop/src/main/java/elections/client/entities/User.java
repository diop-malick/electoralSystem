package elections.client.entities;

public class User {

	// propriétés
	private String login;
	private String password;

	// constructeur
	public User() {
	}

	public User(String login, String password) {
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
