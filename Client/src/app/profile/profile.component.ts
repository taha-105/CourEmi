import { Component, OnInit } from '@angular/core';
import {NgForm} from '@angular/forms';
import {UsersService} from '../services/users.service';
import {User} from '../model/user';
import {LoginService} from '../services/login.service';


@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  user: User ;
  fileIsUploading: any;
  sele
  selectedFile: File;
   filename: string;
  constructor(private usersService:UsersService,private loginService:LoginService) { }

  ngOnInit(): void {

    this.usersService.getMemberFromToken(this.loginService.getToken());
    this.usersService.userByTokenSubject.asObservable().subscribe((result:User)=>{
      this.user=result
    })
  }


  detectFiles(event: any) {
    this.selectedFile = event.target.files[0];
    if (event.target.files && event.target.files.length > 0) {
      this.onUploadFile(event.target.files[0]);
      this.filename = this.selectedFile.name;
    }
  }
  onUploadFile(file: File) {
    this.fileIsUploading = true;
    this.usersService.changePhoto(file,this.user?.idUser).subscribe((result: any) => {
        this.fileIsUploading=false
        this.usersService.getMemberFromToken(this.loginService.getToken())
      }, (error) => {
        console.log(error);
      }
    );

  }

  onUpdateProfile(f: NgForm) {
    console.log(f.value
    );
  }
}
