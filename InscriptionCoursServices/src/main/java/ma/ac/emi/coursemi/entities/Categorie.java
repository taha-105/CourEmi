package ma.ac.emi.coursemi.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter@Setter@AllArgsConstructor
public class Categorie {
	private Long idCategorie;
	private String titre;
	private String description;
}
