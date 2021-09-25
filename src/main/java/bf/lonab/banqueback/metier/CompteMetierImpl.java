package bf.lonab.banqueback.metier;

import java.util.List;

import org.springframework.stereotype.Service;

import bf.lonab.banqueback.dao.CompteRepository;
import bf.lonab.banqueback.entites.Compte;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CompteMetierImpl implements ICompteMetier {
	private CompteRepository cr;
	@Override
	public Compte creer(Compte entite) {
		// TODO Auto-generated method stub
		return cr.save(entite);
	}

	@Override
	public Compte modifier(Compte entite) {
		// TODO Auto-generated method stub
		return cr.save(entite);
	}

	@Override
	public boolean supprimer(Long identifiant) {
		// TODO Auto-generated method stub
		cr.deleteById(identifiant);
		return true;
	}

	@Override
	public List<Compte> lister() {
		// TODO Auto-generated method stub
		return cr.findAll();
	}

	@Override
	public Compte trouver(Long identifiant) {
		// TODO Auto-generated method stub
		return cr.findById(identifiant).get();
	}

}
