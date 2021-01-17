package ma.ac.emi.coursemi.entities;

import java.io.Serializable;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter@Setter@AllArgsConstructor@NoArgsConstructor
@Data
public class Utilisateur implements Serializable {
	protected Long idUser;
	protected String nom;
	protected String prenom;
	protected String email;
	protected String password;
	protected String photoProfile;
	protected Role role;
	
	public Utilisateur(String nom, String prenom, String email, String password, String photoProfile) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.password = password;
		this.photoProfile = photoProfile;
	}
	
}
