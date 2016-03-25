package elections.webjson.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import elections.webjson.entities.Response;


@Controller
public class AuthenticateController {

	// dépendances Spring
	@Autowired
	private ApplicationContext context;

	@RequestMapping(value = "/authenticate", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String authenticate() throws JsonProcessingException {
		// réponse jSON
		ObjectMapper mapperResponse = context.getBean(ObjectMapper.class);
		return mapperResponse.writeValueAsString(new Response<Void>(0, null, null));
	}

}