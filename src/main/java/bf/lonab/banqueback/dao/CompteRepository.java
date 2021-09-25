package bf.lonab.banqueback.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import bf.lonab.banqueback.entites.Compte;

public interface CompteRepository extends JpaRepository<Compte, Long> {

}
