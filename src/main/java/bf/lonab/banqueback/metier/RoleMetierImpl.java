package bf.lonab.banqueback.metier;

import java.util.List;

import org.springframework.stereotype.Service;

import bf.lonab.banqueback.dao.RoleRepository;
import bf.lonab.banqueback.entites.Role;
import bf.lonab.banqueback.entites.RoleName;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RoleMetierImpl implements IRoleMetier {
    private RoleRepository rRepository;
	@Override
	public Role creer(Role entite) {
		// TODO Auto-generated method stub
		return rRepository.save(entite);
	}

	@Override
	public Role modifier(Role entite) {
		// TODO Auto-generated method stub
		return rRepository.save(entite);
	}

	@Override
	public boolean supprimer(Long identifiant) {
		// TODO Auto-generated method stub
		rRepository.deleteById(identifiant);
		return true;
	}

	@Override
	public List<Role> lister() {
		// TODO Auto-generated method stub
		return rRepository.findAll();
	}

	@Override
	public Role trouver(Long identifiant) {
		// TODO Auto-generated method stub
		return rRepository.findById(identifiant).get();
	}

	@Override
	public Role findByNomr(RoleName rname) {
		// TODO Auto-generated method stub
		return rRepository.findByNomr(rname).get();
	}

}
