package ma.ac.emi.coursemi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ma.ac.emi.coursemi.entities.Categorie;
import ma.ac.emi.coursemi.metier.CategorieMetier;
@RestController
public class CategorierController {
	@Autowired
	private CategorieMetier categorieMetier;
	
	@PostMapping("")
	public Categorie saveCategorie(@RequestBody Categorie categorie) {
		return this.categorieMetier.save(categorie);
	}
	@GetMapping("")
	public List<Categorie> getAllCategorie() {
		return this.categorieMetier.getAllCategories();
	}
	@GetMapping("/{idCat}")
	
	public Categorie getCatByID(@PathVariable("idCat") Long idCat) {
		return this.categorieMetier.getCategorieById(idCat);
	}
	@GetMapping("/hello")
	public String hello() {
		return "hello";
	}
}
