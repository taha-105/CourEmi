import {Cours} from './cours';
import {User} from './user';

export class Inscription {
  idInscription: number
  idCours: number
  cours: Cours
  idEtudiant: number
  etudiant: User
  dateInscri :Date

  constructor(idInscription: number, idCours: number, cours: Cours, idEtudiant: number, etudiant: User, dateInscri: Date) {
    this.idInscription = idInscription;
    this.idCours = idCours;
    this.cours = cours;
    this.idEtudiant = idEtudiant;
    this.etudiant = etudiant;
    this.dateInscri = dateInscri;
  }
}
