package es.uniovi.asw.voterAcess.webService.htmlController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import es.uniovi.asw.dbManagement.GetVoter;
import es.uniovi.asw.dbManagement.impl.GetVoterDB;
import es.uniovi.asw.dbManagement.model.Voter;
import es.uniovi.asw.dbManagement.persistence.VoterRepository;
import es.uniovi.asw.voterAcess.webService.responses.errors.ErrorResponse;


/**
 * Se utiliza para gestionar las peticiones de tipo "get" que
 * son recibidas por el servidor web
 *
 */
@Controller
public class HTMLController
{
	private final VoterRepository voterRepository;
	
	
	@Autowired
	HTMLController(VoterRepository voterRepository)
	{
		this.voterRepository = voterRepository;
	}
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String userHTMLget(Model model)
	{
		return "user";
	}
	
	
	@RequestMapping(value = "/validarse", method = RequestMethod.POST)
	public String userHTMLpost(@RequestBody String parametros, Model model)
	{
		String[] parametro = parametros.split("&");
		
		if(parametro[0].split("=").length<=1 || parametro[1].split("=").length<=1)
			return "error";
		String email = parametro[0].split("=")[1].replace("%40", "@");
		String contraseña = parametro[1].split("=")[1];

		
		GetVoter gv = new GetVoterDB(this.voterRepository);
		Voter user = gv.getVoter(email);	
		
		if (user != null && user.getPassword().compareTo(contraseña) == 0)
		{
			model.addAttribute("email", user.getEmail());
			model.addAttribute("name", user.getName());
			model.addAttribute("nif", user.getNIF());
			model.addAttribute("polling", user.getPollingPlace());
		}else{
			return "error";
		}
		
		
		return "result";
	}
	
}
