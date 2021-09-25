package bf.lonab.banqueback.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import bf.lonab.banqueback.entites.Employe;

public interface EmployeRepository extends JpaRepository<Employe, Long> {
	Optional<Employe> findByEmail(String email);
}
