import { Component, OnInit } from '@angular/core';
import {LoginService} from '../services/login.service';
import {CoursService} from '../services/cours.service';
import {Cours} from '../model/cours';
import * as CanvasJS from '../../assets/js/canvasjs.min.js';
import {FormControl, FormGroup} from '@angular/forms';
import {CategorieService} from '../services/categorie.service';
import {Categorie} from '../model/categorie';
@Component({
  selector: 'app-admin-cours-ens',
  templateUrl: './admin-cours-ens.component.html',
  styleUrls: ['./admin-cours-ens.component.css']
})
export class AdminCoursEnsComponent implements OnInit {
  coursesEns: Array<Cours>;
  courseForm: FormGroup;
  imageUploaded: boolean =false;
  imagename: string ;
  categories: Array<Categorie>;
  selectedImage: File;
  succesFb: string ;
  errorFb: string;
   type: string;
   coursFetched: Cours;

  constructor(private loginService:LoginService,private coursService:CoursService,private categorieService:CategorieService) { }

  ngOnInit(): void {
    this.courseForm = new FormGroup({
      'description': new FormControl(null),
      'categorie': new FormControl(null),
      'titre' : new FormControl(null),
      'dateDebut' : new FormControl(null),
      'dateFin' : new FormControl(null)
    })
    if(this.loginService.getMemberId()){
      this.coursService.getCoursesByIdEns(parseInt(this.loginService.getMemberId()))
      this.coursService.coursesEnsSubject.asObservable().subscribe((coursesEns:Cours[])=>{
        this.coursesEns=coursesEns
        this.initChart(this.coursesEns);
      })
      this.uploadCategories()
    }
  }

 initChart(coursesEns: Array<Cours>) {
    let dataPoints : Array<any>  = new Array<any>()
   coursesEns.map(c=>c).forEach(c=>{
     dataPoints.push({y:c?.nbrInscri,label:c?.titre})
   })
   let chart = new CanvasJS.Chart("chartContainer", {
     animationEnabled: true,
     exportEnabled: true,
     title: {
       text: "Statistiques des inscriptions à Mes cours"
     },
     data: [{
       type: "column",
       dataPoints: dataPoints
     }]
   });

   chart.render();
  }

  onSaveCourse() {

    const idEns = parseInt(this.loginService.getMemberId());
    const idCat =parseInt(this.courseForm.get('categorie').value)

    delete this.courseForm.value['categorie']
    this.errorFb=null
    this.succesFb=null
    if(this.type==='create'){
      this.coursService.addCourse(this.courseForm.value,idEns,idCat,this.selectedImage).subscribe((response:any)=>{
        this.succesFb = "Cours ajouté avec succés"
        this.coursService.getCoursesByIdEns(idEns)
      },error => {
        this.errorFb = "Une erreur est survenue , ressayez !"
      })
    }
    else{
      this.coursService.editCourse(this.courseForm.value,this.coursFetched?.idCours,idCat).subscribe((response:any)=>{
        this.succesFb = "Cours modifé avec succés"
        this.coursService.getCoursesByIdEns(idEns)
      },error => {
        this.errorFb = "Une erreur est survenue , ressayez !"
      })
    }


  }

  detectImage(event: any) {
    this.selectedImage = event.target.files[0] ;
    if (event.target.files && event.target.files.length > 0) {
      this.onUploadImage(event.target.files[0]);
      this.imagename = this.selectedImage.name;
    }
  }
  onUploadImage(file: File) {
    this.imageUploaded = true;

  }

  private uploadCategories() {
    this.categorieService.getAllCategorie();
    this.categorieService.categoriesSubject.asObservable().subscribe((categories:Categorie[])=>{
      this.categories=categories
    })
  }

  onDeleteCours(idCours: number) {
    if(window.confirm("voulez-vous vraiment supprimer ce cours")){
      const idEns = parseInt(this.loginService.getMemberId());
      this.coursService.deleteCours(idCours,idEns)
    }
  }

  onClickCreate() {
    this.errorFb=null
    this.succesFb=null
    this.courseForm.reset()
   this.type="create"
  }

  onEditCours(cours: Cours) {
    this.errorFb=null
    this.succesFb=null
    this.type="edit"
    this.coursFetched=cours
  }

  getAction(type: string) {
    if(type==="create") return "Enregistrer"
    else{
      return "Modifier"
    }
  }

}
