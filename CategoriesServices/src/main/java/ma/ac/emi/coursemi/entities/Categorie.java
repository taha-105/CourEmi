package ma.ac.emi.coursemi.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter@Setter@NoArgsConstructor
public class Categorie {
	@Id@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idCategorie;
	@Column(nullable = false,length = 30)
	private String titre;
	@Column(nullable = false,length = 100000)
	private String description;
	public Categorie(String titre, String description) {
		super();
		this.titre = titre;
		this.description = description;
	}
	
}
