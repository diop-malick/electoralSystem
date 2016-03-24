package elections.security.repositories;

import org.springframework.data.repository.CrudRepository;

import elections.security.entities.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {

	// recherche d'un rôle via son nom
	Role findRoleByName(String name);

}
