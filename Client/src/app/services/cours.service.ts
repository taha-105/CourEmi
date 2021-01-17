import { Injectable } from '@angular/core';
import {Cours} from '../model/cours';
import {Subject} from 'rxjs';
import {environment} from '../../environments/environment';
import {HttpClient} from '@angular/common/http';
import {AbstractControl} from '@angular/forms';
const BACKEND_URL = environment.apiUrl;
@Injectable({
  providedIn: 'root'
})
export class CoursService {
  courses: Array<Cours> = [];
  coursesSubject = new Subject<Cours[]>();
  coursesEns:Array<Cours> = [];
  coursesEnsSubject = new Subject<Cours[]>();
  constructor(private http:HttpClient) { }

  getAllCourseFromServer() {
    this.http.get(BACKEND_URL+'cours').subscribe((courses : any[])=>{
      this.courses = courses;
      this.emitCoursesSubject();
    });

  }

  emitCoursesSubject() {
    this.coursesSubject.next(this.courses.slice());
  }

  getCoursById(idCours : number){
    return this.http.get(BACKEND_URL+'cours/'+idCours);
  }

  getCoursesByIdEns(idEns:number){
    this.http.get(BACKEND_URL+'cours/enseignants/'+idEns).subscribe((courses : any[])=>{
      this.coursesEns = courses;
      this.emitCoursesEnsSubject();
    });
  }

   emitCoursesEnsSubject() {
    this.coursesEnsSubject.next(this.coursesEns.slice())
  }
  getCoursByIdCat(idCat:number){
    return this.http.get(BACKEND_URL+"cours/categories/"+idCat)
  }

  addCourse(cours: any, idEns: number, idCat: number, image: File) {
    let coursBody = new FormData();
     coursBody.append('coursData', JSON.stringify(cours));
    coursBody.append('image', image);
    return this.http.post(BACKEND_URL+"cours/"+idCat+"/"+idEns,coursBody);
  }

  deleteCours(idCours: number, idEns: number) {
    this.http.delete(BACKEND_URL+"cours/"+idCours).subscribe((response:any)=>{
      this.getCoursesByIdEns(idEns)
      window.alert("Ce cours a été supprimé avec succés")
    })
  }

  editCourse(cours: any, idCours: number,idCat:number) {
    let coursBody = new FormData();
    coursBody.append('coursData', JSON.stringify(cours));
    return this.http.put(BACKEND_URL+"cours/"+idCours+"/categories/"+idCat,coursBody);
  }
}
