package elections.dao.repositories.security;

import org.springframework.data.repository.CrudRepository;

import elections.dao.entities.security.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {

	// recherche d'un rôle via son nom
	Role findRoleByName(String name);

}
