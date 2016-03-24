package elections.client.entities;


public class ElectionsConfig extends AbstractEntity {

	private static final long serialVersionUID = 1L;
	// champs
	private int nbSiegesAPourvoir;
	private double seuilElectoral;

	// constructeurs
	public ElectionsConfig() {

	}

	public ElectionsConfig(int nbSiegesAPourvoir, double seuilElectoral) {
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
