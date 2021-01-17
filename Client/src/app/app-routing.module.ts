import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {SignInComponent} from './sign-in/sign-in.component';
import {SignUpComponent} from './sign-up/sign-up.component';
import {HomeComponent} from './home/home.component';
import {CoursDetailsComponent} from './cours-details/cours-details.component';
import {AdminCoursEnsComponent} from './admin-cours-ens/admin-cours-ens.component';
import {AdminCoursInscriptionComponent} from './admin-cours-inscription/admin-cours-inscription.component';
import {LoginGuardService} from './services/login-guard.service';
import {EnseignantGuardService} from './services/enseignant-guard.service';
import {MesInscriptionsComponent} from './mes-inscriptions/mes-inscriptions.component';
import {EtudiantGuardService} from './services/etudiant-guard.service';
import {ProfileComponent} from './profile/profile.component';
import {CoursCategorieComponent} from './cours-categorie/cours-categorie.component';

const routes: Routes = [
  {path:'sign-in',component:SignInComponent},
  {path: 'sign-up',component:SignUpComponent},
  {path: 'home',component:HomeComponent},
  {path: 'cours',component:HomeComponent},
  {path: 'cours/:coursId',component:CoursDetailsComponent},
  {path: 'cours/categories/:categorieId',component:CoursCategorieComponent},
  {path: 'admin-courses',component:AdminCoursEnsComponent, canActivate:[LoginGuardService,EnseignantGuardService]},
  {path: 'admin-courses/course/:coursIdAdmin',component:AdminCoursInscriptionComponent,canActivate:[LoginGuardService,EnseignantGuardService]},
  {path: 'my-courses',component:MesInscriptionsComponent, canActivate:[LoginGuardService,EtudiantGuardService]},
  {path: 'profile',component:ProfileComponent, canActivate:[LoginGuardService]},

  {path: '',component:HomeComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
