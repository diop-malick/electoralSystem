package elections.metier.service;

import java.util.List;

import elections.dao.entities.ElectionsConfig;
import elections.dao.entities.ListeElectorale;

public interface IElectionsMetier {

	/**
	 * obtenir les listes en compétition
	 * @return
	 */
	public List<ListeElectorale> getListesElectorales();

	/**
	 * obtenir l'election
	 * @return
	 */
	public ElectionsConfig getElectionsConfig();
		
	/**
	 * le nombre de sièges à pourvoir
	 * @return
	 */
	public int getNbSiegesAPourvoir();

	/**
	 * le seuil électoral
	 * @return
	 */
	public double getSeuilElectoral();

	/**
	 * le calcul des sièges
	 * @param listesElectorales
	 * @param election
	 * @return
	 */
	public List<ListeElectorale> calculerSieges(List<ListeElectorale> listesElectorales, ElectionsConfig election);
	
	/**
	 * l'enregistrement des résultats
	 * @param listesElectorales
	 */
	public void recordResultats(List<ListeElectorale> listesElectorales);

}
