package ma.ac.emi.coursemi.metier;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.ac.emi.coursemi.entities.Categorie;
import ma.ac.emi.coursemi.repositories.CategorieRepositorie;
@Service
@Transactional
public class CategorieMetierImpl implements CategorieMetier{
	@Autowired 
	private CategorieRepositorie categorieReposotorie;

	@Override
	public Categorie save(Categorie categorie) {
		
		return this.categorieReposotorie.save(categorie);
	}

	@Override
	public Categorie getCategorieById(Long id) {
		
		return this.categorieReposotorie.findById(id).get();
	}

	@Override
	public List<Categorie> getAllCategories() {
		// TODO Auto-generated method stub
		return this.categorieReposotorie.findAll();
	}

}
