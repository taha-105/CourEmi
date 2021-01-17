import { Component, OnInit } from '@angular/core';
import {CoursService} from '../services/cours.service';
import {ActivatedRoute} from '@angular/router';
import {Cours} from '../model/cours';
import {CategorieService} from '../services/categorie.service';
import {Categorie} from '../model/categorie';

@Component({
  selector: 'app-cours-categorie',
  templateUrl: './cours-categorie.component.html',
  styleUrls: ['./cours-categorie.component.css']
})
export class CoursCategorieComponent implements OnInit {
   categorieId: number;
   cours: Array<Cours>;
   categorie: Categorie;
  term: string;
  itemsPerPage: number = 6;
  page: number = 1;

  constructor(private coursService:CoursService,private route:ActivatedRoute,private categorieService:CategorieService) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe((param)=>{
      if(param.has('categorieId')){
        this.categorieId =parseInt(param.get('categorieId')) ;
        this.categorieService.getCategorieById(this.categorieId).subscribe((response:any)=>{
          this.categorie=response
        })
        this.coursService.getCoursByIdCat(this.categorieId).subscribe((cours:any[])=>{
          this.cours=cours
          console.log("hello");
        })
      }

    })
  }
  onAffichePlus() {
    this.itemsPerPage=this.cours?.length
  }

  onAfficheMoins() {
    this.itemsPerPage=6
  }

}
