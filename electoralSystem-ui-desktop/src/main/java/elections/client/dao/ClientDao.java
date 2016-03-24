package elections.client.dao;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import elections.client.entities.ElectionsException;

/**
 * implémente les échanges avec le service web / jSON :
 * @author Malick
 *
 */
@Component
public class ClientDao implements IClientDao {

	// data 
	// injection du composant RestTemplate et de l'url web/JSON
	@Autowired
	protected RestTemplate restTemplate;
	@Autowired
	protected String urlServiceWebJson;

	/**
	 * requête générique : factorise les méthode de communication avec le serveur
	 * @param url : URL demandé 
	 * @param jsonPost :  la chaîne jSON à poster, null sinon. Si [jsonPost==null], la requête de l'URL est faite avec un GET, sinon avec un POST
	 */
	@Override
	public String getResponse(String url, String jsonPost) {

		try {
			// url : URL à contacter
			// jsonPost : la valeur jSON à poster

			// exécution requête
			RequestEntity<?> request;
			if (jsonPost != null) {
				// requête POST
				request = RequestEntity.post(new URI(String.format("%s%s", urlServiceWebJson, url)))
						.header("Content-Type", "application/json").accept(MediaType.APPLICATION_JSON).body(jsonPost);
			} else {
				// requête GET
				request = RequestEntity.get(new URI(String.format("%s%s", urlServiceWebJson, url)))
						.accept(MediaType.APPLICATION_JSON).build();
			}
			// on exécute la requête
			return restTemplate.exchange(request, new ParameterizedTypeReference<String>(){}).getBody();
			
		} catch (URISyntaxException e1) {
			throw new ElectionsException(200, e1);
		} catch (RuntimeException e2) {
			throw new ElectionsException(201, e2);
		}
	}
}
