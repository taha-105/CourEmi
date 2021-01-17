package ma.ac.emi.coursemi.metier;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.ac.emi.coursemi.entities.Cours;
import ma.ac.emi.coursemi.repositories.CoursRepositorie;
@Service
@Transactional
public class CoursMetierImpl implements CoursMetier{
	@Autowired
	private CoursRepositorie courRepositorie;
	@Override
	public Cours save(Cours cours) {
		// TODO Auto-generated method stub
		return this.courRepositorie.save(cours);
	}
	@Override
	public Cours getCourById(Long idCours) {
		// TODO Auto-generated method stub
		return this.courRepositorie.findById(idCours).get();
	}
	@Override
	public List<Cours> getCoursByEns(Long userId) {
		// TODO Auto-generated method stub
		return this.courRepositorie.findByIdUser(userId);
	}
	@Override
	public List<Cours> getAllCourses() {
		// TODO Auto-generated method stub
		return this.courRepositorie.findAll();
	}
	@Override
	public List<Cours> getAllCoursesByIdEns(Long idEns) {
		// TODO Auto-generated method stub
		return this.courRepositorie.findByIdUser(idEns);
	}
	@Override
	public List<Cours> getAllCoursesByIdcategorie(Long idCat) {
		// TODO Auto-generated method stub
		return this.courRepositorie.findByIdCategorie(idCat);
	}
	@Override
	public Cours delete(Long idCours) {
		Cours cours = this.courRepositorie.findById(idCours).get();
		this.courRepositorie.deleteById(idCours);
		return cours;
	}
	
	
}
