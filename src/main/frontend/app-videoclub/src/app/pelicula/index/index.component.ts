import {Component, EventEmitter, Input, Output} from '@angular/core';
import {Pelicula} from "../../pelicula/pelicula";
import {PeliculaService} from "../pelicula.service";
import {ModalComponent} from "../../modal/modal.component";
import { MdbModalRef, MdbModalService } from 'mdb-angular-ui-kit/modal';
import {askConfirmation} from "@angular/cli/src/utilities/prompt";
import {ModalService} from "../../service/modal.service";


@Component({
  selector: 'app-index',
  templateUrl: './index.component.html',
  styleUrls: ['./index.component.css']
})
export class IndexComponent {
  peliculas: Pelicula[] = [];
  @Input() confirm: EventEmitter<boolean> = new EventEmitter<boolean>();


  constructor(public peliculaservice:PeliculaService, private modalService:MdbModalService, private modalService1:ModalService) { }

  ngOnInit(): void {
    this.peliculaservice.getAll().subscribe((data: Pelicula[])=>{
      this.peliculas= data;
      console.log(this.peliculas);
    })

  }

  deletePelicula(id: any) {
    this.modalService.open(ModalComponent);
    this.modalService1.confirm$.subscribe((confirm: boolean) => {
      if (confirm) {
        this.peliculaservice.delete(id).subscribe(res => {
          this.peliculas = this.peliculas.filter(cat => cat.id !== id);

          console.log('Película id =' + id + ' eliminada satisfactoriamente!');
        })
      }
    });
  }

}
