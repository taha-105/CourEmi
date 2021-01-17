import { Component, OnInit } from '@angular/core';
import {Subscription} from 'rxjs';
import {LoginService} from '../../services/login.service';
import {UsersService} from '../../services/users.service';
import {Router} from '@angular/router';
import {User} from '../../model/user';
import {CategorieService} from '../../services/categorie.service';
import {Categorie} from '../../model/categorie';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  private authListenerSub: Subscription;
  memberIsAuthenticated: boolean = false;
  private userId: number;
  memberNom: string;
  memberPnom: string;
  memberConnected: any
  role: string
  spinnerAvatar: boolean = true;
  categories : Array<Categorie>
  constructor(private loginService: LoginService, private memberService: UsersService, private router: Router,private categorieService:CategorieService) {
  }

  ngOnInit(): void {
    this.categorieService.getAllCategorie()
    this.categorieService.categoriesSubject.asObservable().subscribe((categories:Categorie[])=>{
      this.categories=categories
    })
    this.memberIsAuthenticated = this.loginService.getAuthStatus();
    this.authListenerSub = this.loginService.getAuthStatusListener()
      .subscribe((isAuthenticated) => {
        this.memberIsAuthenticated = isAuthenticated;
        if (this.memberIsAuthenticated) {
          this.memberService.getMemberFromToken(this.loginService.getToken())
          this.memberService.userByTokenSubject.asObservable().subscribe((connectedMember: User) => {
            this.spinnerAvatar = false
            this.memberConnected = connectedMember
            this.role = connectedMember.role;
            this.memberNom = connectedMember.nom;
            this.memberPnom = connectedMember.prenom;
            this.userId = connectedMember.idUser;
          })

        }


      });

    this.memberIsAuthenticated = this.loginService.getAuthStatus();
    if (this.memberIsAuthenticated){
      this.memberService.getMemberFromToken(this.loginService.getToken())
      this.memberService.userByTokenSubject.asObservable().subscribe((connectedMember: User) => {
        this.spinnerAvatar = false
        this.memberConnected = connectedMember
        this.role = connectedMember.role;
        this.memberNom = connectedMember.nom;
        this.memberPnom = connectedMember.prenom;
        this.userId = connectedMember.idUser;
      });
    }
  }

  getPhotoProfile(photoProfile: string) {
    if(photoProfile!=null) return photoProfile
    else return "../../assets/images/unknown.jpg"
  }

  onLogout() {
    this.loginService.logout()
  }

  getAdminRoute(role: string) {
    if(role==='Enseignant') return 'admin-courses';
    else if(role=='Etudiant') return 'my-courses'
  }
}
