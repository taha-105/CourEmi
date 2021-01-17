import {Categorie} from './categorie';
import {User} from './user';

export class Cours {
  idCours: number
  titre: string
  description: string
  idCategorie: number
  categorie:Categorie
  dateDebut: Date
  dateFin: Date
  idUser: number
  urlImage: string
  enseignant: User
  nbrInscri : number

  constructor(idCours: number, titre: string, description: string, idCategorie: number, categorie: Categorie, dateDebut: Date, dateFin: Date, idUser: number, urlImage: string, enseignant: User,  nbrInscri : number) {
    this.idCours = idCours;
    this.titre = titre;
    this.description = description;
    this.idCategorie = idCategorie;
    this.categorie = categorie;
    this.dateDebut = dateDebut;
    this.dateFin = dateFin;
    this.idUser = idUser;
    this.urlImage = urlImage;
    this.enseignant = enseignant;
    this.nbrInscri=nbrInscri
  }
}
