package elections.metier.service;

import elections.dao.entities.ListeElectorale;

public interface IElectionsMetier {

	// obtenir les listes en compétition
	public ListeElectorale[] getListesElectorales();

	// le nombre de sièges à pourvoir
	public int getNbSiegesAPourvoir();

	// le seuil électoral
	public double getSeuilElectoral();

	// l'enregistrement des résultats
	public void recordResultats(ListeElectorale[] listesElectorales);

	// le calcul des sièges
	public ListeElectorale[] calculerSieges(ListeElectorale[] listesElectorales);

}
