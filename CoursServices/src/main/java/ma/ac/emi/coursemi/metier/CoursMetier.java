package ma.ac.emi.coursemi.metier;

import java.util.List;

import ma.ac.emi.coursemi.entities.Cours;

public interface CoursMetier {
	public Cours save(Cours cours);

	public Cours getCourById(Long idCours);

	public List<Cours> getCoursByEns(Long userId);

	public List<Cours> getAllCourses();

	public List<Cours> getAllCoursesByIdEns(Long idEns);

	public List<Cours> getAllCoursesByIdcategorie(Long idCat);

	public Cours delete(Long idCours);


}
