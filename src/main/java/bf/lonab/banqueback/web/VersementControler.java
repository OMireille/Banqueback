package bf.lonab.banqueback.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import bf.lonab.banqueback.entites.Compte;
import bf.lonab.banqueback.entites.Versement;
import bf.lonab.banqueback.metier.ICompteMetier;
import bf.lonab.banqueback.metier.IVersementMetier;
import bf.lonab.banqueback.utilitaire.GestionExceptions;
import bf.lonab.banqueback.utilitaire.Reponse;

@RestController
@CrossOrigin
@RequestMapping("/api/auth")

public class VersementControler {
	@Autowired
	private IVersementMetier ivm;
	@Autowired
	private ObjectMapper mapper;
	@PostMapping("/versement")
	public String creer(@RequestBody Versement v) throws JsonProcessingException {
		Reponse<Versement> reponse;
		try {
		  Versement verse =	ivm.creer(v);
		  List<String> message = new ArrayList<>();
		  message.add(String.format("%s a été crée avec succès.", verse.getId()));
		  reponse = new Reponse<Versement>(0, message, verse);
		} catch (Exception e) {
			// TODO: handle exception
		  reponse = new Reponse<Versement>(1, GestionExceptions.getErrorForException(e), null);
		}
	return mapper.writeValueAsString(reponse);	
	}

	@GetMapping("/versement")
	public String lister() throws JsonProcessingException {
		Reponse<List<Versement>> reponse;
		try {
			List<Versement> listVerses = ivm.lister();
			if (listVerses!=null && !listVerses.isEmpty()) {
				reponse = new Reponse<List<Versement>>(0,null,listVerses);
			} 
			else
			{
				List<String> message = new ArrayList<>();
				message.add("Pas de versement enrégistré !");
				reponse = new Reponse<List<Versement>>(3,message,null);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			reponse = new Reponse<List<Versement>>(1,GestionExceptions.getErrorForException(e),null);
		}
		return mapper.writeValueAsString(reponse);
	}
}
