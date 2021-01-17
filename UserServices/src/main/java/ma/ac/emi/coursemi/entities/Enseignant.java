package ma.ac.emi.coursemi.entities;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter@Setter@NoArgsConstructor
@DiscriminatorValue("ENS")
@Entity
public class Enseignant extends Utilisateur {
	@Column(nullable =true)
	@Enumerated(EnumType.STRING)
	private Grade grade;
	public Enseignant(String nom, String prenom, String email, String password, String photoProfile,Grade grade) {
		super(nom, prenom, email, password, photoProfile);
		this.grade=grade;
		this.setRole(Role.Enseignant);;
		// TODO Auto-generated constructor stub
	}

}
