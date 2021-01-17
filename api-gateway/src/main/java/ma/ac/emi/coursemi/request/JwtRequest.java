package ma.ac.emi.coursemi.request;

import java.io.Serializable;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.ac.emi.coursemi.entities.Role;
@Getter@Setter@NoArgsConstructor@AllArgsConstructor
public class JwtRequest implements Serializable {

	private static final long serialVersionUID = 5926468583005150707L;

	private String username;
	private String password;



}