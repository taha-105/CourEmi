package ma.ac.emi.coursemi.metier;

import java.util.List;
import java.util.Optional;

import ma.ac.emi.coursemi.entities.Inscription;

public interface InscriptionMetier {
	public Inscription save(Inscription inscrip);
	public Optional<Inscription> getInscriptionById(Long idInscr);
	public List<Inscription> getInscriptionsByIdCours(Long idCours);
	public Optional<Inscription> getInscriptionByCourEtudiant(Long idCours, Long idEtd);
	public Inscription deleteInscriptionById(Long id);
	public List<Inscription> getInscriptionByiDEtudiant(Long idEtudiant);
	public List<Inscription> getAllInscriptions();
	
}
