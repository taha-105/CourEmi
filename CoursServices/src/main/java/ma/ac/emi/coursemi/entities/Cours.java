package ma.ac.emi.coursemi.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.hibernate.annotations.ManyToAny;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter@Setter@NoArgsConstructor@AllArgsConstructor
@Entity
public class Cours {
	@Id@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idCours ;
	@Column(nullable = false,length = 1000)
	private String titre;
	@Column(nullable = false,length = 100000)
	private String description;
	private Long idCategorie;
	@Transient
	private Categorie categorie;
	@Column(nullable=false)
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone="GMT")
	private LocalDate dateDebut;
	@Column(nullable=false)
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone="GMT")
	private LocalDate dateFin;
	private Long idUser;
	private String urlImage;
	@Transient
	private Enseignant enseignant;
	@Transient
	private Integer nbrInscri;
	
}
