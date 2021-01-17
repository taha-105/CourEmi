import { Component, OnInit } from '@angular/core';
import {CoursService} from '../services/cours.service';
import {Cours} from '../model/cours';
import {ActivatedRoute} from '@angular/router';
import {LoginService} from '../services/login.service';
import {InscriptionService} from '../services/inscription.service';
import {Inscription} from '../model/inscription';

@Component({
  selector: 'app-cours-details',
  templateUrl: './cours-details.component.html',
  styleUrls: ['./cours-details.component.css']
})
export class CoursDetailsComponent implements OnInit {
  coursId : number
   cours: Cours
   memberRole: string;
   memberId: string;
   inscriptions: Array<Inscription>
  succesFb: string=null;
  errorFb: string =null;
  coursAsso: Array<Cours>;

  constructor(private coursService: CoursService, private route: ActivatedRoute,private loginService:LoginService,private inscriptionService:InscriptionService) {
  }

  ngOnInit(): void {

    this.memberRole=this.loginService.getMemberRole()
    this.memberId=this.loginService.getMemberId()
    this.route.paramMap.subscribe((param)=>{
      if(param.has('coursId')){
        this.coursId =parseInt(param.get('coursId')) ;
        this.uploadInscriptionByCours(this.coursId);
        this.coursService.getCoursById(this.coursId).subscribe((cours:any)=>{
          this.cours=cours
          this.uploadCoursAssocie(this.cours?.idCategorie)

        })
      }

     })
  }

  isNotInscrit(memberId: string) {
    let c : boolean = false
    this.inscriptions?.map(t=>t).forEach(i=>{
      if(i?.etudiant.idUser==parseInt(memberId)){
        c=true
      }
    })
    return c
  }
  getInscriptionId(memberId:string){
    let id : number= null;
    this.inscriptions?.map(t=>t).forEach(i=>{
      if(i?.etudiant.idUser==parseInt(memberId)){
        id=i.idInscription
      }
    })
    return id
  }
   uploadInscriptionByCours(coursId: number) {
    this.inscriptionService.getInscriptionsByCours(coursId).subscribe((inscription:any[])=>{
      this.inscriptions=inscription
    })
  }

  inscriptionToCourse(memberId: string, coursId: number) {
    this.errorFb=null
    this.succesFb=null
      this.inscriptionService.insciptionToCourse(parseInt(memberId),coursId).subscribe((response:any)=>{
        this.succesFb="Vous êtes inscrit au cours '"+this.cours?.titre+"'"
      },error => {
        console.log(error);
        this.errorFb=error.error.message
      })
  }


  desinscriptionCours(inscriptionId: number) {
    this.errorFb=null
    this.succesFb=null
    this.inscriptionService.desinscriptionCours(inscriptionId).subscribe((response:any)=>{
      this.succesFb="vous êtes désinscrits de ce cours '"
    },error => {
      console.log(error);
      this.errorFb=error.error.message
    })
  }

  private uploadCoursAssocie(idCategorie: number) {
    this.coursService.getCoursByIdCat(idCategorie).subscribe((response:Cours[])=>{
      this.coursAsso=response
    })
  }
}
