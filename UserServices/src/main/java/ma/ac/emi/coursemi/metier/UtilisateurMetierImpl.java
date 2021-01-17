package ma.ac.emi.coursemi.metier;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.ac.emi.coursemi.entities.Utilisateur;
import ma.ac.emi.coursemi.repositories.UtilisateurRepository;
@Service
public class UtilisateurMetierImpl implements UtilisateurMetier{
	@Autowired
	private UtilisateurRepository utilisateurRepository;
	@Override
	public Utilisateur register(Utilisateur user) {
		// TODO Auto-generated method stub
		return this.utilisateurRepository.save(user);
	}

	@Override
	public Optional<Utilisateur> findUserByEmail(String email) {
		// TODO Auto-generated method stub
		return this.utilisateurRepository.findByEmail(email);
	}

	@Override
	public Optional<Utilisateur> findUserByID(Long id) {
		// TODO Auto-generated method stub
		return this.utilisateurRepository.findById(id);
	}


}
