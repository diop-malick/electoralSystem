package elections.dao.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import elections.dao.entities.ElectionsConfig;
import elections.dao.entities.ElectionsException;
import elections.dao.entities.ListeElectorale;
import elections.dao.repositories.ElectionsConfigRepository;
import elections.dao.repositories.ListeElectoraleRepository;

@Component
public class ElectionDaoJPa implements IElectionsDao {

	  private static final Logger logger = LoggerFactory.getLogger(ElectionDaoJPa.class);

	  
	// injection de repositories
	@Autowired
	public ElectionsConfigRepository electionsConfigRepository;

	@Autowired
	public ListeElectoraleRepository listeElectoraleRepository;

	
	// listes en compétition
	@Override
	public List<ListeElectorale> getListesElectorales() {
	  try {			
		  return listeElectoraleRepository.findAll();
		} catch (Exception e) {
			throw new ElectionsException(101, e);
		}
	}

	// sauvegarde des résultats
	@Override
	public void setListesElectorales(List<ListeElectorale> listesElectorales) {
		 try {			
			  listeElectoraleRepository.save(listesElectorales);
			} catch (Exception e) {
				throw new ElectionsException(101, e);
			}
	}

	@Cacheable("electionsConfig")
	@Override
	public ElectionsConfig getElectionsConfig() {
		  try {			
			List<ElectionsConfig> result = electionsConfigRepository.findAll();
			Optional<ElectionsConfig> opt = result.stream().findFirst();
			//  if(opt.isPresent()) {
				  return opt.get();
			 // }
			} catch (Exception e) {
				throw new ElectionsException(103, e);
			}
	}

}
