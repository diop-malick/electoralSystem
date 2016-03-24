package elections.client.metier;

import java.util.List;

import elections.client.entities.ElectionsConfig;
import elections.client.entities.ListeElectorale;

public interface IElectionsMetier {

	// obtenir les listes en compétition
	public List<ListeElectorale> getListesElectorales();

	// obtenir les listes en compétition
	public ElectionsConfig getElectionsConfig();
		
	// le nombre de sièges à pourvoir
	public int getNbSiegesAPourvoir();

	// le seuil électoral
	public double getSeuilElectoral();

	// l'enregistrement des résultats
	public Void recordResultats(List<ListeElectorale> listesElectorales);

	// le calcul des sièges
	public List<ListeElectorale> calculerSieges(List<ListeElectorale> listesElectorales, ElectionsConfig election);

}
