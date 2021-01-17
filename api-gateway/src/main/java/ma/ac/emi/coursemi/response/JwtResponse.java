package ma.ac.emi.coursemi.response;


import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter@Setter@NoArgsConstructor@AllArgsConstructor
public class JwtResponse implements Serializable {

	private static final long serialVersionUID = -8091879091924046844L;
	private String token;
	private long expireIn;
	private Long memberId;
	private String role;
	

	
}