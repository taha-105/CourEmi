package ma.ac.emi.coursemi.metier;




import ma.ac.emi.coursemi.entities.Utilisateur;

public interface UserMetier {
	
    public Utilisateur findUserByEmail(String login);
   
}
