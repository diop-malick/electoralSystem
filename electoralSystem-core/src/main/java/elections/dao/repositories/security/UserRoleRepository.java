package elections.dao.repositories.security;

import org.springframework.data.repository.CrudRepository;

import elections.dao.entities.security.UserRole;


public interface UserRoleRepository extends CrudRepository<UserRole, Long> {

}
