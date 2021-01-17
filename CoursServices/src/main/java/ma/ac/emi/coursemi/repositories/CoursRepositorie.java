package ma.ac.emi.coursemi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ma.ac.emi.coursemi.entities.Cours;

public interface CoursRepositorie extends JpaRepository<Cours, Long>{
	List<Cours> findByIdUser(Long idUser);
	List<Cours> findByIdCategorie(Long idCategorie);
	
	
}
