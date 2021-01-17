package ma.ac.emi.coursemi.controllers;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ma.ac.emi.coursemi.conf.JwtTokenUtil;
import ma.ac.emi.coursemi.conf.SecurityParams;
import ma.ac.emi.coursemi.entities.Utilisateur;
import ma.ac.emi.coursemi.metier.UserMetier;
import ma.ac.emi.coursemi.request.JwtRequest;
import ma.ac.emi.coursemi.response.JwtResponse;

@RestController
public class Home {
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserDetailsService jwtInMemoryUserDetailsService;
	@Autowired
	private UserMetier userMetier;

	@PostMapping(value = "/login")
	public ResponseEntity<?> generateAuthenticationToken(@RequestBody JwtRequest authenticationRequest)
			throws Exception {;
			
		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
		
		final UserDetails userDetails = jwtInMemoryUserDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());
	
		final String token = jwtTokenUtil.generateToken(userDetails);
	

		Utilisateur userLogin = this.userMetier.findUserByEmail(authenticationRequest.getUsername());

		return ResponseEntity.ok(new JwtResponse(token,SecurityParams.EXPIRATION, userLogin.getIdUser(), userLogin.getRole().name()));
	}
													
	private void authenticate(String username, String password) throws Exception {
		Objects.requireNonNull(username);
		Objects.requireNonNull(password);
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
		
	}
	@GetMapping(value = "token/{token}")
	public ResponseEntity<Object> getUserByToken(@PathVariable(value ="token" ) String token){
		Utilisateur user ;
		try {
			user = this.userMetier.findUserByEmail(this.jwtTokenUtil.getUsernameFromToken(token));
			Map<String, Object> mapResponse = new HashMap<String, Object>();
			mapResponse.put("message", "User Fetched succesfully");
			mapResponse.put("result",user);
			return new ResponseEntity<Object>(mapResponse, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("User with this token not found !", HttpStatus.NOT_FOUND);
		}
		
	}

}
