package elections.client.metier;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import elections.client.dao.IClientDao;
import elections.client.entities.CalculerSiegesDto;
import elections.client.entities.ElectionsConfig;
import elections.client.entities.ElectionsException;
import elections.client.entities.ListeElectorale;
import java.io.IOException;

@Component
public class ElectionsMetier implements IElectionsMetier {

	@Autowired
	private IClientDao dao;
	@Autowired
	private ApplicationContext context;

	// configuration de l'élection
	private ElectionsConfig electionsConfig;
	
	// filtres JSON - mappeurs jSON
	@Autowired
	private ObjectMapper jsonMapper;
	
	@PostConstruct
	public void init() {
		// mappeurs jSON
		ObjectMapper mapperResponse = context.getBean(ObjectMapper.class);
		try {
			// requête
			Response<ElectionsConfig> response = mapperResponse.readValue(
					dao.getResponse("/getElectionsConfig", null), new TypeReference<Response<ElectionsConfig>>() {});
			// erreur ?
			if (response.getStatus() != 0) {
				// on lance 1 exception
				throw new ElectionsException(response.getStatus(), response.getMessages());
			} else {
				electionsConfig = response.getBody();
			}
		} catch (ElectionsException e1) {
			throw e1;
		} catch (IOException | RuntimeException e2) {
			throw new ElectionsException(100, getMessagesForException(e2));
		}
	}

	// obtenir les listes en compétition
	public ElectionsConfig getElectionsConfig() {
		return electionsConfig;
	};
		
	@Override
	public int getNbSiegesAPourvoir() {
		return electionsConfig.getNbSiegesAPourvoir();
	}

	@Override
	public double getSeuilElectoral() {
		return electionsConfig.getSeuilElectoral();
	}
	
	@Override
	public List<ListeElectorale> getListesElectorales() {
		try {
			// requête
			Response<List<ListeElectorale>> response = jsonMapper.readValue(
					dao.getResponse("/getListesElectorales", null), new TypeReference<Response<List<ListeElectorale>>>() {});
			// erreur ?
			if (response.getStatus() != 0) {
				throw new ElectionsException(response.getStatus(), response.getMessages());
			} else {
				return response.getBody();
			}
		} catch (ElectionsException e1) {
			throw e1;
		} catch (IOException | RuntimeException e2) {
			throw new ElectionsException(100, getMessagesForException(e2));
		}
	}

	@Override
	public Void recordResultats(List<ListeElectorale> listesElectorales) { 
		try {
			// requête
			Response<Void> response = jsonMapper.readValue(
					dao.getResponse("/setListesElectorales", jsonMapper.writeValueAsString(listesElectorales)), 
					new TypeReference<Response<Void>>() {}
			);
			// erreur ?
			if (response.getStatus() != 0) {
				throw new ElectionsException(response.getStatus(), response.getMessages());
			} else {
				return response.getBody();
			}
		} catch (ElectionsException e1) {
			throw e1;
		} catch (IOException | RuntimeException e2) {
			throw new ElectionsException(104, getMessagesForException(e2));
		}
	}

	@Override
	public List<ListeElectorale> calculerSieges(List<ListeElectorale> listesElectorales, ElectionsConfig election) {
		try {
			CalculerSiegesDto calculerSiegesDto = new CalculerSiegesDto(election, listesElectorales);
			// requête
			Response<List<ListeElectorale>> response = jsonMapper.readValue(
					dao.getResponse("/calculerSieges", jsonMapper.writeValueAsString(calculerSiegesDto)), 
					new TypeReference<Response<List<ListeElectorale>>>() {}
			);
			// erreur ?
			if (response.getStatus() != 0) {
				throw new ElectionsException(response.getStatus(), response.getMessages());
			} else {
				return response.getBody();
			}
		} catch (ElectionsException e1) {
			throw e1;
		} catch (IOException | RuntimeException e2) {
			throw new ElectionsException(113, getMessagesForException(e2));
		}
	}

	// liste des messages d'erreur d'une exception
	private List<String> getMessagesForException(Exception exception) {
		// on récupère la liste des messages d'erreur de l'exception
		Throwable cause = exception;
		List<String> erreurs = new ArrayList<>();
		while (cause != null) {
			// on récupère le message seulement s'il est !=null et non blanc
			String message = cause.getMessage();
			if (message != null) {
				message = message.trim();
				if (message.length() != 0) {
					erreurs.add(message);
				}
			}
			// cause suivante
			cause = cause.getCause();
		}
		return erreurs;
	}
}
