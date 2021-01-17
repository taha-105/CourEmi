import { Injectable } from '@angular/core';
import {environment} from '../../environments/environment';
import {HttpClient} from '@angular/common/http';
import {Inscription} from '../model/inscription';
import {Subject} from 'rxjs';
import {Cours} from '../model/cours';
const BACKEND_URL = environment.apiUrl;
@Injectable({
  providedIn: 'root'
})
export class InscriptionService {
  inscriptions : Array<Inscription> =[]
  inscriptionsSubject = new Subject<Inscription[]>();
  allInscriptions: Array<Inscription> =[];
  allInscriptionsSubject = new Subject<Inscription[]>();
  constructor(private http:HttpClient) { }

  getInscriptionsByCours(idCours:number){
    return this.http.get(BACKEND_URL+"inscriptions/cours/"+idCours)
  }

  insciptionToCourse(memberId: number, coursId: number) {
    return this.http.post(BACKEND_URL+"inscriptions/"+coursId+"/"+memberId,null);
  }

  desinscriptionCours(idInscription :number) {
    return this.http.delete(BACKEND_URL+"inscriptions/"+idInscription)
  }

  getInscriptionsByEtudiant(idEtudiant: number) {
    this.http.get(BACKEND_URL+"inscriptions/etudiants/"+idEtudiant).subscribe((inscriptions:any[])=>{
      this.inscriptions=inscriptions;
      this.emitInscriptionsSubject();
    })
  }

  emitInscriptionsSubject() {
    this.inscriptionsSubject.next(this.inscriptions.slice())
  }

}
