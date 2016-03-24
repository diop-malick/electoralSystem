package elections.client.dao;

public interface IClientDao {

	// requête générique
	// TODO Changer la valeur de reponse en oBJECT POur désérialiser automatiquement, pas de mapping manuelle
	String getResponse(String url, String jsonPost);

}