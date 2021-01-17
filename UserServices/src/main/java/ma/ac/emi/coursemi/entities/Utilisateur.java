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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter@Setter@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE_USER",discriminatorType = DiscriminatorType.STRING,length = 3)
@Entity
public abstract class Utilisateur implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long idUser;
	@Column(nullable = false,length = 15)
	protected String nom;
	@Column(nullable = false,length = 15)
	protected String prenom;
	@Column(nullable = false,length = 100,unique = true)
	protected String email;
	@Column(nullable = false,length = 100)
	protected String password;
	@Column(nullable = true)
	protected String photoProfile;
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
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
