package elections.dao.service;

import java.util.List;

import elections.dao.entities.ElectionsConfig;
import elections.dao.entities.ListeElectorale;

public interface IElectionsDao {

	/**
	 * configuration de l'élection
	 * 
	 * @return
	 */
	public ElectionsConfig getElectionsConfig();

	/**
	 * listes candidates
	 * 
	 * @return
	 */
	public List<ListeElectorale> getListesElectorales();

	/** 
	 * listes candidates
	 * 
	 * @param listesElectorales
	 */
	public void setListesElectorales(List<ListeElectorale> listesElectorales);
}
