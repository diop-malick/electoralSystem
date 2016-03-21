package elections.metier.junit;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import elections.dao.config.AppConfig;
import elections.dao.entities.ElectionsException;
import elections.dao.entities.ListeElectorale;
import elections.metier.service.IElectionsMetier;

@SpringApplicationConfiguration(classes = AppConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class Test01 {

	// couche [DAO]
	@Autowired
	private IElectionsMetier electionsMetier;

	// mappeur jSON
	private ObjectMapper mapper = new ObjectMapper();

	/**
	 * vérification 1 : méthode de calcul des sièges on fixe en dur les listes
	 */
	@Test
	public void calculSieges1() {
		// on crée le tableau des 7 listes candidates
		ListeElectorale[] listes = new ListeElectorale[7];
		listes[0] = new ListeElectorale("A", 32000, 0, false);
		listes[1] = new ListeElectorale("B", 25000, 0, false);
		listes[2] = new ListeElectorale("C", 16000, 0, false);
		listes[3] = new ListeElectorale("D", 12000, 0, false);
		listes[4] = new ListeElectorale("E", 8000, 0, false);
		listes[5] = new ListeElectorale("F", 4500, 0, false);
		listes[6] = new ListeElectorale("G", 2500, 0, false);
		// on calcule les sièges de chacune des listes
		listes = electionsMetier.calculerSieges(listes);
		// on vérifie les résultats
		Assert.assertEquals(2, listes[0].getSieges());
		Assert.assertFalse(listes[0].isElimine());
		Assert.assertEquals(2, listes[1].getSieges());
		Assert.assertFalse(listes[1].isElimine());
		Assert.assertEquals(1, listes[2].getSieges());
		Assert.assertFalse(listes[2].isElimine());
		Assert.assertEquals(1, listes[3].getSieges());
		Assert.assertFalse(listes[3].isElimine());
		Assert.assertEquals(0, listes[4].getSieges());
		Assert.assertFalse(listes[4].isElimine());
		Assert.assertEquals(0, listes[5].getSieges());
		Assert.assertTrue(listes[5].isElimine());
		Assert.assertEquals(0, listes[6].getSieges());
		Assert.assertTrue(listes[6].isElimine());
	}

	/**
	 * vérification 2 : méthode de calcul des sièges on demande les listes à la couche [metier] puis on fixe en dur les
	 * voix
	 */
	@Test
	public void calculSieges2() {
		// on crée le tableau des 7 listes candidates
		ListeElectorale[] listes = electionsMetier.getListesElectorales();
		// on fixe en dur les voix
		listes[0].setVoix(32000);
		listes[1].setVoix(25000);
		listes[2].setVoix(16000);
		listes[3].setVoix(12000);
		listes[4].setVoix(8000);
		listes[5].setVoix(4500);
		listes[6].setVoix(2500);
		// on calcule les sièges obtenus par chacune des listes
		listes = electionsMetier.calculerSieges(listes);
		// on vérifie les résultats
		Assert.assertEquals(2, listes[0].getSieges());
		Assert.assertFalse(listes[0].isElimine());
		Assert.assertEquals(2, listes[1].getSieges());
		Assert.assertFalse(listes[1].isElimine());
		Assert.assertEquals(1, listes[2].getSieges());
		Assert.assertFalse(listes[2].isElimine());
		Assert.assertEquals(1, listes[3].getSieges());
		Assert.assertFalse(listes[3].isElimine());
		Assert.assertEquals(0, listes[4].getSieges());
		Assert.assertFalse(listes[4].isElimine());
		Assert.assertEquals(0, listes[5].getSieges());
		Assert.assertTrue(listes[5].isElimine());
		Assert.assertEquals(0, listes[6].getSieges());
		Assert.assertTrue(listes[6].isElimine());
	}

	/**
	 * vérification 3 méthode de calcul des sièges on provoque une exception
	 */
	@Test(expected = ElectionsException.class)
	public void calculSieges3() {
		// on crée un tableau de 24 listes candidates avec chacune 1 voix
		ListeElectorale[] listes = new ListeElectorale[25];
		// les 25 listes auront le même nombre de voix (4%)
		for (int i = 0; i < listes.length; i++) {
			listes[i] = new ListeElectorale("Liste" + (i + 1), 1, 0, false);
		}
		// calcul des sièges - normalement on doit avoir une ElectionsException
		// avec un seuil èlectoral de 5%
		electionsMetier.calculerSieges(listes);
	}

	/**
	 * enregistrement des résultats de l'élection
	 * 
	 * @throws JsonProcessingException
	 */
	@Test
	public void ecritureResultatsElections() throws JsonProcessingException {
		// on crée le tableau des 7 listes candidates
		ListeElectorale[] listes = electionsMetier.getListesElectorales();
		// on fixe en dur les voix
		listes[0].setVoix(32000);
		listes[1].setVoix(25000);
		listes[2].setVoix(16000);
		listes[3].setVoix(12000);
		listes[4].setVoix(8000);
		listes[5].setVoix(4500);
		listes[6].setVoix(2500);
		// on calcule les sièges obtenus par chacune des listes
		listes = electionsMetier.calculerSieges(listes);
		// on affiche les résultats
		for (int i = 0; i < listes.length; i++) {
			System.out.println(mapper.writeValueAsString(listes[i]));
		}
		// on enregistre les résultats dans la base de données
		electionsMetier.recordResultats(listes);
		// on vérifie les résultats
		listes = electionsMetier.getListesElectorales();
		// on affiche les résultats
		for (int i = 0; i < listes.length; i++) {
			System.out.println(mapper.writeValueAsString(listes[i]));
		}
		Assert.assertEquals(2, listes[0].getSieges());
		Assert.assertFalse(listes[0].isElimine());
		Assert.assertEquals(2, listes[1].getSieges());
		Assert.assertFalse(listes[1].isElimine());
		Assert.assertEquals(1, listes[2].getSieges());
		Assert.assertFalse(listes[2].isElimine());
		Assert.assertEquals(1, listes[3].getSieges());
		Assert.assertFalse(listes[3].isElimine());
		Assert.assertEquals(0, listes[4].getSieges());
		Assert.assertFalse(listes[4].isElimine());
		Assert.assertEquals(0, listes[5].getSieges());
		Assert.assertTrue(listes[5].isElimine());
		Assert.assertEquals(0, listes[6].getSieges());
		Assert.assertTrue(listes[6].isElimine());
	}
}
