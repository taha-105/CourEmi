
import { Injectable } from '@angular/core';
import {Subject} from 'rxjs';
import {Router} from '@angular/router';
import {HttpClient} from '@angular/common/http';
import {UsersService} from './users.service';
import {environment} from '../../environments/environment';
import {User} from '../model/user';
const BACKEND_URL = environment.apiUrl;
@Injectable({
  providedIn: 'root'
})
export class LoginService {

  isAuth : boolean = false;
  private token : string;
  private authStatusListener = new Subject<boolean>();
  private isAuthenticated = false;
  private  memberId : string;
  private tokenTimer: any;
  private memberRole : string;
  private memberRoleSub =new Subject<boolean>();
  private isAdmin = false;
  constructor(private http : HttpClient, private router : Router,private memberService : UsersService) { }
  getToken() : string{
    return localStorage.getItem("token");
  }
  getAuthStatusListener() {
    return this.authStatusListener.asObservable();
  }
  getAuthStatus(){
    return this.isAuthenticated;
  }
  signIn(login : String, password : String) {
    const authData = {"username" : login, "password": password};
    this.http.post<{token : string, expireIn : number, memberId : string,role : string}>(
      BACKEND_URL+'login',authData).subscribe((result)=>{
      const token = result.token;
      localStorage.setItem('token', token);
      if(token){
        const expiresInDuration = result.expireIn;

        this.setAuthTimer(expiresInDuration);
        this.memberId = result.memberId;

        this.memberRole = result.role;
        this.memberRoleSub.next(this.memberRole==="admin")
        // @ts-ignore
        const now = new Date();
        // @ts-ignore
        const expirationDate = new Date(now.getTime()+expiresInDuration*1000);

        this.saveAuthData(token,expirationDate,this.memberId,this.memberRole);

        this.authStatusListener.next(true);
        this.isAuthenticated = true;
        this.isAdmin = this.memberRole === 'admin'? true : false;
        this.router.navigate(['home']);
      }

    },(error)=>{
      window.alert(error.error.error);
    });

  }
  logout(){
    this.token = null;
    this.authStatusListener.next(false);
    this.isAuthenticated = false;
    clearTimeout(this.tokenTimer);
    this.memberId = null;
    this.memberRole = null;
    this.clearAuthData();
    this.router.navigate(['home']);
  }
  private saveAuthData(token: string, expirationDate: Date, memberId: string, memberRole: string) {
    localStorage.setItem("token", token);
    localStorage.setItem("expiration", expirationDate.toISOString());
    localStorage.setItem("memberId", memberId);
    localStorage.setItem("memberRole", memberRole);
  }
  private clearAuthData(){
    localStorage.removeItem("token");
    localStorage.removeItem("expiration");
    localStorage.removeItem("memberId");
    localStorage.removeItem("memberRole");
  }
  private getAuthData() {
    const token = localStorage.getItem("token");
    const expirationDate = localStorage.getItem("expiration");
    const memberId=localStorage.getItem("memberId");
    const memberRole=localStorage.getItem("memberRole");
    if (!token || !expirationDate) {
      return;
    }

    return {
      token: token,
      expirationDate: new Date (expirationDate),
      memberId:memberId,
      memberRole:memberRole
    };
  }


  getMemberStatus(){
    this.memberService.getMemberFromToken(this.getToken())
    this.memberService.userByTokenSubject.asObservable().subscribe((result:User)=>{
      this.memberRoleSub.next(result.role==="Enseignant")
    })
    return this.memberRoleSub.asObservable()
  }
  autoAuthUser() {
    const authInfo = this.getAuthData();

    if (!authInfo) {
      return;
    }
    // @ts-ignore
    const now = new Date();
    const expiresIn = authInfo.expirationDate.getTime() - now.getTime();
    if (expiresIn > 0) {

      this.token = authInfo.token;
      this.isAuthenticated = true;
      this.setAuthTimer(expiresIn / 1000);
      this.authStatusListener.next(true);
    }
    else{
      this.logout()
    }
  }
  private setAuthTimer(duration: number) {
    this.tokenTimer = setTimeout(() => {
      this.logout();
    }, duration * 1000);
  }
  getMemberRole(){
    return localStorage.getItem("memberRole")
  }
  getMemberId(){
    return localStorage.getItem("memberId")
  }
}
