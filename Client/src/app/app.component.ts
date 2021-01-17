import {Component, OnInit} from '@angular/core';
import {Subscription} from 'rxjs';
import {LoginService} from './services/login.service';
import {NavigationEnd, Router} from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

export class AppComponent  implements  OnInit{
  memberIsAuthenticated : boolean=false;
  private authListenerSub:Subscription;
  url:string;
  constructor(private loginService:LoginService,private router: Router) {

  }
  ngOnInit(): void {

    this.url=this.router.url
    this.loginService.autoAuthUser();
    this.memberIsAuthenticated =this.loginService.getAuthStatus();
    this.authListenerSub=this.loginService.getAuthStatusListener().subscribe((isAuthenticated)=>{
      this.memberIsAuthenticated=isAuthenticated;
    })
    this.router.events.subscribe(e => {
      if (!(e instanceof NavigationEnd)) {
        return;
      }
      window.scroll({
        top:0,
        left:0,
        behavior: 'smooth'
      });

    } )
  }


}
