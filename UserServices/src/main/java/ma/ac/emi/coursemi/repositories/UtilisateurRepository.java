package ma.ac.emi.coursemi.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ma.ac.emi.coursemi.entities.Utilisateur;

public interface UtilisateurRepository extends JpaRepository<Utilisateur,Long>{
	Optional<Utilisateur> findByEmail(String email);
	
}
