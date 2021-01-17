package ma.ac.emi.coursemi.entities;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter@Setter
@Data
public class Enseignant extends Utilisateur {
	private Grade grade;
	public Enseignant(String nom, String prenom, String email, String password, String photoProfile,Grade grade) {
		super(nom, prenom, email, password, photoProfile);
		this.grade=grade;
		this.setRole(Role.Enseignant);;
		// TODO Auto-generated constructor stub
	}

}
