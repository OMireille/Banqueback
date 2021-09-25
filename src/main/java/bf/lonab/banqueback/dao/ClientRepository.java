package bf.lonab.banqueback.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import bf.lonab.banqueback.entites.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
    List<Client> findByNom(String nom);
    Client findByPrenom(String prenom);
}