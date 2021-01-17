package ma.ac.emi.coursemi.metier;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ma.ac.emi.coursemi.entities.Inscription;
import ma.ac.emi.coursemi.repositories.InscriptionRepositorie;
@Service
@Transactional
public class InscriptionMetierImpl implements InscriptionMetier{
	@Autowired
	private InscriptionRepositorie inscripRepo;
	
	@Override
	public Inscription save(Inscription inscrip) {
		// TODO Auto-generated method stub
		return this.inscripRepo.save(inscrip);
	}

	@Override
	public Optional<Inscription> getInscriptionById(Long idInscr) {
		// TODO Auto-generated method stub
		return this.inscripRepo.findById(idInscr);
	}

	@Override
	public List<Inscription> getInscriptionsByIdCours(Long idCours) {
		// TODO Auto-generated method stub
		return this.inscripRepo.findByIdCours(idCours);
	}

	@Override
	public Optional<Inscription> getInscriptionByCourEtudiant(Long idCours, Long idEtd) {
		// TODO Auto-generated method stub
		return this.inscripRepo.findByIdCoursAndIdEtudiant(idCours,idEtd);
	}

	@Override
	public Inscription deleteInscriptionById(Long id) {
		Inscription inscription = this.inscripRepo.findById(id).get();
		 this.inscripRepo.deleteById(id);
		 return inscription;
	}

	@Override
	public List<Inscription> getInscriptionByiDEtudiant(Long idEtudiant) {
		// TODO Auto-generated method stub
		return this.inscripRepo.findByIdEtudiant(idEtudiant);
	}

	@Override
	public List<Inscription> getAllInscriptions() {
		// TODO Auto-generated method stub
		return this.inscripRepo.findAll();
	}

}
