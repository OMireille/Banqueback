package bf.lonab.banqueback.metier;

import java.util.List;

import org.springframework.stereotype.Service;

import bf.lonab.banqueback.dao.VersementRepository;
import bf.lonab.banqueback.entites.Versement;
import bf.lonab.banqueback.exceptions.InvalideBanquebackException;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class VersementMetierImpl implements IVersementMetier{
	private VersementRepository verseR;

	@Override
	public Versement creer(Versement entite) throws InvalideBanquebackException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Versement modifier(Versement entite) {
		// TODO Auto-generated method stub
		return verseR.save(entite);
	}

	@Override
	public boolean supprimer(Long identifiant) {
		// TODO Auto-generated method stub
		verseR.deleteById(identifiant);
		return true;
	}

	@Override
	public List<Versement> lister() {
		// TODO Auto-generated method stub
		return verseR.findAll();
	}

	@Override
	public Versement trouver(Long identifiant) {
		// TODO Auto-generated method stub
		return verseR.findById(identifiant).get();
	}

}
