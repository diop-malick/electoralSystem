package elections.metier.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import elections.dao.entities.ElectionsConfig;
import elections.dao.entities.ListeElectorale;
import elections.dao.service.ElectionDaoJPa;
import elections.dao.service.IElectionsDao;

@Component
public class ElectionsMetier implements IElectionsMetier {

	
	  private static final Logger logger = LoggerFactory.getLogger(ElectionsMetier.class);

	  
	// le point d'accès à la couche [dao] instanciée par [Spring]
	@SuppressWarnings("unused")
	@Autowired
	private IElectionsDao electionsDao;

	@Override
	@Cacheable("electionsConfig")
	public ElectionsConfig getElectionsConfig() {
		return electionsDao.getElectionsConfig();
	}	
	
	// nombre de sièges à pourvoir
	@Cacheable("electionsConfig")
	@Override
	public int getNbSiegesAPourvoir() {
		int nbrSiege = 0;
		ElectionsConfig electionConfig = getElectionsConfig();
		if (electionConfig != null) {
			nbrSiege = electionConfig.getNbSiegesAPourvoir();
		}
		return nbrSiege;
	}
	
	// seuil électoral
	@Cacheable("electionsConfig")
	@Override
	public double getSeuilElectoral() {
		double seuil = 0;
		ElectionsConfig electionConfig = getElectionsConfig();
		if (electionConfig != null) {
			seuil = electionConfig.getSeuilElectoral();
		}
		return seuil;
	}
	
	// TODO to improve and factorise
	// calcul des sièges obtenus
	@Override
	public List<ListeElectorale> calculerSieges(List<ListeElectorale> listesElectorales, ElectionsConfig election) {
		
		Integer totalVoix = 0; 
		Integer nbVoixUtiles = 0;
		Integer quotientElectoral;
		int nbSiegesPourvus = 0;
		
		
		// Calcul du TOTAL DES VOIX de tous les lsite
		// totalVoix = listesElectorales.stream().map(e -> e.getVoix()).reduce(0, (x, y) -> x + y);
		totalVoix = listesElectorales.stream().map(e -> e.getVoix()).reduce(0, Integer::sum);
		logger.error("totalVoix: " + totalVoix);
		
		// calcul des suffrages exprimés utiles
		// TOTAL DES voix des listes ayant dépassés le seuil
		// On Elimine les listes n'ayant pas atteint la barre du % minimum défini
		// on considère que tout les liste sont en course au départ : elimine à true et nbr Voix à 0
		
		// way 1	
		for (int i = 0; i <= (listesElectorales.size() - 1) ; i++) {
			
			System.out.println(listesElectorales.get(i).getVoix() + " / " + (listesElectorales.get(i).getVoix() * 100 / totalVoix));
			System.out.println((listesElectorales.get(i).getVoix() * 100 / totalVoix) < (election.getSeuilElectoral() * 100));
			
			if ((listesElectorales.get(i).getVoix() * 100 / totalVoix) < (election.getSeuilElectoral() * 100)) {
				listesElectorales.get(i).setElimine(true);
			} else {	
				listesElectorales.get(i).setElimine(false);
				nbVoixUtiles += listesElectorales.get(i).getVoix();
			}	
		}
		logger.info("nbVoixUtiles: " + nbVoixUtiles);
		
		// way 2 - java 8
		// listesElectorales.stream().filter(item -> ((item.getVoix()/totalVoix) < election.getSeuilElectoral())).forEach(l -> l.setElimine(true));		
		// nbVoixUtiles =listesElectorales.stream().filter(x -> Boolean.FALSE.equals(x.isElimine())).map(e -> e.getVoix()).reduce(0, Integer::sum); 
			
		// y-a-t-il des listes non éliminées ?
		// si nombre vois utile = 0
		if (nbVoixUtiles == 0) {
			logger.info("Erreur : toutes les listes ont été éliminées");
			return null;
		}
		
		
		// Calcul le quotient Electoral : Nombre Suffrages Exprimés utiles / Nombre de siège à pourvoir
		quotientElectoral = nbVoixUtiles / election.getNbSiegesAPourvoir();
		logger.info("quotientElectoral: " + quotientElectoral);
		
		// on stocke les moyennes
		List<Double> moyennesListes  = new ArrayList<>(); 
		
		// Premiere répartition des sièges au quotient
		for (int i = 0; i <= (listesElectorales.size() - 1) ; i++) {
			// si non eliminée
			if ((Boolean.FALSE.equals(listesElectorales.get(i).isElimine()))) {
				int nbrSiege = listesElectorales.get(i).getVoix() / quotientElectoral;
				moyennesListes.add(i, ((double)listesElectorales.get(i).getVoix() / (nbrSiege + 1)));
				nbSiegesPourvus += nbrSiege; 
				listesElectorales.get(i).setSieges(nbrSiege);
			} 
			// si eliminée
			else {	
				// listesElectorales.get(i).setVoix(0);
				listesElectorales.get(i).setSieges(0);
			}	
		
		}
				
		// indice de la plus ofrte moyenne
		int iMax = -1;
		// moyenne max
		double moyenneMax;
		
		// répartition des sièges restants à la plus forte moyenne
		// 1 siège est attribué à chaque tour de boucle
		for (int iSiege = 0; iSiege <=  (election.getNbSiegesAPourvoir() - nbSiegesPourvus -1) ; iSiege++) {
			
			moyenneMax =  -1;
			
			// recherche de la liste ayant la + forte moyenne
			for (int i = 0; i <= (listesElectorales.size() - 1) ; i++) {
				if ((Boolean.FALSE.equals(listesElectorales.get(i).isElimine()))) {
					if (moyennesListes.get(i) > moyenneMax) {
						moyenneMax = moyennesListes.get(i);
						iMax = i; // indice de la liste ayant la moyenne max
					}
				}
			}			
			// on attribue 1 siège à la liste de + forte moyenne
			if (iMax != -1) {
				int tmp = listesElectorales.get(iMax).getSieges();
				listesElectorales.get(iMax).setSieges(tmp + 1);						
				// et on change sa moyenne // tiem à l'index iMax
				moyennesListes.set(iMax, ( 
							(double) listesElectorales.get(iMax).getVoix() / (listesElectorales.get(iMax).getSieges() + 1)
						) 
				);
			}
			
		}
		
		
		/**	Classement Final sans tri */
		
		return listesElectorales;
		
	}

	// listes en compétition
	@Override
	public List<ListeElectorale> getListesElectorales() {
		return electionsDao.getListesElectorales();
	}

	// sauvegarde des résultats
	@Override
	public void recordResultats(List<ListeElectorale> listesElectorales) {
		electionsDao.setListesElectorales(listesElectorales);
	}




}
