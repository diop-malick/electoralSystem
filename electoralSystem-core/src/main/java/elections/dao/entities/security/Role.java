package elections.dao.entities.security;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import elections.dao.entities.AbstractEntity;

@Entity
@Table(name = "ROLES")
public class Role extends AbstractEntity {

	private static final long serialVersionUID = 1L;
	// propriétés
	@Column(name="NAME")
	private String name;

	// constructeurs
	public Role() {
	}

	public Role(String name) {
		this.name = name;
	}

	// getters et setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
