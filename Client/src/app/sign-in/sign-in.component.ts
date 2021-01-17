import { Component, OnInit } from '@angular/core';
import {NgForm} from '@angular/forms';
import {LoginService} from '../services/login.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-sign-in',
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.css']
})
export class SignInComponent implements OnInit {

  constructor(private loginService:LoginService,private router:Router) { }

  ngOnInit(): void {
    if(this.loginService.getMemberId()){
      this.loginService.logout()
    }
  }

  onLogin(f: NgForm) {
    if(f.valid){
      const username= f.value['username']
      const password =f.value['password']
      this.loginService.signIn(username,password)
    }


  }
}
