package bf.lonab.banqueback.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import bf.lonab.banqueback.entites.Versement;

public interface VersementRepository extends JpaRepository<Versement, Long> {

}
