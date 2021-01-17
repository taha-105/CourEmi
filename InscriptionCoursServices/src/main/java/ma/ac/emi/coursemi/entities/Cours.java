package ma.ac.emi.coursemi.entities;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
@Getter@Setter@AllArgsConstructor
public class Cours {

	private Long idCours ;
	
	private String titre;
	
	private String description;
	private Long idCategorie;
	private Categorie categorie;
	private LocalDate dateDebut;
	private LocalDate dateFin;
	private Long idUser;
	private String urlImage;
	private Enseignant enseignant;
	private Integer nbrInscri;
}
