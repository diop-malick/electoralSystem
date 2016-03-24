package elections.security.repositories;

import org.springframework.data.repository.CrudRepository;

import elections.security.entities.UserRole;

public interface UserRoleRepository extends CrudRepository<UserRole, Long> {

}
