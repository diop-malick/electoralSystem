package elections.webjson.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import elections.dao.entities.ElectionsConfig;
import elections.dao.entities.ElectionsException;
import elections.metier.service.IElectionsMetier;

@Controller
public class ElectionsController {

	// dépendances Spring
	@Autowired
	private ObjectMapper jsonMapper;

	@Autowired
	private IElectionsMetier metier;

	@RequestMapping(value = "/getElectionsConfig", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String getElectionsConfig() throws JsonProcessingException {
		// réponse
		Response<ElectionsConfig> response;
		try {
			response = new Response<>(0, null,
					new ElectionsConfig(metier.getNbSiegesAPourvoir(), metier.getSeuilElectoral()));
		} catch (ElectionsException e1) {
			response = new Response<>(e1.getCode(), e1.getErreurs(), null);
		} catch (RuntimeException e2) {
			response = new Response<>(1000, getErreursForException(e2), null);
		}
		// réponse
		return jsonMapper.writeValueAsString(response);
	}

	@RequestMapping(value = "/getListesElectorales", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String getListesElectorales() throws JsonProcessingException {
		throw new UnsupportedOperationException("Not supported yet");
	}

	@RequestMapping(value = "/setListesElectorales", method = RequestMethod.POST, consumes = "application/json; charset=UTF-8", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String setListesElectorales(HttpServletRequest request) throws JsonProcessingException {
		throw new UnsupportedOperationException("Not supported yet");
	}

	@RequestMapping(value = "/calculerSieges", method = RequestMethod.POST, consumes = "application/json; charset=UTF-8", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String calculerSieges(HttpServletRequest request) throws JsonProcessingException {
		throw new UnsupportedOperationException("Not supported yet");
	}

	// méthodes privées -----------------------------
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
