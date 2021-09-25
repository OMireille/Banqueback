package bf.lonab.banqueback.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import bf.lonab.banqueback.entites.Role;
import bf.lonab.banqueback.entites.RoleName;

public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByNomr(RoleName rname);
	
}
