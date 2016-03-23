package elections.ui.service;

import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import elections.dao.entities.ElectionsException;
import elections.dao.entities.ListeElectorale;
import elections.metier.service.IElectionsMetier;

@Component
public class ElectionsSwing extends AbstractElectionsSwing implements IElectionsUI {

	private static final long serialVersionUID = 1L;

	// référence sur la couche [métier]
	@Autowired
	private IElectionsMetier metier;

	// modèles des listes JList
	private final DefaultListModel<String> modèleNomsVoix = null;
	private final DefaultListModel<String> modèleRésultats = null;

	// les listes en compétition
	private ListeElectorale[] listes;

	// listes saisies par l'utilisateur
	private final List<ListeElectorale> listesSaisies = new ArrayList<>();
	private ListeElectorale[] tListesSaisies;

	// initialisations
	@Override
	protected void init() {
	}

	private String getInfoForException(String message, ElectionsException ex) {
		// on affiche le message
		StringBuilder info = new StringBuilder(String.format("%s -------------\n", message));
		info.append(String.format("Code erreur : %d\n", ex.getCode()));
		// on affiche les erreurs
		for (String erreur : ex.getErreurs()) {
			info.append(String.format("-- %s\n", erreur));
		}
		return info.toString();
	}

	private String getInfoForException(String message, RuntimeException ex) {
		// on affiche le message
		StringBuilder info = new StringBuilder(String.format("%s -------------\n", message));
		// on affiche la pile des exceptions
		Throwable cause = ex;
		while (cause != null) {
			info.append(String.format("-- %s\n", cause.getMessage()));
			cause = cause.getCause();
		}
		return info.toString();
	}

	// gestionnaires d'évts
	@Override
	protected void doAjouter() {

	}

	@Override
	protected void doCalculer() {

	}

	@Override
	protected void doEffacer() {

	}

	@Override
	protected void doEnregistrer() {

	}

	@Override
	protected void doQuitter() {
		System.exit(0);
	}

	@Override
	protected void doSupprimer() {

	}

	@Override
	protected void doMajLabelAjouter() {

	}

	@Override
	protected void doMajLabelSupprimer() {

	}

	@Override
	public void run() {
		// on affiche l'interface graphique
		SwingUtilities.invokeLater(new Runnable() {
      @Override
			public void run() {
				init();
				setVisible(true);
			}
		});
	}

	@Override
	protected void doInformer() {
    JOptionPane.showMessageDialog(this,
        "TD IstiA - Université d'Angers",
        "Informations",
        JOptionPane.PLAIN_MESSAGE);		
	}

}
