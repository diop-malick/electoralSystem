package elections.ui.boot;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import elections.dao.entities.ElectionsException;
import elections.ui.config.UiConfig;
import elections.ui.service.IElectionsUI;

public abstract class AbstractBootElections {

	// récupération du contexte Spring
	protected AnnotationConfigApplicationContext ctx;

	public void run() {
		// instanciation couche [ui]
		IElectionsUI electionsUI = null;
		try {
			// récupération du contexte Spring
			ctx = new AnnotationConfigApplicationContext(UiConfig.class);
			// récupération de la couche [ui]
			electionsUI = getUI();
		} catch (RuntimeException ex) {
			// on signale l'erreur
			afficheExceptions("Les erreurs suivantes se sont produites :", ex);
			// on arrête l'application
			System.exit(1);
		}
		// exécution couche [ui]
		try {
			electionsUI.run();
		} catch (ElectionsException ex2) {
			// on signale l'erreur
			afficheExceptions("Les erreurs suivantes se sont produites :", ex2);
			// on arrête l'application
			System.exit(3);
		} catch (RuntimeException ex1) {
			// on signale l'erreur
			afficheExceptions("Les erreurs suivantes se sont produites :", ex1);
			// on arrête l'application
			System.exit(2);
		}
	}

	protected abstract IElectionsUI getUI();

	private void afficheExceptions(String message, ElectionsException ex) {
		// on affiche le message
		System.out.println(String.format("%s -------------", message));
		System.out.println(String.format("Code erreur : %d", ex.getCode()));
		// on affiche les erreurs
		for (String erreur : ex.getErreurs()) {
			System.out.println(String.format("-- %s", erreur));
		}

	}

	public void afficheExceptions(String message, Exception ex) {
		// on affiche le message
		System.out.println(String.format("%s -------------", message));
		// on affiche la pile des exceptions
		Throwable cause = ex;
		while (cause != null) {
			System.out.println(String.format("-- %s", cause.getMessage()));
			cause = cause.getCause();
		}
	}
}
