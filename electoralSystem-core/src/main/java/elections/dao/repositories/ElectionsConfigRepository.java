package elections.dao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import elections.dao.entities.ElectionsConfig;

public interface ElectionsConfigRepository extends JpaRepository<ElectionsConfig, Long>{
	
}
