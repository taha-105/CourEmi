package ma.ac.emi.coursemi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import ma.ac.emi.coursemi.entities.Inscription;
import java.lang.Long;
import java.util.List;
import java.util.Optional;

public interface InscriptionRepositorie extends JpaRepository<Inscription, Long>{
	List<Inscription> findByIdCours(Long idcours);
	Optional<Inscription> findByIdCoursAndIdEtudiant(Long idCours,Long idEtd);
	List<Inscription> findByIdEtudiant(Long idEtudiant);
	
}
