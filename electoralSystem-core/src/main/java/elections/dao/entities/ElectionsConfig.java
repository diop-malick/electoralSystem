package elections.dao.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "conf")
public class ElectionsConfig extends AbstractEntity {

	private static final long serialVersionUID = 1L;
	
	// champs
	@Column(name = "sap")
	private int nbSiegesAPourvoir;
	@Column
	private double seuilElectoral;

	// constructeurs
	public ElectionsConfig() {
	}

	public ElectionsConfig(int nbSiegesAPourvoir, double seuilElectoral) {
		// parent
		super();
		// champs locaux
		this.nbSiegesAPourvoir = nbSiegesAPourvoir;
		this.seuilElectoral = seuilElectoral;
	}

	// getters et setters
	public int getNbSiegesAPourvoir() {
		return nbSiegesAPourvoir;
	}

	public void setNbSiegesAPourvoir(int nbSiegesAPourvoir) {
		this.nbSiegesAPourvoir = nbSiegesAPourvoir;
	}

	public double getSeuilElectoral() {
		return seuilElectoral;
	}

	public void setSeuilElectoral(double seuilElectoral) {
		this.seuilElectoral = seuilElectoral;
	}
}
