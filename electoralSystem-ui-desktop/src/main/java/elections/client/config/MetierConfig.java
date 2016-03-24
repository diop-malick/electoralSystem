package elections.client.config;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Scope;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

@ComponentScan({ "elections.client.dao","elections.client.metier" })
public class MetierConfig {

	// constantes
	static private final int TIMEOUT = 1000; // les échange sont soumis à un timeout de 1000 ms = 1s
	static private final String URL_WEBJSON = "http://localhost:8080";

	// assure les échanges avec le service web / JSON
	// TODO ajouter le convertisseur json jackson
	@Bean
	public RestTemplate restTemplate(int timeout) {
		// création du composant RestTemplate
		HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
		RestTemplate restTemplate = new RestTemplate(factory);
		// timeout des échanges
		factory.setConnectTimeout(timeout);
		factory.setReadTimeout(timeout);
		// résultat
		return restTemplate;
	}

	@Bean
	public int timeout() {
		return TIMEOUT;
	}

	@Bean
	public String urlWebJson() {
		return URL_WEBJSON;
	}

	// mappeur jSON
	@Bean
	@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public ObjectMapper jsonMapper() {
		return new ObjectMapper();
	}
}