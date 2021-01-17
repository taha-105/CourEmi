export class Categorie {
  idCategorie: number
  titre: string
  description:string

  constructor(idCategorie: number, titre: string, description: string) {
    this.idCategorie = idCategorie;
    this.titre = titre;
    this.description = description;
  }
}
