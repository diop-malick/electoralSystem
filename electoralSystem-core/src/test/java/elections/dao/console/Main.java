package elections.dao.console;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import elections.dao.config.DaoConfig;
import elections.dao.entities.ElectionsConfig;
import elections.dao.entities.ListeElectorale;
import elections.dao.service.IElectionsDao;

public class Main {

  // source de données
  private static IElectionsDao dao;

  public static void main(String[] args) {
    // récupération de la source de données
    try (
            AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(DaoConfig.class)) {
      // récupération de la source de données
      dao = ctx.getBean(IElectionsDao.class);
      // contenu des deux tables
      ElectionsConfig electionsConfig = dao.getElectionsConfig();
      List<ListeElectorale> listes = dao.getListesElectorales();
      // affichage
      System.out.println(String.format("Nombre de sièges à pourvoir : %d", electionsConfig.getNbSiegesAPourvoir()));
      System.out.println(String.format("Seuil électoral : %5.2f", electionsConfig.getSeuilElectoral()));
      System.out.println("Listes candidates----------------");
      for (ListeElectorale liste : listes) {
        System.out.println(liste);
      }
      // fermeture contexte Spring
    }
  }

}
