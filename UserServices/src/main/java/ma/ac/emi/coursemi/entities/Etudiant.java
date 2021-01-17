package ma.ac.emi.coursemi.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor

@DiscriminatorValue("ETD")
@Entity
public class Etudiant extends Utilisateur {


	public Etudiant(String nom, String prenom, String email, String password, String photoProfile) {
		super(nom, prenom, email, password, photoProfile);
		this.setRole(Role.Etudiant);
		// TODO Auto-generated constructor stub
	}




}
