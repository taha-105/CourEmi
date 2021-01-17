import { Component, OnInit } from '@angular/core';
import {InscriptionService} from '../services/inscription.service';
import {LoginService} from '../services/login.service';
import {Inscription} from '../model/inscription';

@Component({
  selector: 'app-mes-inscriptions',
  templateUrl: './mes-inscriptions.component.html',
  styleUrls: ['./mes-inscriptions.component.css']
})
export class MesInscriptionsComponent implements OnInit {
   idEtudiant : number
   myInscriptions : Array<Inscription>
  succesFb: string=null;
  errorFb: string=null;
  constructor(private inscriptionService: InscriptionService,private loginService:LoginService) { }

  ngOnInit(): void {
    this.uploadInscriptions()
  }

  uploadInscriptions(){
    this.idEtudiant=parseInt(this.loginService.getMemberId());
    this.inscriptionService.getInscriptionsByEtudiant(this.idEtudiant);
    this.inscriptionService.inscriptionsSubject.asObservable().subscribe((result:Inscription[])=>{
      this.myInscriptions=result;
    })

  }
  desinscrire(idInscription: number) {
    this.errorFb=null
    this.succesFb=null
    this.inscriptionService.desinscriptionCours(idInscription).subscribe((response:any)=>{
      this.succesFb="vous êtes désinscrits de ce cours '"
      this.inscriptionService.getInscriptionsByEtudiant(this.idEtudiant)
    },error => {
      console.log(error);
      this.errorFb=error.error.message
    })
  }

}
