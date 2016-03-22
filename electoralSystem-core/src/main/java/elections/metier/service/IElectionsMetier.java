package elections.metier.service;

import java.util.List;

import elections.dao.entities.ElectionsConfig;
import elections.dao.entities.ListeElectorale;

public interface IElectionsMetier {

	// obtenir les listes en compétition
	public List<ListeElectorale> getListesElectorales();

	// le nombre de sièges à pourvoir
	public int getNbSiegesAPourvoir();

	// le seuil électoral
	public double getSeuilElectoral();

	// l'enregistrement des résultats
	public void recordResultats(List<ListeElectorale> listesElectorales);

	// le calcul des sièges
	public List<ListeElectorale> calculerSieges(List<ListeElectorale> listesElectorales, ElectionsConfig election);

}
