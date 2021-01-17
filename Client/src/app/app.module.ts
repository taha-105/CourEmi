import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './template/header/header.component';
import { FooterComponent } from './template/footer/footer.component';
import { SignInComponent } from './sign-in/sign-in.component';
import { SignUpComponent } from './sign-up/sign-up.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { HomeComponent } from './home/home.component';
import { CoursesComponent } from './courses/courses.component';
import { HttpClientModule} from '@angular/common/http';
import { CoursDetailsComponent } from './cours-details/cours-details.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { AdminCoursEnsComponent } from './admin-cours-ens/admin-cours-ens.component';
import { AdminCoursInscriptionComponent } from './admin-cours-inscription/admin-cours-inscription.component';
import { MesInscriptionsComponent } from './mes-inscriptions/mes-inscriptions.component';
import { ProfileComponent } from './profile/profile.component';
import { CoursCategorieComponent } from './cours-categorie/cours-categorie.component';
import {QuillModule} from 'ngx-quill';
import {SafeHtmlPipe} from './safe-html.pipe';
import {Ng2SearchPipeModule} from 'ng2-search-filter';
import {NgxPaginationModule} from 'ngx-pagination';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    SignInComponent,
    SignUpComponent,
    HomeComponent,
    CoursesComponent,
    CoursDetailsComponent,
    AdminCoursEnsComponent,
    AdminCoursInscriptionComponent,
    MesInscriptionsComponent,
    ProfileComponent,
    CoursCategorieComponent,
    SafeHtmlPipe
  ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        BrowserAnimationsModule,
        HttpClientModule,
        FormsModule,
        QuillModule,
        QuillModule.forRoot(),
        ReactiveFormsModule,
        Ng2SearchPipeModule,
        NgxPaginationModule,


    ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
