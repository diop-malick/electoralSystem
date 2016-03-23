package elections.webjson.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.CharStreams;

import elections.dao.entities.ElectionsConfig;
import elections.dao.entities.ElectionsException;
import elections.dao.entities.ListeElectorale;
import elections.metier.service.IElectionsMetier;

//@Controller
@RestController
public class ElectionsController {

	// dépendances Spring
	@Autowired
	private ObjectMapper jsonMapper;

	@Autowired
	private IElectionsMetier metier;

	/**
	 * Renvoi une Election ave sa toute sa config
	 * 
	 * @return
	 * @throws JsonProcessingException
	 */
	@RequestMapping(value = "/getElectionsConfig", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public Response<ElectionsConfig> getElectionsConfig() throws JsonProcessingException {
		Response<ElectionsConfig> response;
		try {
			response = new Response<>(0, null,
					new ElectionsConfig(metier.getNbSiegesAPourvoir(), metier.getSeuilElectoral()));
		} catch (ElectionsException e1) {
			response = new Response<>(e1.getCode(), e1.getErreurs(), null);
		} catch (RuntimeException e2) {
			response = new Response<>(1000, getErreursForException(e2), null);
		}
		return response;
	}

	/**
	 * Renvoi l'ensemble des listes electorales
	 * 
	 * @return
	 * @throws JsonProcessingException
	 */
	@RequestMapping(value = "/getListesElectorales", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public Response<List<ListeElectorale>> getListesElectorales() throws JsonProcessingException {
		// réponse
		Response<List<ListeElectorale>> response;
		try {
			response = new Response<>(0, null, metier.getListesElectorales());
		} catch (ElectionsException e1) {
			response = new Response<>(1003, e1.getErreurs(), null);
		} catch (Exception e2) {
			response = new Response<>(1003, getErreursForException(e2), null);
		}
		// réponse jSON
		return response;
	}

	/**
	 * Enregistrer les résutlats des listes
	 * 
	 * @param liste
	 * @return
	 * @throws JsonProcessingException
	 */
	@RequestMapping(value = "/setListesElectorales", method = RequestMethod.POST, consumes = "application/json; charset=UTF-8", produces = "application/json; charset=UTF-8")
	public Response<Void> setListesElectorales(@RequestBody List<ListeElectorale> liste)
			throws JsonProcessingException {
		// réponse
		Response<Void> response;
		try {
			// on persiste les liste recupérées
			metier.recordResultats(liste);
			response = new Response<>(0, null, null);
		} catch (ElectionsException e1) {
			response = new Response<>(1000, e1.getErreurs(), null);
		} catch (Exception e2) {
			response = new Response<>(1000, getErreursForException(e2), null);
		}
		// réponse jSON
		return response;
	}

	/**
	 * Calcul le resustat en fonction de l'election et des liste passé en
	 * paramètres
	 * 
	 * @param request
	 * @return
	 * @throws JsonProcessingException
	 */
	@RequestMapping(value = "/calculerSieges", method = RequestMethod.POST, consumes = "application/json; charset=UTF-8", produces = "application/json; charset=UTF-8")
	public Response<List<ListeElectorale>> calculerSieges(@RequestBody List<ListeElectorale> listes, @RequestBody ElectionsConfig election)
			throws JsonProcessingException {
		// réponse
		Response<List<ListeElectorale>> response;
		try {
			response = new Response<>(0, null, metier.calculerSieges(listes, election));
		} catch (ElectionsException e1) {
			response = new Response<>(1003, e1.getErreurs(), null);
		} catch (Exception e2) {
			response = new Response<>(1003, getErreursForException(e2), null);
		}
		// réponse jSON
		return response;
	}

	/*
	 * méthodes privées
	 * =========================================================================
	 */
	
	// liste des messages d'erreur d'une RuntimeException
	private List<String> getErreursForException(Exception e) {
		// on récupère la liste des messages d'erreur de l'exception
		Throwable cause = e;
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
