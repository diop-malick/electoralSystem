package elections.client.dao;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.RequestEntity.BodyBuilder;
import org.springframework.http.RequestEntity.HeadersBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import elections.client.entities.ElectionsException;
import elections.client.entities.User;

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
	 * Encrypt password
	 * @param user
	 * @return
	 */
	private String getBase64(User user) {
		// on encode en base 64 l'utilisateur et son mot de passe - nécessite java 8
		String chaîne = String.format("%s:%s", user.getLogin(), user.getPassword());
		return String.format("Basic %s", new String(Base64.getEncoder().encode(chaîne.getBytes())));
	}
	
	/**
	 * requête générique : factorise les méthode de communication avec le serveur
	 * @param url : URL à contacter
	 * @param jsonPost :  la chaîne jSON à poster, null sinon. Si [jsonPost==null], la requête de l'URL est faite avec un GET, sinon avec un POST
	 */
	@Override
	public String getResponse(User user, String url, String jsonPost) {
		try {
			// exécution requête
			RequestEntity<?> request;
			if (jsonPost == null) {
				HeadersBuilder<?> headersBuilder = RequestEntity.get(new URI(String.format("%s%s", urlServiceWebJson, url)))
						.accept(MediaType.APPLICATION_JSON);
				if (user != null) {
					headersBuilder = headersBuilder.header("Authorization", getBase64(user));
				}
				request = headersBuilder.build();
			} else {
				BodyBuilder bodyBuilder = RequestEntity.post(new URI(String.format("%s%s", urlServiceWebJson, url)))
						.header("Content-Type", "application/json").accept(MediaType.APPLICATION_JSON);
				if (user != null) {
					bodyBuilder = bodyBuilder.header("Authorization", getBase64(user));
				}
				request = bodyBuilder.body(jsonPost);
			}

			// on exécute la requête
			return restTemplate.exchange(request, new ParameterizedTypeReference<String>() {
			}).getBody();
		} catch (URISyntaxException e1) {
			throw new ElectionsException(20, e1);
		} catch (RuntimeException e2) {
			throw new ElectionsException(21, e2);
		}
	}

}
