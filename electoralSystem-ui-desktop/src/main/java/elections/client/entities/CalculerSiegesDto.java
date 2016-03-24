package elections.client.entities;

import java.util.List;


/**
 * DTO contain object election and list 
 * 
 * @author Malick
 *
 */
public class CalculerSiegesDto {
	
	private ElectionsConfig election;
	private List<ListeElectorale> listes;
	
	
	
	public CalculerSiegesDto(ElectionsConfig election, List<ListeElectorale> listes) {
		super();
		this.election = election;
		this.listes = listes;
	}
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
