package ma.ac.emi.coursemi.controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.checkerframework.dataflow.qual.Deterministic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;


import ma.ac.emi.coursemi.entities.Categorie;
import ma.ac.emi.coursemi.entities.Cours;
import ma.ac.emi.coursemi.entities.Enseignant;
import ma.ac.emi.coursemi.entities.Utilisateur;
import ma.ac.emi.coursemi.metier.CoursMetier;
import ma.ac.emi.coursemi.metier.StorageFileMetier;
import ma.ac.emi.coursemi.repositories.CoursRepositorie;
import ma.ac.emi.coursemi.request.CourRequest;

@RestController
public class CoursControllers {
	@Autowired
	private CoursMetier coursMetier;
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private StorageFileMetier storageMetier;

	@PostMapping(value="/{categorieId}/{userId}", produces = "application/json",  consumes = { "multipart/form-data"})
	public Cours saveCours(@PathVariable("categorieId") Long idCat,@PathVariable("userId") Long userId,@RequestParam("coursData") String coursData,@RequestParam(value = "image",required = false) MultipartFile file) throws IOException {
		System.out.println(coursData);
		CourRequest courReq= new ObjectMapper().readValue(coursData, CourRequest.class);
		Cours cours = new Cours(null, courReq.getTitre(), courReq.getDescription(), idCat, null, courReq.getDateDebut(), courReq.getDateFin(), userId,null, null,null);
		
		cours.setUrlImage("http://localhost:9091/cours/upload-dir/"+this.storageMetier.store(file));

		return this.coursMetier.save(cours);
	}
	
	@GetMapping("/{coursId}")
	public Cours getCoursByIdCours(@PathVariable("coursId") Long idCours) {
		Cours coursFind= this.coursMetier.getCourById(idCours);
		Enseignant user=restTemplate.getForObject("http://localhost:9091/users/"+coursFind.getIdUser(), Enseignant.class);
		Categorie categorie=restTemplate.getForObject("http://localhost:9091/categories/"+coursFind.getIdCategorie(), Categorie.class);
		Integer len = restTemplate.getForObject("http://localhost:9091/inscriptions/cours/"+idCours+"/len", Integer.class);
		coursFind.setEnseignant(user);
		coursFind.setCategorie(categorie);
		coursFind.setNbrInscri(len);
		return coursFind;
	}
	
	@GetMapping("/users/{userId}")
	public List<Cours> getCoursByIdUser(@PathVariable("userId") Long userId) {
		List<Cours> coursByUser = this.coursMetier.getCoursByEns(userId);
		coursByUser.forEach(c->{
			Enseignant user=restTemplate.getForObject("http://localhost:9091/users/"+userId, Enseignant.class);
			Categorie categorie=restTemplate.getForObject("http://localhost:9091/categories/"+c.getIdCategorie(), Categorie.class);
			Integer len = restTemplate.getForObject("http://localhost:9091/inscriptions/cours/"+c.getIdCours()+"/len", Integer.class);
			c.setEnseignant(user);
			c.setCategorie(categorie);
			c.setNbrInscri(len);
		});
		return coursByUser;
	}
	@GetMapping("")
	public  List<Cours> getAllCourses(){
		
		List<Cours> cours = this.coursMetier.getAllCourses();
		cours.forEach(c->{
			Enseignant user=restTemplate.getForObject("http://localhost:9091/users/"+c.getIdUser(), Enseignant.class);
			Categorie categorie=restTemplate.getForObject("http://localhost:9091/categories/"+c.getIdCategorie(), Categorie.class);
			Integer len = restTemplate.getForObject("http://localhost:9091/inscriptions/cours/"+c.getIdCours()+"/len", Integer.class);
			c.setEnseignant(user);
			c.setCategorie(categorie);
			c.setNbrInscri(len);
		});
		return cours;
		
	}
	@GetMapping("/enseignants/{idEns}")
	public  List<Cours> getAllCoursesByIdEns(@PathVariable("idEns") Long idEns){
		
		List<Cours> cours = this.coursMetier.getAllCoursesByIdEns(idEns);
		cours.forEach(c->{
			Enseignant user=restTemplate.getForObject("http://localhost:9091/users/"+c.getIdUser(), Enseignant.class);
			Categorie categorie=restTemplate.getForObject("http://localhost:9091/categories/"+c.getIdCategorie(), Categorie.class);
			Integer len = restTemplate.getForObject("http://localhost:9091/inscriptions/cours/"+c.getIdCours()+"/len", Integer.class);
			c.setEnseignant(user);
			c.setCategorie(categorie);
			c.setNbrInscri(len);
		});
		return cours;
		
	}
	@GetMapping("/categories/{idCat}")
	public  List<Cours> getCategoriesByCategorie(@PathVariable("idCat") Long idCat){
		
		List<Cours> cours = this.coursMetier.getAllCoursesByIdcategorie(idCat);
		cours.forEach(c->{
			Enseignant user=restTemplate.getForObject("http://localhost:9091/users/"+c.getIdUser(), Enseignant.class);
			Categorie categorie=restTemplate.getForObject("http://localhost:9091/categories/"+c.getIdCategorie(), Categorie.class);
			Integer len = restTemplate.getForObject("http://localhost:9091/inscriptions/cours/"+c.getIdCours()+"/len", Integer.class);
			c.setEnseignant(user);
			c.setCategorie(categorie);
			c.setNbrInscri(len);
		});
		return cours;
		
	}
	@DeleteMapping("/{idCours}")
	public Cours deletCours(@PathVariable("idCours") Long idCours) {
		Cours cours=this.coursMetier.delete(idCours);
		return cours;
	}
	@PutMapping("/{idCours}/categories/{idCat}")
	public Cours deletCours(@PathVariable("idCours") Long idCours,@PathVariable("idCat") Long idCat,@RequestParam("coursData") String coursData) throws JsonMappingException, JsonProcessingException{
		CourRequest courReq= new ObjectMapper().readValue(coursData, CourRequest.class);
		Cours cours = this.coursMetier.getCourById(idCours);
		cours.setIdCategorie(idCat);
		cours.setDateDebut(courReq.getDateDebut());
		cours.setDateFin(courReq.getDateFin());
		cours.setDescription(courReq.getDescription());
		cours.setTitre(courReq.getTitre());
		return this.coursMetier.save(cours);
	}
}



