import { Component, OnInit } from '@angular/core';
import {NgForm} from '@angular/forms';
import {UsersService} from '../services/users.service';
import {LoginService} from '../services/login.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css']
})
export class SignUpComponent implements OnInit {
  imageUploaded: boolean;
  filename: string="choisir votre photo";
  private selectedFile: File;
  errorFb:string=null
  succesFb:string=null
  nomSens: string=null;
  prenomSens :string=null
  constructor(private usersService:UsersService) {

  }

  ngOnInit(): void {
  }

  onSignUp(f: NgForm) {
    console.log(f.value);
    if(f.valid){
      this.succesFb=null;
      this.errorFb=null
      this.usersService.signup(f,this.selectedFile).subscribe((result:any)=>{
        this.succesFb="Votre Compte à été crée avec succés."
        f.resetForm()
      },error => {
        this.errorFb=error.error.message
      })
    }

  }

  detectImage(event:any) {
    this.selectedFile = event.target.files[0];
    if (event.target.files && event.target.files.length > 0) {
      this.onUploadFile(event.target.files[0]);
      this.filename = this.selectedFile.name;
    }
  }
  onUploadFile(file: File) {
    this.imageUploaded = true;
  }


  changeNameSens(nom:string,prenom:string) {
    this.nomSens=prenom;
    this.prenomSens=nom
  }
}
