package ma.ac.emi.coursemi.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter@Setter@AllArgsConstructor
@Data
public abstract class Utilisateur implements Serializable {
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
