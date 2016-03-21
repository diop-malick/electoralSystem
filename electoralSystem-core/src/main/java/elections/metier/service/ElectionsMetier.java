package elections.metier.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import elections.dao.entities.ListeElectorale;
import elections.dao.service.IElectionsDao;

@Component
public class ElectionsMetier implements IElectionsMetier {

	// le point d'accès à la couche [dao] instanciée par [Spring]
	@SuppressWarnings("unused")
	@Autowired
	private IElectionsDao electionsDao;

	// calcul des sièges obtenus
	@Override
	public ListeElectorale[] calculerSieges(ListeElectorale[] listesElectorales) {
		throw new UnsupportedOperationException("[calculerSieges] not yet implemented");
	}

	// listes en compétition
	@Override
	public ListeElectorale[] getListesElectorales() {
		throw new UnsupportedOperationException("[getListesElectorales] not yet implemented");
	}

	// sauvegarde des résultats
	@Override
	public void recordResultats(ListeElectorale[] listesElectorales) {
		throw new UnsupportedOperationException("[recordResultats] not yet implemented");
	}

	// nombre de sièges à pourvoir
	@Cacheable("electionsConfig")
	@Override
	public int getNbSiegesAPourvoir() {
		throw new UnsupportedOperationException("[getNbSiegesAPourvoir] not yet implemented");
	}

	// seuil électoral
	@Cacheable("electionsConfig")
	@Override
	public double getSeuilElectoral() {
		throw new UnsupportedOperationException("[getSeuilElectoral] not yet implemented");
	}

}
