package ma.ac.emi.coursemi.entities;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter@Setter@NoArgsConstructor@AllArgsConstructor
public class Inscription implements Serializable{
	@Id@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idInscription;
	private Long idCours;
	@Transient
	private Cours cours;
	private Long idEtudiant;
	@Transient
	private Etudiant etudiant;
	@Column(nullable=false)
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone="GMT")
	private LocalDate dateInscri;
	
}
