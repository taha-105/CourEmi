import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {InscriptionService} from '../services/inscription.service';
import {ActivatedRoute} from '@angular/router';
import {Inscription} from '../model/inscription';
import {Cours} from '../model/cours';
import {CoursService} from '../services/cours.service';
import * as jspdf from 'jspdf'
import html2canvas from 'html2canvas';
import autoTable from 'jspdf-autotable'
@Component({
  selector: 'app-admin-cours-inscription',
  templateUrl: './admin-cours-inscription.component.html',
  styleUrls: ['./admin-cours-inscription.component.css']
})

export class AdminCoursInscriptionComponent implements OnInit {
   coursIdAdmin: number;
   inscriptions: Array<Inscription>;
   cours:Cours
  @ViewChild('htmlData') htmlData:ElementRef;
  export: boolean =true;
  constructor(private inscriptionService:InscriptionService,private route:ActivatedRoute,private coursService:CoursService) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe((param)=>{
      if(param.has('coursIdAdmin')){
        this.coursIdAdmin =parseInt(param.get('coursIdAdmin')) ;
        this.inscriptionService.getInscriptionsByCours(this.coursIdAdmin).subscribe((inscriptions:any[])=>{
          this.inscriptions=inscriptions
          if(this.inscriptions[0]?.cours!=null){
            this.cours = this.inscriptions[0]?.cours
          }
          else{
            this.coursService.getCoursById(this.coursIdAdmin).subscribe((response:Cours)=>{
              this.cours=response
            })
          }

        })
      }

    })
  }

  exportPdf(inscriptions: Array<Inscription>,cours:Cours) {
    /*this.export=false
    let DATA = this.htmlData.nativeElement;
    html2canvas(DATA).then(canvas => {
      var imgWidth = 208;
      var imgHeight = canvas.height * imgWidth / canvas.width;
      const contentDataURL = canvas.toDataURL('image/png')
      let pdf = new jspdf.jsPDF('p', 'mm', 'a4');
      var position = 0;
      pdf.addImage(contentDataURL, 'PNG', 0, position, imgWidth, imgHeight)
      pdf.save('liste_cours_'+titre+'.pdf');
      this.export=true
    });*/
    var doc = new jspdf.jsPDF();
    var col = ["Nom", "Prénom","Email","Date Inscription"];
    var rows = [];

    var imgData = 'data:image/jpeg;base64,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H3mzT6KBhRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAf/9k='




    var rowCountModNew = [
    ]
    inscriptions.map(i=>i).forEach(i=>{
      let newRow = new Array<any>();
      newRow=[i?.etudiant?.nom,i?.etudiant?.prenom,i?.etudiant?.email,i?.dateInscri]
      rowCountModNew.push(newRow)
    })

    rowCountModNew.forEach(element => {
      rows.push(element);

    });

    doc.setFont("bold","oblique");
    doc.text("Enseignant : "+cours?.enseignant?.nom, 10, 15);
    doc.setFont("bold","oblique");
    doc.text("Cours : "+cours?.titre, 160, 15);
    doc.setFont("bold","oblique");
    doc.text("Liste des étudiants inscrits à ce cours", 55, 50);
    //doc.addImage(imgData, 'JPEG', 90, 40, 20, 20);
    doc.addFont('ComicSansMS', 'Comic Sans', 'normal','StandardEncoding');
    autoTable(doc,{columns:col,body:rows,startY:60})
    doc.text("Fait à Rabat : ",10,120);
    doc.text("Signature : ",160,120);
    doc.save('liste_inscriptions_'+cours?.titre+'.pdf');
  }
}
