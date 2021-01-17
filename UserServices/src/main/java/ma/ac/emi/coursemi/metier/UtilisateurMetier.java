package ma.ac.emi.coursemi.metier;

import java.util.Optional;

import ma.ac.emi.coursemi.entities.Utilisateur;

public interface UtilisateurMetier {
	public Utilisateur register(Utilisateur user);
	public Optional<Utilisateur> findUserByEmail(String email);
	public Optional<Utilisateur> findUserByID(Long id);
}
