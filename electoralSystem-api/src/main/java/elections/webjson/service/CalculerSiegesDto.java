package elections.webjson.service;

import java.util.List;

import elections.dao.entities.ElectionsConfig;
import elections.dao.entities.ListeElectorale;

/**
 * DTO contain object election and list 
 * 
 * @author Malick
 *
 */
public class CalculerSiegesDto {
	
	private ElectionsConfig election;
	private List<ListeElectorale> listes;
	
	
	public ElectionsConfig getElection() {
		return election;
	}
	public void setElection(ElectionsConfig election) {
		this.election = election;
	}
	public List<ListeElectorale> getListes() {
		return listes;
	}
	public void setListes(List<ListeElectorale> listes) {
		this.listes = listes;
	}
	
	

}
