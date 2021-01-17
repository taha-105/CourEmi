import { Injectable } from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from '@angular/router';
import {LoginService} from './login.service';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoginGuardService implements  CanActivate{
  canActivate(route: ActivatedRouteSnapshot,
              state: RouterStateSnapshot):
    Observable<boolean> | Promise<boolean>  | boolean  {
    if(this.loginService.getAuthStatus()){return  this.loginService.getAuthStatus(); }
    else {this.router.navigate(['/sign-in']);}
  }
  constructor(private loginService : LoginService,private router : Router) { }
}
