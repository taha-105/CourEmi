package ma.ac.emi.coursemi.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.ac.emi.coursemi.entities.Grade;
import ma.ac.emi.coursemi.entities.Role;
@Getter@Setter@AllArgsConstructor@NoArgsConstructor
public class UserRequest {
	private String nom;
	private String prenom;
	private String email;
	private String password;
	private String photoProfile;
	private Role role;
	private Grade grade; 

}
