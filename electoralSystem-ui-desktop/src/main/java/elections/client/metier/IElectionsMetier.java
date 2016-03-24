package elections.client.metier;

import java.util.List;

import elections.client.entities.ElectionsConfig;
import elections.client.entities.ListeElectorale;
import elections.client.entities.User;

public interface IElectionsMetier {

	// authentification
	public void authenticate(User user);
		
	// obtenir les listes en compétition
	public List<ListeElectorale> getListesElectorales(User user);

	// obtenir les listes en compétition
	public ElectionsConfig getElectionsConfig(User user);
		
	// le nombre de sièges à pourvoir
	public int getNbSiegesAPourvoir(User user);

	// le seuil électoral
	public double getSeuilElectoral(User user);

	// l'enregistrement des résultats
	public Void recordResultats(User user, List<ListeElectorale> listesElectorales);

	// le calcul des sièges
	public List<ListeElectorale> calculerSieges(User user, List<ListeElectorale> listesElectorales, ElectionsConfig election);

}
