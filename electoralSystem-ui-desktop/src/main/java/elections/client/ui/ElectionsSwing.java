package elections.client.ui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import elections.client.entities.ElectionsConfig;
import elections.client.entities.ElectionsException;
import elections.client.entities.ListeElectorale;
import elections.client.entities.User;
import elections.client.metier.IElectionsMetier;

/**
 * classe dérivée de [AbstractElectionsSwing] qui implémentera tous les
 * gestionnaires d'événement
 * 
 * @author Malick
 *
 */
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
	private List<ListeElectorale> listes;
	
	private ElectionsConfig election;

	// listes saisies par l'utilisateur
	private final List<ListeElectorale> listesSaisies = new ArrayList<>();
	private List<ListeElectorale> tListesSaisies;
	
	@Autowired
	private User user;

	// initialisations
	@Override
	protected void init() {
		
		election = metier.getElectionsConfig(user); 
				
		// génération des composants par la classe parent
		super.init();
		
		// on demande les listes à la couche [metier]
		listes = metier.getListesElectorales(user);
		
		// on associe les noms des listes au combo jComboBoxNomsListes
		// jComboBoxNomsListes.
		
		// ainsi que les paramètres de l'élection
		jLabelSE.setText(jLabelSE.getText() + election.getSeuilElectoral());
		jLabelSAP.setText(jLabelSAP.getText() + election.getNbSiegesAPourvoir());
	
		// on initialise les labels liés à ces deux informations
		
		// message de succès
		
		// initialisation état de certains composants formulaire
		
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
		listes = metier.calculerSieges(user, listes, election);
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
		JOptionPane.showMessageDialog(this, "TD UTBM", "Informations",
				JOptionPane.PLAIN_MESSAGE);
	}

}
