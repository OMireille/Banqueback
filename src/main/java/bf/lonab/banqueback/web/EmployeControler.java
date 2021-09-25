package bf.lonab.banqueback.web;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import bf.lonab.banqueback.entites.Employe;
import bf.lonab.banqueback.entites.Role;
import bf.lonab.banqueback.entites.RoleName;
import bf.lonab.banqueback.metier.IEmployeMetier;
import bf.lonab.banqueback.metier.IRoleMetier;
import bf.lonab.banqueback.security.JwtAuthenticationResponse;
import bf.lonab.banqueback.security.JwtTokenProvider;
import bf.lonab.banqueback.utilitaire.GestionExceptions;
import bf.lonab.banqueback.utilitaire.Reponse;

@RestController
@CrossOrigin
@RequestMapping("/api/auth") /* ajoute une racine à l'URL */
public class EmployeControler {
	@Autowired
	private IEmployeMetier iEmpM;
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	IRoleMetier roleMetier;

	@Autowired
	JwtTokenProvider tokenProvider;
	@Autowired
	private ObjectMapper mapper;

	
	
	@PostMapping("/signin")
	public String authenticateUser(@Valid @RequestBody Employe loginRequest) throws JsonProcessingException {
		Reponse<ResponseEntity<?>> reponse;
		Authentication authentication = authenticationManager.authenticate(

		new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPwd()));
		SecurityContextHolder.getContext().setAuthentication(authentication);

		String jwt = tokenProvider.generateToken(authentication);
		reponse = new Reponse<ResponseEntity<?>>(0, null, ResponseEntity.ok(new JwtAuthenticationResponse(jwt)));
		return mapper.writeValueAsString(reponse);

	}

	@PostMapping("/signup")
	@ResponseStatus(code = HttpStatus.CREATED)
	public String createUser(@RequestBody Employe signUpRequest) throws Exception {
		
        Reponse<Employe> reponse = null;
        Employe employe = null;
		try {
			Role userRole = roleMetier.findByNomr(RoleName.ROLE_DIRECTEUR);
			signUpRequest.setRoles(Collections.singleton(userRole));
             employe = iEmpM.creer(signUpRequest);
			System.out.println("Voir le nom complet de la personne recupéré:" + employe.getNomComplet());
			

			List<String> messages = new ArrayList<>();
			messages.add(String.format("%s  a été créé avec succès", employe.getId()));
			reponse = new Reponse<Employe>(0, messages, employe);

		} catch (Exception e) {
			reponse = new Reponse<Employe>(1, GestionExceptions.getErrorForException(e), null);
		}
		return mapper.writeValueAsString(reponse);
	}
	

	
	@PostMapping("/employe")
	public String creer(@RequestBody Employe emp) throws JsonProcessingException
	{
		Reponse<Employe> reponse;
		try {
			Employe employe = iEmpM.creer(emp);
			List<String> msg = new ArrayList<>();
			msg.add(String.format("%s a été créé avec succès.", employe.getNom()));
			reponse = new Reponse<Employe>(0,msg,employe);
		} catch (Exception e) {
			// TODO: handle exception
			reponse = new Reponse<Employe>(1,GestionExceptions.getErrorForException(e), null);
		}
		return mapper.writeValueAsString(reponse);
	}
	
	@PutMapping("/employe")
	public String modifier(@RequestBody Employe emp) throws JsonProcessingException {
		Reponse<Employe> reponse;
		Employe empl = iEmpM.trouver(emp.getId());
		if (empl!=null) {
			try {
				 Employe employe = iEmpM.modifier(emp);
				 List<String> msg = new ArrayList<>();
				 msg.add(String.format("%s a été modifié avec succès", emp.getNom()));
				 reponse = new Reponse<Employe>(0,msg,employe);
			} catch (Exception e) {
				// TODO: handle exception
				reponse = new Reponse<Employe>(1,GestionExceptions.getErrorForException(e), null);
			}			
		}else {
			List<String> msg = new ArrayList<>();
			msg.add(String.format("Employe inexistant"));
			reponse = new Reponse<Employe>(3,msg,null);
		}
		return mapper.writeValueAsString(reponse);
	}
	
	@GetMapping("/employe")
	public String lister() throws JsonProcessingException {
		Reponse<List<Employe>> reponse = null;
		try {
			List<Employe>  employes = iEmpM.lister();
			if (employes!=null && !employes.isEmpty()) {
				reponse = new Reponse<List<Employe>>(0,null,employes);
			} else 
			{
				List<String> message = new ArrayList<>();
				message.add("Aucun employé enrégistré !");
				reponse=new Reponse<List<Employe>>(3,message,null);
			}
		} catch (Exception e) {
			// TODO: handle exception
			reponse = new Reponse<List<Employe>>(1,GestionExceptions.getErrorForException(e), null);
			
		}
		return mapper.writeValueAsString(reponse);
	}
	
	@GetMapping("/employe/{id}")
	public String trouver(@PathVariable Long id) throws JsonProcessingException {
		Reponse<Employe> reponse = null;
		try {
			Employe clt = iEmpM.trouver(id);
			reponse = new Reponse<Employe>(0,null, clt);
		} catch (Exception e) {
			// TODO: handle exception
			reponse = new Reponse<Employe>(1,GestionExceptions.getErrorForException(e),null);
		}
		return mapper.writeValueAsString(reponse);
	}
	
	
	@DeleteMapping("/employe/{id}")
	public String supprimer(@PathVariable Long id) throws JsonProcessingException {
		Reponse<Boolean> reponse = null;
		try {
			 List<String> msg = new ArrayList<>();
			 msg.add(String.format("%s a été supprimé avec succès", id));
			 reponse = new Reponse<Boolean>(0, msg, iEmpM.supprimer(id));

		} catch (Exception e) {
			// TODO: handle exception
			reponse = new Reponse<Boolean>(1,GestionExceptions.getErrorForException(e),null);
		}
		return mapper.writeValueAsString(reponse);
	}
	
}
