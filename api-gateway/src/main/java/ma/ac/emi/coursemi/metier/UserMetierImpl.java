package ma.ac.emi.coursemi.metier;

import java.util.List;
import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import ma.ac.emi.coursemi.entities.Enseignant;
import ma.ac.emi.coursemi.entities.Etudiant;
import ma.ac.emi.coursemi.entities.Utilisateur;

@Service
public class UserMetierImpl implements UserMetier{
	@Autowired
	private RestTemplate restTemplate;

	@Override
	public Utilisateur findUserByEmail(String login) {
		Utilisateur user = this.restTemplate.getForObject("http://localhost:9091/users/username/"+login, Utilisateur.class);
		return user;
	}






}
