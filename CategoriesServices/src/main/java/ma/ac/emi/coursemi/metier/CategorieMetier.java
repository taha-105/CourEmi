package ma.ac.emi.coursemi.metier;

import java.util.List;

import ma.ac.emi.coursemi.entities.Categorie;

public interface CategorieMetier {
	public Categorie save(Categorie categorie);
	public Categorie getCategorieById(Long id);
	public List<Categorie> getAllCategories();
}
