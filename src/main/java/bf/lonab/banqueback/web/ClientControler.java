package bf.lonab.banqueback.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import bf.lonab.banqueback.entites.Client;
import bf.lonab.banqueback.metier.IClientMetier;
import bf.lonab.banqueback.utilitaire.GestionExceptions;
import bf.lonab.banqueback.utilitaire.Reponse;

@RestController
@CrossOrigin

public class ClientControler {
	@Autowired
	private IClientMetier iCltM;
	@Autowired
	private ObjectMapper mapper;
	
	@PostMapping("/client")
	public String creer(@RequestBody Client clt) throws JsonProcessingException
	{
		Reponse<Client> reponseclt;
		try {
			Client client = iCltM.creer(clt);
			List<String> msg = new ArrayList<>();
			msg.add(String.format("%s a été créé avec succès.", client.getNom()));
			reponseclt = new Reponse<Client>(0,msg,client);
		} catch (Exception e) {
			// TODO: handle exception
			reponseclt = new Reponse<Client>(1,GestionExceptions.getErrorForException(e), null);
		}
		return mapper.writeValueAsString(reponseclt);
	}
	
	@PutMapping("/client")
	public String modifier(@RequestBody Client clt) throws JsonProcessingException {
		Reponse<Client> repClt;
		Client c = iCltM.trouver(clt.getId());
		if (c!=null) {
			try {
				 Client client = iCltM.modifier(clt);
				 List<String> msg = new ArrayList<>();
				 msg.add(String.format("%s a été modifié avec succès", clt.getNom()));
				 repClt = new Reponse<Client>(0,msg,client);
			} catch (Exception e) {
				// TODO: handle exception
				repClt = new Reponse<Client>(1,GestionExceptions.getErrorForException(e), null);
			}			
		}else {
			List<String> msg = new ArrayList<>();
			msg.add(String.format("Client inexistant"));
			repClt = new Reponse<Client>(3,msg,null);
		}
		return mapper.writeValueAsString(repClt);
	}
	
	@GetMapping("/client")
	public String lister() throws JsonProcessingException {
		Reponse<List<Client>> rListClt = null;
		try {
			List<Client>  clients = iCltM.lister();
			if (clients!=null && !clients.isEmpty()) {
				rListClt = new Reponse<List<Client>>(0,null,clients);
			} else 
			{
				List<String> message = new ArrayList<>();
				message.add("Pas de client enrégistré !");
				rListClt=new Reponse<List<Client>>(3,message,null);
			}
		} catch (Exception e) {
			// TODO: handle exception
			rListClt = new Reponse<List<Client>>(1,GestionExceptions.getErrorForException(e), null);
			
		}
		return mapper.writeValueAsString(rListClt);
	}
	
	@GetMapping("/client/{id}")
	public String trouver(@PathVariable Long id) throws JsonProcessingException {
		Reponse<Client> reponse = null;
		try {
			Client clt = iCltM.trouver(id);
			reponse = new Reponse<Client>(0,null, clt);
		} catch (Exception e) {
			// TODO: handle exception
			reponse = new Reponse<Client>(1,GestionExceptions.getErrorForException(e),null);
		}
		return mapper.writeValueAsString(reponse);
	}
	
	
	
	@DeleteMapping("/client/{id}")
	public String supprimer(@PathVariable Long id) throws JsonProcessingException {
		Reponse<Boolean> reponse = null;
		try {
			 List<String> msg = new ArrayList<>();
			 msg.add(String.format("%s a été supprimé avec succès", id));
			 reponse = new Reponse<Boolean>(0, msg, iCltM.supprimer(id));
	
		} catch (Exception e) {
			// TODO: handle exception
			reponse = new Reponse<Boolean>(1,GestionExceptions.getErrorForException(e),null);
		}
		return mapper.writeValueAsString(reponse);
	}
	
}
