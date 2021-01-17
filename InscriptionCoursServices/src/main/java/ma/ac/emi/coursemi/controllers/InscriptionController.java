package ma.ac.emi.coursemi.controllers;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import ma.ac.emi.coursemi.entities.Cours;
import ma.ac.emi.coursemi.entities.Etudiant;
import ma.ac.emi.coursemi.entities.Inscription;
import ma.ac.emi.coursemi.metier.InscriptionMetier;

@RestController
public class InscriptionController {
	@Autowired
	private InscriptionMetier inscriptionMetier;
	@Autowired
	private RestTemplate restTemplate;
	
	
	@PostMapping("/{idCours}/{idEtudiant}")
	public Inscription saveInscription(@PathVariable("idCours")Long idCours,@PathVariable("idEtudiant")Long idEtd) throws Exception {
		Optional<Inscription> inscri = this.inscriptionMetier.getInscriptionByCourEtudiant(idCours,idEtd);
		if(inscri.isPresent()) throw new Exception("Vous êtes déja inscrit à ce cours !");
		Inscription newInscription = new Inscription(null, idCours, null, idEtd, null, LocalDate.now());
		
		return this.inscriptionMetier.save(newInscription);
	}
	@GetMapping("")
	public List<Inscription> getAllInscriptions(){
		List<Inscription> inscriptions = this.inscriptionMetier.getAllInscriptions();
		inscriptions.forEach(iC->{
			Etudiant etudiant = this.restTemplate.getForObject("http://localhost:9091/users/"+iC.getIdEtudiant(), Etudiant.class);
			 Cours cours =this.restTemplate.getForObject("http://localhost:9091/cours/"+iC.getIdCours(), Cours.class);
			 iC.setEtudiant(etudiant);
			 iC.setCours(cours);
		});
		return inscriptions;
	}
	@GetMapping("/{idInscr}")
	public Inscription getInscriptionById(@PathVariable("idInscr")Long idInscription) {
		 Inscription inscri = this.inscriptionMetier.getInscriptionById(idInscription).get();
		 Etudiant etudiant = this.restTemplate.getForObject("http://localhost:9091/users/"+inscri.getIdEtudiant(), Etudiant.class);
		 Cours cours =this.restTemplate.getForObject("http://localhost:9091/cours/"+inscri.getIdCours(), Cours.class);
		 inscri.setCours(cours);
		 inscri.setEtudiant(etudiant);
		 return inscri;
		
	}
	@GetMapping("/cours/{idCours}")
	public List<Inscription> getInscriptionsByIdCours(@PathVariable("idCours")Long idCours) {
		List<Inscription> inscriptionsByCours = this.inscriptionMetier.getInscriptionsByIdCours(idCours); 
	
		inscriptionsByCours.forEach(iC->{
			Etudiant etudiant = this.restTemplate.getForObject("http://localhost:9091/users/"+iC.getIdEtudiant(), Etudiant.class);
			 Cours cours =this.restTemplate.getForObject("http://localhost:9091/cours/"+idCours, Cours.class);
			 iC.setEtudiant(etudiant);
			 iC.setCours(cours);
		});
		return inscriptionsByCours;
	}
	@GetMapping("/cours/{idCours}/len")
	public Integer getLenInscriptionsByIdCours(@PathVariable("idCours")Long idCours) {
		List<Inscription> inscriptionsByCours = this.inscriptionMetier.getInscriptionsByIdCours(idCours); 
		return inscriptionsByCours.size();
		
	}
	@DeleteMapping("/{idInscription}")
	public Inscription deleteInscriptionByID(@PathVariable("idInscription")Long id) throws Exception {
		
		Optional<Inscription> inscription= this.inscriptionMetier.getInscriptionById(id) ;
		if(!inscription.isPresent()) {
			throw new Exception("Vous n'êtes pas inscrit à ce cours");
		}
		else {
			return this.inscriptionMetier.deleteInscriptionById(id);
		}
	}
	@GetMapping("/etudiants/{idEtudiant}")
	public List<Inscription> getInscriptionsByetudiant(@PathVariable("idEtudiant")Long idEtudiant){
		List<Inscription> inscriptions = this.inscriptionMetier.getInscriptionByiDEtudiant(idEtudiant);
		inscriptions.forEach(iC->{
			Etudiant etudiant = this.restTemplate.getForObject("http://localhost:9091/users/"+iC.getIdEtudiant(), Etudiant.class);
			 Cours cours =this.restTemplate.getForObject("http://localhost:9091/cours/"+iC.getIdCours(), Cours.class);
			 iC.setEtudiant(etudiant);
			 iC.setCours(cours);
		});
		return inscriptions;
		
	}
	
}
