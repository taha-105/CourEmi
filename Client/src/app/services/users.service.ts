import { Injectable } from '@angular/core';
import {NgForm} from '@angular/forms';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../environments/environment';
import {User} from '../model/user';
import {Subject} from 'rxjs';
const BACKEND_URL = environment.apiUrl;
@Injectable({
  providedIn: 'root'
})
export class UsersService {
  userByToken: User;
  userByTokenSubject = new Subject<User>();
  constructor(private http:HttpClient) { }

  signup(f: NgForm,image:File) {
    let memberBody = new FormData();
    memberBody.append('user', JSON.stringify(f.value));
    memberBody.append('image', image);
    return this.http.post(BACKEND_URL+"users/sign-up",memberBody);
  }

  getMemberFromToken(token: string) {
    this.http.get(BACKEND_URL + "token/" + token).subscribe((result:any)=>{

      this.userByToken=result.result
      this.emitUserByToken()
    })
  }
  emitUserByToken() {
    this.userByTokenSubject.next(this.userByToken)
  }

  changePhoto(image: File,idUser:number) {
    let memberBody = new FormData();
    memberBody.append('image', image);

    if (memberBody != null) {
      return this.http.put(BACKEND_URL + "users/"+idUser+"/changePhoto", memberBody)
    }
  }
}
