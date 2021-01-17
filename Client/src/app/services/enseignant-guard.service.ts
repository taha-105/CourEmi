import { Injectable } from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from '@angular/router';
import {LoginService} from './login.service';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EnseignantGuardService implements  CanActivate{
  canActivate(route: ActivatedRouteSnapshot,
              state: RouterStateSnapshot):
    Observable<boolean> | Promise<boolean>  | boolean  {
    if(this.loginService.getAuthStatus() && this.loginService.getMemberRole()==='Enseignant'){return  this.loginService.getMemberRole()==='Enseignant'; }
    else {this.router.navigate(['/home']);}
  }
  constructor(private loginService : LoginService,private router : Router) { }
}
