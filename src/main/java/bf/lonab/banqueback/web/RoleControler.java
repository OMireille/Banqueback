package bf.lonab.banqueback.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import bf.lonab.banqueback.entites.Compte;
import bf.lonab.banqueback.entites.Role;
import bf.lonab.banqueback.metier.IRoleMetier;
import bf.lonab.banqueback.utilitaire.GestionExceptions;
import bf.lonab.banqueback.utilitaire.Reponse;

@RestController
@CrossOrigin
public class RoleControler {
	@Autowired
	private IRoleMetier iRm;
	@Autowired
	private ObjectMapper mapper;
	
	@PostMapping("/role")	
	public String creer(@RequestBody Role r) throws JsonProcessingException {
		Reponse<Role> reponse;
		try {
			  Role role =	iRm.creer(r);
			  List<String> message = new ArrayList<>();
			  message.add(String.format("%s a été crée avec succès.", role.getNomr()));
			  reponse = new Reponse<Role>(0, message, role);
			} catch (Exception e) {
				// TODO: handle exception
			  reponse = new Reponse<Role>(1, GestionExceptions.getErrorForException(e), null);
			}
		return mapper.writeValueAsString(reponse);	
	}
}
