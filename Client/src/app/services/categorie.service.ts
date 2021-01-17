import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../environments/environment';

import {Subject} from 'rxjs';
import {Categorie} from '../model/categorie';
const BACKEND_URL = environment.apiUrl;
@Injectable({
  providedIn: 'root'
})
export class CategorieService {
  categories: Array<Categorie> = [];
  categoriesSubject = new Subject<Categorie[]>();
  constructor(private http:HttpClient) { }

  getAllCategorie(){
    this.http.get(BACKEND_URL+"categories").subscribe((response:any[])=>{
      this.categories=response
      this.emitCategories()
    })
  }

  private emitCategories() {
    this.categoriesSubject.next(this.categories.slice())
  }

  getCategorieById(categorieId: number) {
    return this.http.get(BACKEND_URL+"categories/"+categorieId)
  }
}
