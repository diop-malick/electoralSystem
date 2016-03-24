package elections.client.entities;

public class ListeElectorale extends AbstractEntity {

	private static final long serialVersionUID = 1L;
	// champs
	private String nom;
	private int voix;
	private int sieges;
	private boolean elimine;

	// constructeurs
	public ListeElectorale() {
	}

	public ListeElectorale(String nom, int voix, int sieges, boolean elimine) {
		// parent
		super();

		// champs locaux
		initNom(nom);
		initVoix(voix);
		initSieges(sieges);
		this.elimine=elimine;
	}

	public ListeElectorale(Long id, Long version, String nom, int voix, int sieges, boolean elimine) {
		// parent
		super(id, version);
		// champs locaux
		initNom(nom);
		initVoix(voix);
		initSieges(sieges);
		this.elimine=elimine;
	}

	// méthodes privées

	private void initNom(String nom) {
		this.nom = nom.trim();
		if ("".equals(nom)) {
			throw new ElectionsException(10, "Le nom ne peut être vide");
		}
	}

	private void initVoix(int voix) {
		this.voix = voix;
		if (voix < 0) {
			throw new ElectionsException(11, "Le nombre de voix ne peut être <0");
		}
	}

	private void initSieges(int sieges) {
		this.sieges = sieges;
		if (sieges < 0) {
			throw new ElectionsException(12, "Le nombre de sièges ne peut être <0");
		}
	}

	// getters et setters

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		initNom(nom);
	}

	public int getVoix() {
		return voix;
	}

	public void setVoix(int voix) {
		initVoix(voix);
	}

	public int getSieges() {
		return sieges;
	}

	public void setSieges(int sieges) {
		initSieges(sieges);
	}

	public boolean isElimine() {
		return elimine;
	}

	public void setElimine(boolean elimine) {
		this.elimine = elimine;
	}

}
