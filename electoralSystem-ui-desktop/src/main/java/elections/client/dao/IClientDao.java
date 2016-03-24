package elections.client.dao;

import elections.client.entities.User;

public interface IClientDao {

	// requête générique
	// TODO Changer la valeur de reponse en oBJECT POur désérialiser automatiquement, pas de mapping manuelle
	String getResponse(User user, String url, String jsonPost);

}