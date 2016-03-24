package elections.client.security.swing;

import javax.swing.JLabel;
import javax.swing.JMenuItem;

//classe utilitaire
class Utilitaires {
	// gérer l'état du label
	public static void setEnabled(JLabel[] labels, boolean value) {
		for (int i = 0; i < labels.length; i++) {
			labels[i].setEnabled(value);
		}
	}

	// gérer l'état de l'option de menu
	public static void setEnabled(JMenuItem[] menuItems, boolean value) {
		for (int i = 0; i < menuItems.length; i++) {
			menuItems[i].setEnabled(value);
		}
	}

}
