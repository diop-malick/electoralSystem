package elections.dao.tests;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import elections.dao.config.AppConfig;
import elections.dao.entities.ElectionsConfig;
import elections.dao.entities.ListeElectorale;
import elections.dao.service.IElectionsDao;

@SpringApplicationConfiguration(classes = AppConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class Test01 {

	// couche [DAO]
	@Autowired
	private IElectionsDao electionsDao;

	@Before
	public void init() {
		// on nettoie la table [LISTES]
		// listes en compétition
		List<ListeElectorale> listes = electionsDao.getListesElectorales();
		// on met à 0 les voix et sièges et elimine à false
		int voix = 0;
		int sièges = 0;
		boolean elimine = false;
		for (ListeElectorale liste : listes) {
			liste.setVoix(voix);
			liste.setSieges(sièges);
			liste.setElimine(elimine);
		}
		// on rend ces données persistantes grâce à la couche [dao]
		electionsDao.setListesElectorales(listes);
	}

	@Test
	public void testElections01() {
		System.out.println("testElections01-------------------------------------");
		// récupération de la configuration des élections
		ElectionsConfig electionsConfig = electionsDao.getElectionsConfig();
		int nbSiegesAPourvoir = electionsConfig.getNbSiegesAPourvoir();
		double seuilElectoral = electionsConfig.getSeuilElectoral();
		Assert.assertEquals(6, nbSiegesAPourvoir);
		Assert.assertEquals(0.05, seuilElectoral, 1E-6);

		// listes en compétition
		List<ListeElectorale> listes = electionsDao.getListesElectorales();
		// affichage valeurs lues
		System.out.println("Nombre de sièges à pourvoir : " + nbSiegesAPourvoir);
		System.out.println("Seuil électoral : " + seuilElectoral);
		System.out.println("Listes en compétition ---------------------");
		for (int i = 0; i < listes.size(); i++) {
			System.out.println(listes.get(i));
		}

		// on affecte des voix et des sièges aux listes
		int voix = 0;
		int sièges = 0;
		boolean elimine = false;
		for (ListeElectorale liste : listes) {
			liste.setVoix(voix);
			liste.setSieges(sièges);
			liste.setElimine(elimine);
			voix += 10;
			sièges += 1;
			elimine = !elimine;
		}

		// on rend ces données persistantes grâce à la couche [dao]
		electionsDao.setListesElectorales(listes);

		// on relit les données
		List<ListeElectorale> listesElectorales2 = electionsDao.getListesElectorales();
		// on vérifie les données lues
		Assert.assertEquals(7, listesElectorales2.size());
		voix = 0;
		sièges = 0;
		elimine = false;
		for (ListeElectorale liste : listes) {
			Assert.assertEquals(voix, liste.getVoix());
			Assert.assertEquals(sièges, liste.getSieges());
			Assert.assertEquals(elimine, liste.isElimine());
			voix += 10;
			sièges += 1;
			elimine = !elimine;
		}
		System.out.println("Listes en compétition ---------------------");
		for (int i = 0; i < listes.size(); i++) {
			System.out.println(listes.get(i));
		}
	}
}
