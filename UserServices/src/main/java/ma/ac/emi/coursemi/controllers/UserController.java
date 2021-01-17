package ma.ac.emi.coursemi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ma.ac.emi.coursemi.entities.Enseignant;
import ma.ac.emi.coursemi.entities.Etudiant;
import ma.ac.emi.coursemi.entities.Role;
import ma.ac.emi.coursemi.entities.Utilisateur;
import ma.ac.emi.coursemi.metier.StorageFileMetier;
import ma.ac.emi.coursemi.metier.UtilisateurMetier;
import ma.ac.emi.coursemi.request.UserRequest;

@RestController
public class UserController {
	
	@Autowired
	private UtilisateurMetier utilisateurMetier;
	@Autowired
	private PasswordEncoder bcryptPassword;
	@Autowired
	private StorageFileMetier storageFileMetier;

	@PostMapping("/sign-up")
	public Utilisateur register(@RequestParam("user") String userData,@RequestParam(value="image",required = false) MultipartFile file) throws Exception {
		UserRequest userReq = new ObjectMapper().readValue(userData, UserRequest.class) ;
		if(this.utilisateurMetier.findUserByEmail(userReq.getEmail()).isPresent()) {
			throw new Exception("Cet Email est déja pris ! essayer avec un autre . ");
		}
		Utilisateur user=null;
		if(userReq.getRole().equals(Role.Enseignant)) {
			user = new Enseignant(userReq.getNom(), userReq.getPrenom(), userReq.getEmail(),bcryptPassword.encode(userReq.getPassword()), userReq.getPhotoProfile(), userReq.getGrade());
			user.setPhotoProfile("http://localhost:9091/users/upload-dir/"+this.storageFileMetier.store(file));
		}
		else if(userReq.getRole().equals(Role.Etudiant)) {
			user = new Etudiant(userReq.getNom(), userReq.getPrenom(), userReq.getEmail(),bcryptPassword.encode(userReq.getPassword()), userReq.getPhotoProfile());
			user.setPhotoProfile("http://localhost:9091/users/upload-dir/"+this.storageFileMetier.store(file));		
		}
		
		return  this.utilisateurMetier.register(user);
	}
	@GetMapping("/{userId}")
	public Utilisateur getUserByID(@PathVariable("userId") Long idUser) {
		return this.utilisateurMetier.findUserByID(idUser).get();
	}
	@GetMapping("/username/{username}")
	public Utilisateur getUserByEmail(@PathVariable("username") String username) {
		
		return this.utilisateurMetier.findUserByEmail(username).get();
	}
	@PutMapping("/{idUser}/changePhoto")
	public Utilisateur changePhoto(@PathVariable("idUser")Long idUser , @RequestParam("image") MultipartFile file) {
		Utilisateur user = this.utilisateurMetier.findUserByID(idUser).get();
		user.setPhotoProfile("http://localhost:9091/users/upload-dir/"+this.storageFileMetier.store(file));
		return utilisateurMetier.register(user);
	}

}

