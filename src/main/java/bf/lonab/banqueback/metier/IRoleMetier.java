package bf.lonab.banqueback.metier;

import java.util.Optional;

import bf.lonab.banqueback.entites.Role;
import bf.lonab.banqueback.entites.RoleName;
import bf.lonab.banqueback.utilitaire.InterfaceMetier;

public interface IRoleMetier extends InterfaceMetier<Role, Long> {
	Role findByNomr(RoleName rname);
}
