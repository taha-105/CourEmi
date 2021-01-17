package ma.ac.emi.coursemi.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter@Setter
@Data
public class Etudiant extends Utilisateur {


	public Etudiant(String nom, String prenom, String email, String password, String photoProfile) {
		super(nom, prenom, email, password, photoProfile);
		this.setRole(Role.Etudiant);
		// TODO Auto-generated constructor stub
	}




}
