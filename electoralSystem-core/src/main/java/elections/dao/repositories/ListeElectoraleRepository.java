package elections.dao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import elections.dao.entities.ListeElectorale;

public interface ListeElectoraleRepository extends JpaRepository<ListeElectorale, Long>{
	
} 
