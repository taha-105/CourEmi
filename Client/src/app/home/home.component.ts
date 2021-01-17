import { Component, OnInit } from '@angular/core';
import {CoursService} from '../services/cours.service';
import {Cours} from '../model/cours';
import {InscriptionService} from '../services/inscription.service';
import {Inscription} from '../model/inscription';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  courses: Array<Cours>;
  term: string ;
  itemsPerPage: number = 6;
  page: number = 1;

  constructor(private coursService:CoursService) { }

  ngOnInit(): void {



    this.coursService.getAllCourseFromServer()
    this.coursService.coursesSubject.asObservable().subscribe((result:Cours[])=>{
      this.courses=result
    })
  }

  onAffichePlus() {
    this.itemsPerPage=this.courses?.length
  }

  onAfficheMoins() {
    this.itemsPerPage=6
  }
}
