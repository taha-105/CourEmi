export class User {
  idUser: number
  nom: string
  prenom: string
  email: string
  password: string
  photoProfile: string
  role: string
  grade : string

  constructor(idUser: number, nom: string, prenom: string, email: string, password: string, photoProfile: string, role: string, grade: string) {
    this.idUser = idUser;
    this.nom = nom;
    this.prenom = prenom;
    this.email = email;
    this.password = password;
    this.photoProfile = photoProfile;
    this.role = role;
    this.grade = grade;
  }
}
